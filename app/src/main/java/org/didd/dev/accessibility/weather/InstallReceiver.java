package org.didd.dev.accessibility.weather;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.didd.common.BuildConfig;
import org.didd.common.L;

public class InstallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (BuildConfig.DEBUG) {
            L.d("", "method, action = " + action);
        }

    }
}
