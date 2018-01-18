package org.didd.common.log;


import android.text.TextUtils;
import android.util.Log;

/**
 * Created by xuewu.jiang@afmobigroup.com on 2017/11/2.
 * <p>Log helper</p>
 */

public class L {

    private final static String TAG = "SKYDEV ";

    public static void d(String tag, String msg) {
        FLog.d(toTag(tag), toMsg(msg));
    }

    public static void w(String tag, String msg, Throwable tr) {
        FLog.d(toTag(tag), toMsg(msg));
        Log.w(toTag(tag), toMsg(msg), tr);
    }

    private static String toMsg(String msg) {
//        int index = 4;
//        String str2 = Thread.currentThread().getStackTrace()[index].getClassName();
//        String str3 = str2.substring(1 + str2.lastIndexOf("."));
//        if (str3.contains("$"))
//            str3 = str3.substring(0, str3.lastIndexOf("$"));
//        String str4 = Thread.currentThread().getStackTrace()[index].getMethodName();
//        int i = Thread.currentThread().getStackTrace()[index].getLineNumber();
//        long threadId = Thread.currentThread().getId();
//        return "at(" + str3 + ".java:" + i + ")[" + str4 + " " + threadId + "]" + msg;
        return msg;
    }

    private static String toTag(String tag) {
        return TAG + tag;
    }

    public static void i(String tag, String msg) {
        FLog.i(toTag(tag), toMsg(msg));
    }

    public static void w(String tag, String msg) {
        FLog.w(toTag(tag), toMsg(msg));
    }

    public static void e(String tag, String msg) {
        FLog.e(toTag(tag), toMsg(msg));
    }

    public static void u(String tag, String s) {
        if (!TextUtils.isEmpty(s) || !TextUtils.isEmpty(tag)) {
            Log.v(toTag(tag), s);
        }
    }
}
