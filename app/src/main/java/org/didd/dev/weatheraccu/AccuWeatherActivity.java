package org.didd.dev.weatheraccu;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.bbsz.mlibrary.permissions.PermissionUtil;
import com.google.gson.Gson;

import org.didd.common.L;
import org.didd.dev.R;
import org.didd.dev.weatheraccu.data.AccuLocationData;
import org.didd.dev.weatheraccu.response.AccuLocationBean;
import org.didd.dev.weatheraccu.service.AccuLocationService;
import org.didd.http.HttpResponse;
import org.didd.http.IHttpCallback;

import java.io.Serializable;

public class AccuWeatherActivity extends AppCompatActivity {

    private static final String TAG = AccuWeatherActivity.class.getSimpleName();
    private PermissionUtil permissionUtil;
    private BroadcastReceiver locationReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (L.debug) L.i(TAG, "action:" + action);
            if (AccuLocationService.ACTION_LOCATION_UPDATE.equals(action)) {
                Serializable data = intent.getSerializableExtra(AccuLocationService.KEY_LOCATION_UPDATE);

                if (null != data && data instanceof AccuLocation) {
                    updateWeather((AccuLocation) data);
                }

            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accu_weather);
        permissionUtil = new PermissionUtil();
        permissionUtil.requestPermission(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, new PermissionUtil.OnCheckPermissionCallback() {
            @Override
            public void requestPermissionSuccess() {
                startLocationService();

            }

            @Override
            public void requestPermissionFailed() {
                stopLocationService();

            }
        });

        registerLocationReceiver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterLocationReceiver();
    }

    private void stopLocationService() {
//        stopService()
        if (L.debug) L.i(TAG, "stopLocationService");
    }

    private void startLocationService() {
        if (L.debug) L.i(TAG, "startLocationService");
        startService(new Intent(this, AccuLocationService.class));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (null != permissionUtil)
            permissionUtil.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    private void registerLocationReceiver() {
        IntentFilter intent = new IntentFilter(AccuLocationService.ACTION_LOCATION_UPDATE);

        registerReceiver(locationReceiver, intent);

    }

    private void unregisterLocationReceiver() {
        unregisterReceiver(locationReceiver);
    }


    private void updateWeather(AccuLocation data) {

        if (L.debug) L.i(TAG, "updateWeather:" + (null == data ? "null" : new Gson().toJson(data)));
        Configuration config = getResources().getConfiguration();
        String country = config.locale.getCountry();
        String language = config.locale.getLanguage();

        AccuWeatherApi api = new AccuWeatherApi();


        AccuLocationData info = new AccuLocationData();
        info.q = "" + data.getLat() + "," + data.getLon();
        info.language = language.toLowerCase() + "-" + country.toLowerCase();
        info.details = true;
        info.toplevel = false;
        api.getLocation(info, new IHttpCallback() {
            @Override
            public void result(HttpResponse httpResponse) {

                AccuLocationBean bean = AccuWeatherApi.parseJsonToObject(httpResponse, AccuLocationBean.class);
                if (null != bean) {
                    updateUI(bean);
                    if (L.debug) {
                        L.d(TAG, "bean:" + new Gson().toJson(bean));
                    }
                }
            }
        });

    }

    private void updateUI(AccuLocationBean bean) {

    }


}
