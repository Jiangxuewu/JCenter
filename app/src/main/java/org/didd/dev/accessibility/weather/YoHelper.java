package org.didd.dev.accessibility.weather;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.view.accessibility.AccessibilityEvent;

import com.bb_sz.accessibilityutils.FZHelper;

/**
 * Created by Jiangxuewu on 2017/12/12.
 */

public class YoHelper extends AccessibilityService {

    public static final String appApk = "/sdcard/TM/Test/YoWeather.apk";
    public static final String appName = "YoWeather";

    public static final String weather = "com.yomobigroup.yoweather";
    public static final String packageinstaller = "com.google.android.packageinstaller";

    private static final String[] packageNames = new String[]{weather, packageinstaller};

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {


        String pkgName = event.getPackageName().toString();

        if (packageinstaller.equalsIgnoreCase(pkgName)) {
            String name = FZHelper.viewTextById(this, "com.android.packageinstaller:id/install_confirm_panel");
            if (appName.equalsIgnoreCase(name)) {
                FZHelper.viewClickById(this, "com.android.packageinstaller:id/ok_button");
            }
        }

    }

    @Override
    public void onInterrupt() {

    }

    @Override
    protected void onServiceConnected() {
        AccessibilityServiceInfo info = getServiceInfo();
        info.packageNames = packageNames;
        setServiceInfo(info);
        super.onServiceConnected();
    }
}
