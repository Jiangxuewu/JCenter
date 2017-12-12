package org.didd.dev.tools;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Jiangxuewu on 2017/12/12.
 */

public class Utils {


    public static final String pkgWhatsapp = "com.whatsapp";
    public static final String pkgGooglePlus = "com.google.android.apps.plus";

    private Utils() {
    }

    private static final String TAG = "Util";

    private static long clickTime;


    public static String getProp(String key) {
        String line = null;
        try {

            String method = "getprop";
            Process p = new ProcessBuilder(method, key).start();
            InputStreamReader inputStreamReader = new InputStreamReader(p.getInputStream());
            BufferedReader reader = new BufferedReader(inputStreamReader);
            line = reader.readLine();
            reader.close();
        } catch (Exception ignored) {
        }
        return line;
    }

    public static boolean isExists(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                if (null != packageName && null != pinfo.get(i) && packageName.equalsIgnoreCase(pinfo.get(i).packageName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isDoubleClick() {
        if (clickTime > 0 && System.currentTimeMillis() - clickTime < 300) {
            return true;
        }
        clickTime = System.currentTimeMillis();
        return false;
    }
}
