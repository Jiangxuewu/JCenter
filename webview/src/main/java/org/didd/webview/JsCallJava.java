package org.didd.webview;

import android.app.Activity;
import android.content.Context;
import android.webkit.JavascriptInterface;

/**
 * Created by Administrator on 2016/5/18.
 * <p>JS Call to Java</p>
 */
public class JsCallJava {

    private final Context context;

    public JsCallJava(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public void finish() {
        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
    }

}
