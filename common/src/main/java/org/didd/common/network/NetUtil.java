package org.didd.common.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/**
 * Created by xuewu.jiang@afmobigroup.com on 2017/11/10.
 * <p>same to ppgSDK</p>
 */

public class NetUtil {

    public static final int NETWORN_NONE = 0;
    public static final int NETWORN_WIFI = 1;
    public static final int NETWORN_MOBILE = 2;


    public static int getNetworkState(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connManager == null) {
            return NETWORN_NONE;
        }

        NetworkInfo netInfo = connManager.getNetworkInfo(1);
        if (netInfo != null) {
            NetworkInfo.State state = netInfo.getState();
            if ((state == NetworkInfo.State.CONNECTED) || (state == NetworkInfo.State.CONNECTING)) {
                return NETWORN_WIFI;
            }

        }

        netInfo = connManager.getNetworkInfo(0);
        if (netInfo != null) {
            NetworkInfo.State state = netInfo.getState();
            if ((state == NetworkInfo.State.CONNECTED) || (state == NetworkInfo.State.CONNECTING)) {
                return NETWORN_MOBILE;
            }
        }

        return NETWORN_NONE;
    }

}
