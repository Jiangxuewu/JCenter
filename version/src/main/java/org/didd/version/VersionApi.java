package org.didd.version;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.transsnet.version.BuildConfig;
import com.transsnet.version.R;

import org.didd.common.log.L;
import org.didd.common.network.NetUtil;
import org.didd.http.BaseModel;
import org.didd.http.HttpApi;
import org.didd.http.HttpResponse;
import org.didd.http.HttpResponseBody;
import org.didd.http.IHttpCallback;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by Jiangxuewu on 2018/1/23.
 * <p>Check new Version from server</p>
 * <p>Show update dialog</p>
 */

public class VersionApi implements IHttpCallback, View.OnClickListener, DialogInterface.OnKeyListener, DialogInterface.OnCancelListener {

    private static final Object LOCK = new Object();
    private static final String SH_FILE_NAME = "DIDD_VERSION_SP_FILE";
    private static final long DAY_TIME = 24 * 60 * 60 * 1000;
    private static final String TAG = VersionApi.class.getSimpleName();
    private static final String DB_NAME = "DIDD_VERSION_DB";
    private static VersionApi mInstance;
    static String versionHttpUrl;
    private SharedPreferences mSp;
    private Context mContext;
    private String mAppName;
    static boolean mResumed = false;

    private boolean isRequesting;
    private boolean isDialog = false;
    private ICallback callback;
    private String channel;
    private String version;

    public static VersionApi getInstance() {
        synchronized (LOCK) {
            if (null == mInstance) {
                mInstance = new VersionApi();
            }
            return mInstance;
        }
    }

    private VersionApi() {
    }

    public void setContext(Context context) {
        mContext = context;
    }

    //first init
    public void init(Context context, String httpUrl, String appName, String channel, String version) {
        init(context, httpUrl, appName, channel, version, true);
    }

    public void init(Context context, String httpUrl, String appName, String channel, String version, boolean isDialog) {
        if (BuildConfig.DEBUG) L.d(TAG, "init, httpUrl = " + httpUrl);
        if (BuildConfig.DEBUG) L.d(TAG, "init, appName = " + appName);
        if (BuildConfig.DEBUG) L.d(TAG, "init, channel = " + channel);
        if (BuildConfig.DEBUG) L.d(TAG, "init, version = " + version);
        if (TextUtils.isEmpty(httpUrl) || null == context) return;
        // check local update data
        // get update data from server
        // show update dialog
        versionHttpUrl = httpUrl;
        mContext = context;
        mAppName = appName;
        this.isDialog = isDialog;
        this.channel = channel;
        this.version = version;
        this.callback = null;

        DB.getInstance().init(context, DB_NAME);

        mSp = context.getSharedPreferences(SH_FILE_NAME, Context.MODE_PRIVATE);

        requestFromServer(channel, version);
    }

    /**
     * @return version info if exist
     */
    public VersionBean getVersion() {
        VersionBeanDao dao = getDao();

        if (null == dao) {
            return null;
        }

        List<VersionBean> list = dao.queryBuilder().list();

        if (null != list && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    public void startCheck(ICallback callback) {
        this.callback = callback;
        if (null != this.callback) {
            callback.start();
        }
        requestFromServer(channel, version);
    }

    private void requestFromServer(String channel, String version) {
        if (isRequesting) {
            if (null != this.callback) {
                callback.end(ICallback.FAILED_REQUESTING);
            }
            return;
        }
        ReqBodyUpdate bodyUpdate = new ReqBodyUpdate();
        bodyUpdate.packageName = mContext.getPackageName();
        bodyUpdate.cversion = version;
        bodyUpdate.channel = channel;
        bodyUpdate.netType = getNetTypeForServer();

        bodyUpdate.packageName = "com.palmpush.weather";
        bodyUpdate.cversion = "1.1.7";
        bodyUpdate.channel = "google";

        ReqHead head = new ReqHead();

        head.sign = getHeadSign(bodyUpdate);

        VersionReq data = new VersionReq();
        data.reqBody = bodyUpdate;
        data.reqHead = head;

        BaseModel model = new NewVersionModel(data, this);
        HttpApi.getInstance().request(model);

        if (null == this.callback) {
            checkLocalDB();
        }

        isRequesting = true;
    }

    private String getNetTypeForServer() {
        int net = NetUtil.getNetworkState(mContext);
        switch (net) {
            case NetUtil.NETWORN_MOBILE:
                return "3";
            case NetUtil.NETWORN_WIFI:
                return "2";
            case NetUtil.NETWORN_NONE:
            default:
                return "1";
        }
    }


    private String getHeadSign(ReqBodyUpdate bodyUpdate) {
        if (null == bodyUpdate) return null;
        return md5Of32("channel=" + bodyUpdate.channel
                + "&cversion=" + bodyUpdate.cversion
                + "&packageName=" + bodyUpdate.packageName
                + "&netType=" + bodyUpdate.netType
        );
    }

    private void showDialogInUI(final VersionBean data) {
        if (mResumed && !isDialog) {
            return;
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                try {
                    toShowAct(data);
                } catch (Exception ignored) {
                }
                saveCount(data);
            }
        });
    }

