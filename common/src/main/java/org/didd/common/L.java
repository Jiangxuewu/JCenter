package org.didd.common;



/**
 * Created by xuewu.jiang@afmobigroup.com on 2017/11/2.
 */

public class L {
    public static boolean debug = true;//BuildConfig.DEBUG;
    public static boolean test = false;

    public static void d(String tag, String msg) {
//        if (debug)
            FLog.d("SKYDEV" + tag, msg);
    }

    public static void i(String tag, String msg) {
//        if (debug)
            FLog.i("SKYDEV" + tag, msg);
    }

    public static void w(String tag, String msg) {
//        if (debug)
            FLog.w("SKYDEV" + tag, msg);
    }

    public static void e(String tag, String msg) {
//        if (debug)
            FLog.e("SKYDEV" + tag, msg);
    }

    public static void u(String tag, String s) {
    }
}
