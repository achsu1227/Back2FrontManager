package com.achsu.bgdetector;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

import com.achsu.util.SharedPrefUtil;

public class BgDetectorActivityLifecycleCallbacks {

    private static final String OBJECT_SHAREPREFERENCE_KEY = "hashValue";

    /**
     * get ActivityLifecycleCallbacks.
     * @param listener  IStartEventListener
     * @return  ActivityLifecycleCallbacks
     */
    public static ActivityLifecycleCallbacks createActivityLifecycleCallbacks(final IStartEventListener listener) {
        return new Application.ActivityLifecycleCallbacks() {
            private int hashCode;
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                hashCode = 0;
                if (savedInstanceState != null) {
                    hashCode = savedInstanceState.getInt(OBJECT_SHAREPREFERENCE_KEY);
                }
            }

            @Override
            public void onActivityStarted(Activity activity) { }

            @Override
            public void onActivityResumed(Activity activity) {
                if (SharedPrefUtil.newInstance().getInt(OBJECT_SHAREPREFERENCE_KEY)
                        .equals(hashCode != 0 ? hashCode : activity.hashCode())) {
                    listener.start(activity);
                }
                SharedPrefUtil.newInstance().put(OBJECT_SHAREPREFERENCE_KEY, activity.hashCode());
            }

            @Override
            public void onActivityPaused(Activity activity) {
                hashCode = 0;
            }

            @Override
            public void onActivityStopped(Activity activity) {
                hashCode = 0;
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                hashCode = activity.hashCode();
                outState.putInt(OBJECT_SHAREPREFERENCE_KEY, hashCode);
            }

            @Override
            public void onActivityDestroyed(Activity activity) { }
        };
    }

    interface IStartEventListener {
        boolean start(Activity activity);
    }
}
