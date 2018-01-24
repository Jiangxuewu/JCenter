package org.didd.version;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.google.gson.Gson;

import org.didd.common.log.L;
import org.didd.http.BaseModel;
import org.didd.http.HttpApi;
import org.didd.http.HttpResponse;
import org.didd.http.HttpResponseBody;
import org.didd.http.IHttpCallback;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Jiangxuewu on 2018/1/23.
 * <p>Check new Version from server</p>
 * <p>Show update dialog</p>
 */

public class VersionApi implements IHttpCallback {

    private static final Object LOCK = new Object();
    private static final String SH_FILE_NAME = "DIDD_VERSION_SP_FILE";
    private static final long DAY_TIME = 24 * 60 * 60 * 1000;
    private static final String TAG = VersionApi.class.getSimpleName();
    private static final boolean debug = true;
    private static VersionApi mInstance;
    static String versionHttpUrl;
    private SharedPreferences mSp;
    private Context mContext;
    private String mAppName;

    private boolean isRequesting;

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

    //first init
    public void init(Context context, String httpUrl, String appName, String channel, String version) {
        if (debug) L.d(TAG, "init, httpUrl = " + httpUrl);
        if (debug) L.d(TAG, "init, appName = " + appName);
        if (debug) L.d(TAG, "init, channel = " + channel);
        if (debug) L.d(TAG, "init, version = " + version);
        if (TextUtils.isEmpty(httpUrl) || null == context) return;
        // check local update data
        // get update data from server
        // show update dialog
        versionHttpUrl = httpUrl;
        mContext = context;
        mAppName = appName;

        mSp = context.getSharedPreferences(SH_FILE_NAME, Context.MODE_PRIVATE);

        requestFromServer(channel, version);
    }

    private void requestFromServer(String channel, String version) {
        if (isRequesting) {
            return;
        }
        ReqBodyUpdate bodyUpdate = new ReqBodyUpdate();
        bodyUpdate.packageName = mContext.getPackageName();
        bodyUpdate.cversion = version;
        bodyUpdate.channel = channel;

        ReqHead head = new ReqHead();

        head.sign = getHeadSign(bodyUpdate);

        VersionReq data = new VersionReq();
        data.reqBody = bodyUpdate;
        data.reqHead = head;

        BaseModel model = new NewVersionModel(data, this);
        HttpApi.getInstance().request(model);
        isRequesting = true;
    }


    private String getHeadSign(ReqBodyUpdate bodyUpdate) {
        if (null == bodyUpdate) return null;
        return md5Of32("channel=" + bodyUpdate.channel
                + "&cversion=" + bodyUpdate.cversion
                + "&packageName=" + bodyUpdate.packageName);
    }

    private void showDialogInUI(final VersionBean data) {
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
        Intent intent = new Intent(mContext, VersionActivity.class);
        intent.putExtra("appName", mAppName);
        intent.putExtra("versionBean", data);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
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
        if (debug)
            L.d(TAG, "result, code = " + (null == httpResponse ? "null" : httpResponse.code));
        if (null == httpResponse || httpResponse.code < 200 || httpResponse.code >= 300) {
            return;
        }


        HttpResponseBody body = httpResponse.getBody();
        if (null != body) {
            String str = body.getString();
            if (!TextUtils.isEmpty(str)) {
                VersionResponse response = new Gson().fromJson(str, VersionResponse.class);
                handleResult(response);
            }
        }
    }

    private void test() {
        if (debug)
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
        data.setAlertInterval(1);
        data.setDownloadUrl("market://details?id=com.yomobigroup.yoweather");
        data.setStrategyName("V1.2.2");
        data.setUpdateDesc("1, Update 1...\n2, Update 2....\n3, Update 3.......3333\n4, Update 4444\n5, Update 555555");
        data.setUpgradeStrategy("1");

        showUpdateMessage(data);
    }

    private void handleResult(VersionResponse response) {

        if (null == response || response.getCode() != 0) {
            return;
        }

        showUpdateMessage(response.getData());

    }

    private void showUpdateMessage(VersionBean data) {
        if (null == data) {
            if (debug) L.d(TAG, "showUpdateMessage, data is null");
            if (debug)
                test();
            return;
        }

        if (checkRight(data)) {
            if (debug) L.d(TAG, "showUpdateMessage, checkRight = true");
            showDialogInUI(data);
        }
    }

    private boolean checkRight(VersionBean data) {
        if (null == data) {
            if (debug) L.d(TAG, "checkRight, checkRight = false, data is null");
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

            if (debug) L.d(TAG, "checkRight, checkRight = false, total times");
            return false;
        }

        //compare last alert time
        if (data.getAlertInterval() >= 0 && System.currentTimeMillis() - lastAlertTime <= data.getAlertInterval() * DAY_TIME) {
            if (debug) L.d(TAG, "checkRight, checkRight = false, alert interval");
            return false;
        }
        if (debug) L.d(TAG, "checkRight, checkRight = true");
        return true;
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


}
