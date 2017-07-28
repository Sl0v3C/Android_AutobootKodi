package com.pyy.autobootkodi;

/**
 * Created by Sl0v3C on 2016/5/9.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Receiver extends BroadcastReceiver {
    static final String action_boot= Intent.ACTION_BOOT_COMPLETED;
    static final String logTag = "[AutoBootKodi]";
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean findApp = false;
        if (intent.getAction().equals(action_boot)) {
            Log.i(logTag, "Receive " + action_boot);
            Intent mBootIntent = new Intent(context, AutoBootKodi.class);
            mBootIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(mBootIntent);
            Log.i(logTag, "Done!");
            String intentName = new String("");
            PackageManager packageManager = context.getPackageManager();
            ArrayList<AppInfo> appList = new ArrayList<AppInfo>();
            List<PackageInfo> packages = packageManager.getInstalledPackages(0);
            for(int i = 0;i < packages.size();i++) {
                PackageInfo packageInfo = packages.get(i);
                AppInfo tempInfo = new AppInfo();
                tempInfo.appName = packageInfo.applicationInfo.loadLabel(packageManager).toString();
                tempInfo.packageName = packageInfo.packageName;
                tempInfo.versionName = packageInfo.versionName;
                tempInfo.versionCode = packageInfo.versionCode;
                tempInfo.appIcon = packageInfo.applicationInfo.loadIcon(packageManager);
                // if want to launch the app not system app, add below judgement
                if((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0)
                {
                    if (tempInfo.packageName.equals("org.xbmc.xbmc")) {
                        intentName = new String(context.getResources().getString(R.string.boot_app_name) +
                                context.getResources().getString(R.string.old_app_name));
                        findApp =true;
                    } else if (tempInfo.packageName.equals("org.xbmc.kodi")) {
                        intentName = new String(context.getResources().getString(R.string.boot_app_name) +
                                context.getResources().getString(R.string.new_app_name));
                        findApp =true;
                    }
                    if (findApp) {
                        appList.add(tempInfo);
                        Log.i(logTag, "intentName is " + intentName);
                        tempInfo.print();
                    }
                }
            }

            if (findApp) { // should use flag findApp to check the intentName has string or null
                Intent mBootUpIntent = packageManager.getLaunchIntentForPackage(intentName);
                mBootUpIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(mBootUpIntent);
            }

        }

    }

}