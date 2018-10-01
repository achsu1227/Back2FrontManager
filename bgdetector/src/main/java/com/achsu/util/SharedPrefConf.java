package com.achsu.util;

import android.content.Context;

public class SharedPrefConf {
    private Context mContext;
    private String mName;
    private int mMode;

    private SharedPrefConf(Context var1) {
        this.mContext = var1;
    }

    public SharedPrefConf setSharedPrefName(String var1) {
        this.mName = var1;
        return this;
    }

    private SharedPrefConf setMode(int var1) {
        this.mMode = var1;
        return this;
    }

    public String getSharedPrefName() {
        return this.mName;
    }

    public int getMode() {
        return this.mMode;
    }

    public Context getContext() {
        return this.mContext;
    }

    public static class Builder {
        private Context _Context;
        private String _Name;
        private int _Mode;

        public Builder(Context var1) {
            this._Context = var1;
        }

        public SharedPrefConf.Builder setName(String var1) {
            this._Name = var1;
            return this;
        }

        public SharedPrefConf.Builder setMode(int var1) {
            this._Mode = var1;
            return this;
        }

        public SharedPrefConf build() {
            SharedPrefConf var1 = (new SharedPrefConf(this._Context.getApplicationContext())).setMode(this._Mode).setSharedPrefName(this._Name);
            return var1;
        }
    }
}
