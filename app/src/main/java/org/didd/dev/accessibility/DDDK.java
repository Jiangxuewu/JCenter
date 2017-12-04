package org.didd.dev.accessibility;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.ComponentName;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

/**
 * Created by jiangxuewu on 2017/12/3.
 */

public class DDDK extends AccessibilityService {
    private static final String TAG = DDDK.class.getSimpleName();

//    private static final String packageinstaller = "com.android.packageinstaller";
//    private static final String supersu = "eu.chainfire.supersu";
    private static final String dd = "com.alibaba.android.rimet";

    private static final String[] packageNames = new String[]{dd};


    private String currentActivityName;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        switch (event.getEventType()) {
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED://32
                ComponentName componentName = new ComponentName(event.getPackageName().toString(), event.getClassName().toString());
                try {
                    getPackageManager().getActivityInfo(componentName, 0);
                    currentActivityName = componentName.flattenToShortString();
                } catch (Exception ignored) {
                }
                Log.i(TAG, "setCurrentActivityName,...currentActivityName is " + currentActivityName);
                break;
        }
        String pkgName = event.getPackageName().toString();
        Log.e(TAG, "pkg name is " + pkgName + ", cur act name is " + currentActivityName);

    }

    @Override
    public void onInterrupt() {
        Log.d(TAG, "onInterrupt,...");
    }

    @Override
    protected void onServiceConnected() {
        Log.d(TAG, "onServiceConnected,...");
        AccessibilityServiceInfo info = getServiceInfo();
        info.packageNames = packageNames;
        setServiceInfo(info);
        super.onServiceConnected();
    }
}
