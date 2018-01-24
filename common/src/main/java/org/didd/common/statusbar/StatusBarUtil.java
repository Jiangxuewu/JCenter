package org.didd.common.statusbar;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Jiangxuewu on 2018/1/24.
 * <p> set status bar </p>
 */

public class StatusBarUtil {

    // 4.4 - 5.0版本
    public static void setStatusBarUpperAPI19(Activity context) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }

        if (null == context) return;
        Window window = context.getWindow();
        if (null == window) return;
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        ViewGroup mContentView = (ViewGroup) context.findViewById(Window.ID_ANDROID_CONTENT);
        if (null == mContentView) return;
        View statusBarView = mContentView.getChildAt(0);
        //del bar View
        if (statusBarView != null && statusBarView.getLayoutParams() != null &&
                statusBarView.getLayoutParams().height == getStatusBarHeight(context)) {
            mContentView.removeView(statusBarView);
        }
        if (mContentView.getChildAt(0) != null) {
            mContentView.getChildAt(0).setFitsSystemWindows(false);
        }
    }

    public static int getStatusBarHeight(Activity context) {
        int result = 0;
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            result = context.getResources().getDimensionPixelSize(resId);
        }
        return result;
    }

    /**
     * call before {@link Activity#onCreate(Bundle)}
     *
     * @param activity Activity
     */
    public static void setWindowTransparent(Activity activity, boolean statusBarTranEnable, boolean navigationTranEnable) {
        if (null == activity) return;
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            if (null == window) return;
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (statusBarTranEnable) {
                window.setStatusBarColor(Color.TRANSPARENT);
            }
            if (navigationTranEnable) {
                window.setNavigationBarColor(Color.TRANSPARENT);
            }
        } else if (statusBarTranEnable) {
            setStatusBarUpperAPI19(activity);
        }
    }
}
