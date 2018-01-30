package org.didd.admob;

import android.content.Context;
import android.text.TextUtils;

/**
 * Created by Jiangxuewu on 2018/1/22.
 * <p>Util for dev admob</p>
 */

public class AdmobApi {
    public static String dbName = "ad_admob_db";
    public static String adHttpUrl;

    /**
     * init
     *
     * @param context   Context
     * @param channel   Channel
     * @param adHttpUrl server url
     */
    public static void init(Context context, String channel, String adHttpUrl) {
        AdmobApi.adHttpUrl = adHttpUrl;
        DB.getInstance().init(context, dbName);
        AdManager.getInstance().getAdList(context.getPackageName(), channel);
    }

    /**
     * @param isFirst  True MEAN First start app.
     * @param defValue default appid
     * @return return appid
     */
    public static String getAppid(boolean isFirst, String defValue) {
        String result = AdManager.getInstance().getAppID(isFirst, defValue);
        AdManager.getInstance().init(!TextUtils.isEmpty(result));
        return result;
    }

    /**
     * @param positionSerial  the location id
     * @param isFirstStartApp True MEAN First start app
     * @param defValue        default ad unit id
     * @return return ad unit id
     */
    public static String getAdUnitId(String positionSerial, boolean isFirstStartApp, String defValue) {
        return AdManager.getInstance().getAdUnitID(positionSerial, isFirstStartApp, defValue);
    }
}
