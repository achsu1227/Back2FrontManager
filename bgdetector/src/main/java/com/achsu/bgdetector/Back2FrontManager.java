package com.achsu.bgdetector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import com.achsu.util.SharedPrefConf;
import com.achsu.util.SharedPrefUtil;

/**
 * Created by ac on 2017/5/17.
 */

public class Back2FrontManager {
    private static final String OBJECT_SHAREPREFERENCE_TABLE = "bg_detector";
    private static volatile Back2FrontManager singleton;

    private Application application;
    private HashMap<String, Kit> innerKits = new HashMap<>();

    private Back2FrontManager(Application application) {
        this.application = application;
    }

    private Back2FrontManager(Application application, Kit... kits) {
        this(application);
        setKits(kits);
    }

    /**
     * initial Back2FrontManager.
     * @param back2FrontManagerBuilder Back2FrontManager.Builder
     * @return  Back2FrontManager
     */
    public static Back2FrontManager with(Back2FrontManager.Builder back2FrontManagerBuilder) {
        if (singleton == null) {
            synchronized (Back2FrontManager.class) {
                if (singleton == null) {
                    setBack2FrontManager(back2FrontManagerBuilder.build());
                }
            }
        }
        return singleton;
    }

    private static void setBack2FrontManager(Back2FrontManager back2FrontManager) {
        singleton = back2FrontManager;
        back2FrontManager.init();
    }

    private void init() {
        initialSharedPrefUtil();
        registerActivityLifecycleCallbacks();
    }

    private void initialSharedPrefUtil() {
        if (!SharedPrefUtil.isInitial()) {
            SharedPrefUtil.initialSharedPrefUtil(new SharedPrefConf.Builder(application)
                    .setName(OBJECT_SHAREPREFERENCE_TABLE).build());
        }
    }

    private void registerActivityLifecycleCallbacks() {
        application.registerActivityLifecycleCallbacks(BgDetectorActivityLifecycleCallbacks
                .createActivityLifecycleCallbacks(new BgDetectorActivityLifecycleCallbacks.IStartEventListener() {
                    @Override
                    public boolean start(Activity activity) {
                        return startKits(activity);
                    }
                }));
    }

    /**
     * start kits event.
     * @param activity  current Activity
     * @return  is successful
     */
    public boolean startKits(Activity activity) {
        if (!innerKits.isEmpty()) {
            for (Kit kit : innerKits.values()) {
                kit.execute(activity);
            }
            return true;
        }
        return false;
    }

    /**
     * set kits event.
     * @param kits kit Array
     */
    public void setKits(Kit... kits) {
        if (kits == null) {
            return;
        }

        for (Kit kit : kits) {
            innerKits.put(kit.getClass().getSimpleName(), kit);
        }
    }

    /**
     * get using Kit object.
     * @param kitName   kit class name
     * @param <T>   type
     * @return  Kit object
     */
    public <T extends Kit> T getKit(Class<T> kitName) {
        if (innerKits.containsKey(kitName.getSimpleName())) {
            return (T) innerKits.get(kitName.getSimpleName());
        }
        throw new NullPointerException("not found match kit");
    }

    /**
     * get Back2FrontManager Instance.
     * @return  Back2FrontManager
     */
    public static Back2FrontManager getInstance() {
        return singleton;
    }

    /**
     * get Application.
     * @return  Application
     */
    public Application getApplication() {
        return application;
    }

    /**
     * Back2FrontManager.Builder for Back2FrontManager
     */
    public static class Builder {
        private Application application;
        private List<Kit> kits = new ArrayList();

        public Builder(Application application) {
            this.application = application;
        }

        public Builder addKit(Kit kit) {
            kits.add(kit);
            return this;
        }

        public Back2FrontManager build() {
            Kit[] array = kits.toArray(new Kit[kits.size()]);
            return new Back2FrontManager(application, array);
        }
    }
}