    private void toShowAct(VersionBean data) {
        if (isDialog) {
            showDialog(data);
        } else {
            Intent intent = new Intent(mContext, VersionActivity.class);
            intent.putExtra("appName", mAppName);
            intent.putExtra("versionBean", data);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        }
    }

    private void saveCount(VersionBean data) {
        //get sp file key
        String spKey = getSPKey(data.getStrategyName());
        //get alert times
        int totalAlertTimes = getIntFromSp(spKey + "_total");
        // set alert times
        setIntFromSp(spKey + "_total", totalAlertTimes + 1);
        //set last alert time
        setLongFromSp(spKey + "_time");
    }

    @Override
    public void result(HttpResponse httpResponse) {
        isRequesting = false;

//        if (BuildConfig.DEBUG)
//        test();

        if (BuildConfig.DEBUG)
            L.d(TAG, "result, code = " + (null == httpResponse ? "null" : httpResponse.code));
        if (null == httpResponse || httpResponse.code < 200 || httpResponse.code >= 300) {

            if (null != this.callback) {
                callback.end(ICallback.FAILED_NET_ERROR);
            }
            return;
        }


        HttpResponseBody body = httpResponse.getBody();
        if (null != body) {
            String str = body.getString();
            if (!TextUtils.isEmpty(str)) {
                VersionResponse response = new Gson().fromJson(str, VersionResponse.class);
                handleResult(response);
                return;
            }
        }
        if (null != this.callback) {
            callback.end(ICallback.FAILED_NET_UNKNOW);
        }
    }

    private void checkLocalDB() {
        VersionBeanDao dao = getDao();

        if (null == dao) {
            return;
        }

        List<VersionBean> list = dao.queryBuilder().list();

        if (null != list && !list.isEmpty()) {
            VersionBean bean = list.get(0);
            showUpdateMessage(bean);
        }
    }

    private void test() {
        if (BuildConfig.DEBUG)
            L.d(TAG, "result, start test.....");
        VersionBean data = new VersionBean();
//        data.setAlertTimes(21);
//        data.setAlertInterval(0);
//        data.setDownloadUrl("https://play.google.com/store/apps/details?id=com.yomobigroup.yoweather");
//        data.setStrategyName("V1.2.0");
//        data.setUpdateDesc("1, Update 1...\n2, Update 2....\n3, Update 3.......3333\n4, Update 4444\n5, Update 555555");
//        data.setUpgradeStrategy("0");


        data.setAlertTimes(21);
        data.setAlertInterval(0);
        data.setDownloadUrl("market://details?id=com.yomobigroup.yoweather");
        data.setStrategyName("V1.2.1");
        data.setUpdateDesc("1, Update 1...\n2, Update 2....\n3, Update 3.......3333\n4, Update 4444\n5, Update 555555");
        data.setUpgradeStrategy("1");


        data.setAlertTimes(21);
        data.setAlertInterval(0);
        data.setDownloadUrl("market://details?id=com.yomobigroup.yoweather");
        data.setStrategyName("V1.2.2");
        data.setUpdateDesc("1, Update 1...\n2, Update 2....\n3, Update 3.......3333\n4, Update 4444\n5, Update 555555");
        data.setUpgradeStrategy("0");

        showUpdateMessage(data);
    }

