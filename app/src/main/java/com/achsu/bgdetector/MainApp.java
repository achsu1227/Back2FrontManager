package com.achsu.bgdetector;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

public class MainApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Back2FrontManager.with(new Back2FrontManager.Builder(this).addKit(new Kit() {
            @Override
            public void execute(Activity activity) {
                Log.e("hello", "hello");
            }
        }));
    }
}
