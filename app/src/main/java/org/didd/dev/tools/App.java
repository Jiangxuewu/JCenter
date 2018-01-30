package org.didd.dev.tools;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;

import org.didd.common.BuildConfig;
import org.didd.common.log.L;

import java.io.File;

/**
 * Created by Jiangxuewu on 2017/12/12.
 */

public class App {

    public static void launchApp(Context context, String packageName) {
        if (TextUtils.isEmpty(packageName))
            return;
        PackageManager pm = context.getPackageManager();

        Intent intent = pm.getLaunchIntentForPackage(packageName);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                L.d("", "method");
            }
        }
    }

    public static void uninstallApp(Context context, String packageName) {
        if (TextUtils.isEmpty(packageName))
            return;

        try {
            Uri packageURI = Uri.parse("package:" + packageName);
            Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
            uninstallIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(uninstallIntent);
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                L.d("", "method");
            }
        }
    }

    public static void installAppFile(Context context, String filePath) {
        if (filePath != null) {
            File file = new File(filePath);
            if (!file.exists()) {
                return;
            }

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            if (context.getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
                context.startActivity(intent);
            }
        }
    }
}