    private void handleResult(VersionResponse response) {

        if (null == response || response.getCode() != 0) {

            if (null != this.callback) {
                callback.end(ICallback.FAILED_SERVER_ERROR);
            }
            return;
        }

        showUpdateMessage(response.getData());

    }

    private void showUpdateMessage(VersionBean data) {
        if (null == data) {
            if (BuildConfig.DEBUG) L.d(TAG, "showUpdateMessage, data is null");
            saveToLocalDB(null);
            if (null != this.callback) {
                callback.end(ICallback.SUCCESS_NONE_DATA);
            }
            return;
        }

        saveToLocalDB(data);

        if (null == this.callback) {
            if (checkRight(data)) {
                if (BuildConfig.DEBUG) L.d(TAG, "showUpdateMessage, checkRight = true");
                showDialogInUI(data);
            }
        } else {
            showDialogInUI(data);
            callback.end(ICallback.SUCCESS);
        }
    }

    private VersionBeanDao getDao() {
        DaoSession session = DB.getInstance().getDaoSession();
        if (null == session) {
            return null;
        }
        return session.getVersionBeanDao();
    }

    private void saveToLocalDB(VersionBean data) {
        VersionBeanDao dao = getDao();
        if (null != dao) {
            dao.deleteAll();
            if (null != data) {
                dao.insert(data);
            }
        }
    }

    public boolean checkRight(VersionBean data) {
        if (null == data) {
            if (BuildConfig.DEBUG) L.d(TAG, "checkRight, checkRight = false, data is null");
            return false;
        }

        //get sp file key
        String spKey = getSPKey(data.getStrategyName());
        //get alert times
        int totalAlertTimes = getIntFromSp(spKey + "_total");
        //get last alert time
        long lastAlertTime = getLongFromSp(spKey + "_time");
        //compare alert total times
        if (data.getAlertTimes() >= 0 && data.getAlertTimes() <= totalAlertTimes) {

            if (BuildConfig.DEBUG) L.d(TAG, "checkRight, checkRight = false, total times");
            return false;
        }

        //compare last alert time
        if (data.getAlertInterval() >= 0 && System.currentTimeMillis() - lastAlertTime <= data.getAlertInterval() * DAY_TIME) {
            if (BuildConfig.DEBUG) L.d(TAG, "checkRight, checkRight = false, alert interval");
            return false;
        }
        if (BuildConfig.DEBUG) L.d(TAG, "checkRight, checkRight = true");

        //check net
        return isRightNet(data.getNetType());
    }

    public boolean isRightNet(int netType) {
        int net = NetUtil.getNetworkState(mContext);
        if (BuildConfig.DEBUG) {
            L.d(TAG, "isRightNet, net = " + net + ", netType = " + netType);
        }
        switch (netType) {
            case 1:
                return net == NetUtil.NETWORN_NONE;
            case 2:
                return net == NetUtil.NETWORN_WIFI;
            case 3:
                return net == NetUtil.NETWORN_MOBILE;
            default:
                return true;
        }
    }

    private long getLongFromSp(String key) {
        if (null == mSp) return 0;
        return mSp.getLong(key, 0);
    }

    private void setLongFromSp(String key) {
        if (null == mSp) return;
        mSp.edit().putLong(key, System.currentTimeMillis()).apply();
    }

    private int getIntFromSp(String spKey) {
        if (null == mSp) return 0;
        return mSp.getInt(spKey, 0);
    }

    private void setIntFromSp(String spKey, int value) {
        if (null == mSp) return;
        mSp.edit().putInt(spKey, value).apply();
    }

    private String getSPKey(String key) {
        return md5Of32(key);
    }

    private String md5Of32(String plainText) {
        String result = "";
        if (null == plainText) return null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte[] b = md.digest();

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                int i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }


    ////////////////dialog/////////////////////
    private AlertDialog mDialog;
    private VersionBean data;

