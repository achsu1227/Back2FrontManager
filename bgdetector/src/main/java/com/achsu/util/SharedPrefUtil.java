package com.achsu.util;

import java.util.HashSet;
import java.util.Set;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPrefUtil {
    private static volatile SharedPrefUtil sSharedPrefUtil;
    private SharedPrefConf mSharedPrefConf;

    public static void initialSharedPrefUtil(SharedPrefConf var0) {
        if (sSharedPrefUtil == null) {
            Class var1 = SharedPrefUtil.class;
            synchronized (SharedPrefUtil.class) {
                if (sSharedPrefUtil == null) {
                    sSharedPrefUtil = new SharedPrefUtil();
                }
            }
        }

        sSharedPrefUtil.setSharedPrefConf(var0);
    }

    public static void initialSharedPrefUtil(SharedPrefUtil var0) {
        sSharedPrefUtil = var0;
    }

    public static SharedPrefUtil newInstance() {
        checkInitial();
        return sSharedPrefUtil;
    }

    private SharedPrefUtil() {
    }

    public void setSharedPrefConf(SharedPrefConf var1) {
        this.mSharedPrefConf = var1;
    }

    public SharedPrefConf getSharedPrefConf() {
        return this.mSharedPrefConf;
    }

    public static boolean isInitial() {
        return sSharedPrefUtil != null && sSharedPrefUtil.getSharedPrefConf() != null;
    }

    public static void checkInitial() {
        if (!isInitial()) {
            throw new RuntimeException("SharedPrefUtil need initial it before use it.");
        }
    }

    public void putObject(String var1, Object var2) {
        SharedPreferences var3 = this.mSharedPrefConf.getContext().getSharedPreferences(this.mSharedPrefConf.getSharedPrefName(), this.mSharedPrefConf.getMode());
        Editor var4 = var3.edit();
        if (var2 instanceof String) {
            var4.putString(var1, (String) var2);
        } else if (var2 instanceof Boolean) {
            var4.putBoolean(var1, (Boolean) var2);
        } else if (var2 instanceof Float) {
            var4.putFloat(var1, (Float) var2);
        } else if (var2 instanceof Integer) {
            var4.putInt(var1, (Integer) var2);
        } else if (var2 instanceof Long) {
            var4.putLong(var1, (Long) var2);
        } else if (var2 instanceof Set) {
            var4.putStringSet(var1, (Set) var2);
        }

        var4.commit();
    }

    public Editor getEditor() {
        SharedPreferences var1 = this.mSharedPrefConf.getContext().getSharedPreferences(this.mSharedPrefConf.getSharedPrefName(), this.mSharedPrefConf.getMode());
        Editor var2 = var1.edit();
        return var2;
    }

    public SharedPreferences getSharedPref() {
        return this.mSharedPrefConf.getContext().getSharedPreferences(this.mSharedPrefConf.getSharedPrefName(), this.mSharedPrefConf.getMode());
    }

    public static void put(String var0, Object var1) {
        SharedPrefUtil var2 = newInstance();
        var2.putObject(var0, var1);
    }

    public static void remove(String var0) {
        newInstance().getEditor().remove(var0).commit();
    }

    public static void clear() {
        newInstance().getEditor().clear().commit();
    }

    public static String getStr(String var0) {
        return getStr(var0, "");
    }

    public static String getStr(String var0, String var1) {
        return newInstance().getSharedPref().getString(var0, var1);
    }

    public static boolean getBoolean(String var0) {
        return newInstance().getSharedPref().getBoolean(var0, false);
    }

    public static boolean getBoolean(String var0, boolean var1) {
        return newInstance().getSharedPref().getBoolean(var0, var1);
    }

    public static Float getFloat(String var0) {
        return newInstance().getSharedPref().getFloat(var0, 0.0F);
    }

    public static Float getFloat(String var0, float var1) {
        return newInstance().getSharedPref().getFloat(var0, var1);
    }

    public static Integer getInt(String var0) {
        return newInstance().getSharedPref().getInt(var0, 0);
    }

    public static Integer getInt(String var0, int var1) {
        return newInstance().getSharedPref().getInt(var0, var1);
    }

    public static Long getLong(String var0) {
        return newInstance().getSharedPref().getLong(var0, 0L);
    }

    public static Long getLong(String var0, long var1) {
        return newInstance().getSharedPref().getLong(var0, var1);
    }

    public static Set<String> getSet(String var0) {
        return newInstance().getSharedPref().getStringSet(var0, new HashSet());
    }

    public static Set<String> getSet(String var0, Set<String> var1) {
        return newInstance().getSharedPref().getStringSet(var0, var1);
    }
}
