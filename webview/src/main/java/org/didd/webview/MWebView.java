package org.didd.webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

/**
 * Created by Administrator on 2016/5/18.
 * <p>Custom WebView For Me</p>
 */
/*public*/ class MWebView extends VideoEnabledWebView {
    private static final String TAG = MWebView.class.getSimpleName();

    public MWebView(Context context) {
        super(context);
        init();
    }

    public MWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        WebSettings settings = getSettings();
        //Self matching font size
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        } else {
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
            //Set the scale of font zoom, default 100
            settings.setTextZoom(100);
        }

        settings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setRenderPriority(WebSettings.RenderPriority.LOW);
        settings.setPluginState(WebSettings.PluginState.ON);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
//        settings.setNavDump(true);//Deprecated
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(false);

        settings.setUseWideViewPort(true);

        //if set to true, {@link WebChromeClient#onCreateWindow} must be implemented by the host application. The default is false.
        settings.setSupportMultipleWindows(false);

        settings.setStandardFontFamily("sans-serif");//sans-serif  schrappen
        settings.setFixedFontFamily("monospace");//monospace
        settings.setMinimumFontSize(8);
        settings.setDefaultFixedFontSize(8);

        //Whether to allow the loading of the picture
        settings.setBlockNetworkImage(false);
        settings.setBlockNetworkLoads(false);


        //Whether to allow the execution of the JavaScript statement
        settings.setJavaScriptEnabled(false);

        //Enable database
        settings.setDatabaseEnabled(true);

        String cacheDirPath = getContext().getCacheDir().getAbsolutePath() + "/webViewCache ";
        if (!TextUtils.isEmpty(cacheDirPath)) {
            settings.setAppCachePath(cacheDirPath);
            settings.setAppCacheEnabled(true);
//            settings.setAppCacheMaxSize(1024 * 1024 * 10);//
        }


        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDefaultTextEncodingName("UTF-8");//default is "UTF-8"

        settings.setUserAgentString("");
        Log.d(TAG, "WebView's user-agent: " + settings.getUserAgentString());


        settings.setAllowFileAccess(true);
        settings.setAllowContentAccess(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            settings.setAllowFileAccessFromFileURLs(false);
            settings.setAllowUniversalAccessFromFileURLs(false);
        }

//		settings.setPluginsEnabled(true);
        settings.setLoadWithOverviewMode(true);

        String dir = getContext().getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
        //Geolocation
        settings.setGeolocationEnabled(true);
        //Setting the location of the database path
        settings.setGeolocationDatabasePath(dir);
        //Setting up DOM storage and opening JS positioning function
        settings.setDomStorageEnabled(true);

        setBackgroundColor(0); // Setting the background color


        //JsInterface Method one
//        jsJavaInterface = new JsCallJava(getContext());
//        addJavascriptInterface(jsJavaInterface);
        //JsBridge  Method two
//        setWebChromeClient(new WebChromeClient());
        //JsRouter  Method three
//        setWebViewClient(new WebViewClient());
    }

    @SuppressLint({"JavascriptInterface", "AddJavascriptInterface"})
    public void addJavascriptInterface(Object object) {
        WebSettings settings = getSettings();
        if (null != settings) {
            settings.setJavaScriptEnabled(null != object);
            addJavascriptInterface(object, control);
        }
    }

    @Override
    public void loadData(String data, String mimeType, String encoding) {
        super.loadData(data, mimeType, encoding);
    }

    @Override
    public void loadDataWithBaseURL(String baseUrl, String data, String mimeType, String encoding, String historyUrl) {
        super.loadDataWithBaseURL(baseUrl, data, mimeType, encoding, historyUrl);
    }

    @Override
    public void loadUrl(String url) {
        super.loadUrl(url);
    }

    @Override
    public void setWebChromeClient(WebChromeClient client) {
        super.setWebChromeClient(client);
    }

    @Override
    public void setWebViewClient(WebViewClient client) {
        super.setWebViewClient(client);
    }

}
