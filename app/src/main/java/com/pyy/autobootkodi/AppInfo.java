package com.pyy.autobootkodi;

/**
 * Created by Sl0v3C on 2016/5/9.
 */


import android.graphics.drawable.Drawable;
import android.util.Log;

public class AppInfo {
    static final String logTag = "[AutoBootKodi]";
    public String appName="";
    public String packageName="";
    public String versionName="";
    public int versionCode=0;
    public Drawable appIcon=null;
    public void print()
    {
        Log.v(logTag, "APP Name:" + appName + " Package:" + packageName);
        Log.v(logTag, "APP Name:" + appName + " versionName:" + versionName);
        Log.v(logTag, "APP Name:" + appName + " versionCode:" + versionCode);
    }
}