    private void showDialog(VersionBean data) {
        if (null != mDialog && mDialog.isShowing()) {
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        View customView = View.inflate(mContext, R.layout.didd_version_layout, null);

        initCustomViewData(customView, data);

        builder.setView(customView);

        mDialog = builder.create();

        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setOnKeyListener(this);
        mDialog.setOnCancelListener(this);

        mDialog.show();
    }

    private void initCustomViewData(View view, VersionBean data) {

        if (null == view) return;
        this.data = data;
        TextView appName = view.findViewById(R.id.version_app_name);
        TextView info = view.findViewById(R.id.version_message);
        Button cancelBtn = view.findViewById(R.id.version_btn_cancel);
        Button updateBtn = view.findViewById(R.id.version_btn_update);

        String title = getAppName() + " " + data.getStrategyName();
        appName.setText(title);
        info.setText(data.getUpdateDesc());
        String tip;
        if ("1".equals(data.getUpgradeStrategy())) {
            cancelBtn.setEnabled(false);
            cancelBtn.setVisibility(View.GONE);
            view.findViewById(R.id.version_line_b).setVisibility(View.GONE);
            tip = "Update";
            updateBtn.setText("");
            int dp160 = dp2px(160);
            ViewGroup.LayoutParams lp = new LinearLayout.LayoutParams(dp160, -1, 0.f);
            updateBtn.setLayoutParams(lp);
        } else {
            cancelBtn.setOnClickListener(this);
            tip = "Try it now";
        }
        updateBtn.setText(tip);
        updateBtn.setOnClickListener(this);
    }

    private String getAppName() {

        return mAppName;
    }

    private int dp2px(int values) {
        float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (values * scale + 0.5f);
    }

    @Override
    public void onClick(View v) {
        if (BuildConfig.DEBUG) L.d(TAG, "onClick, ");
        if (v.getId() == R.id.version_btn_cancel) {
            if (!"1".equals(data.getUpgradeStrategy())) {
                dismiss();
            }
        } else if (v.getId() == R.id.version_btn_update) {
            update();
        }
    }


    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        return true;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        dismiss();
    }

    private void dismiss() {
        if (BuildConfig.DEBUG) L.d(TAG, "dismiss, ");
        try {
            if (null != mDialog) {
                mDialog.dismiss();
            }
        } catch (Exception ignored) {
        }
    }

    private void update() {
        if (BuildConfig.DEBUG) L.d(TAG, "update, ");
        if (null == data) {
            Toast.makeText(mContext, "Url is null, Error", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!"1".equals(data.getUpgradeStrategy())) {
            dismiss();
        }

        String url = data.getDownloadUrl();

        if (isMarketUrl(url)) {
            toMarket(url);
        } else {
            toBrowser(url);
        }
    }

    private void toBrowser(String url) {
        if (BuildConfig.DEBUG) L.d(TAG, "toBrowser, url = " + url);
        try {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri content_url = Uri.parse(url);
            intent.setData(content_url);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        } catch (Exception ignored) {
        }
    }

    private void toMarket(String url) {
        if (BuildConfig.DEBUG) L.d(TAG, "toMarket, url = " + url);
        try {
            startGooglePlay(url);
        } catch (Exception ignored) {
        }
    }

    private boolean isMarketUrl(String url) {
        if (BuildConfig.DEBUG) L.d(TAG, "isMarketUrl, url = " + url);
        return null != url && url.startsWith("market://");
    }

    private void startGooglePlay(String url) {
        Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        boolean marketExist = false;
        final List<ResolveInfo> otherApps = mContext.getPackageManager().queryIntentActivities(mIntent, 0);
        for (ResolveInfo otherApp : otherApps) {
            if (otherApp.activityInfo.applicationInfo.packageName.equals("com.android.vending")) {
                ActivityInfo otherAppActivity = otherApp.activityInfo;
                ComponentName componentName = new ComponentName(otherAppActivity.applicationInfo.packageName, otherAppActivity.name);
                mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mIntent.setComponent(componentName);
                mContext.startActivity(mIntent);
                marketExist = true;
                break;
            }
        }
        if (!marketExist) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + mContext.getPackageName()));
            mContext.startActivity(webIntent);
        }
    }

    ///////////////////////////////////////
    public interface ICallback {
        int SUCCESS = 0;
        int SUCCESS_NONE_DATA = 1;
        int FAILED = -1;
        int FAILED_REQUESTING = -2;
        int FAILED_NET_ERROR = -3;
        int FAILED_SERVER_ERROR = -4;
        int FAILED_NET_UNKNOW = -5;

        void start();

        void end(int resultCode);
    }
}
