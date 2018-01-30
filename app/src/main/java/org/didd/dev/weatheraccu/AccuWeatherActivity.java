package org.didd.dev.weatheraccu;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

import com.google.gson.Gson;

import org.didd.common.log.L;
import org.didd.common.permission.PermissionUtil;
import org.didd.dev.BuildConfig;
import org.didd.dev.R;
import org.didd.dev.service.MyLocation;
import org.didd.dev.service.MyLocationService;
import org.didd.dev.weatheraccu.response.AccuLocationBean;
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
            if (BuildConfig.DEBUG) L.i(TAG, "action:" + action);
            if (MyLocationService.ACTION_LOCATION_UPDATE.equals(action)) {
                Serializable data = intent.getSerializableExtra(MyLocationService.KEY_LOCATION_UPDATE);

                if (null != data && data instanceof MyLocation) {
                    updateWeather((MyLocation) data);
                }

            }

        }
    };
    private TextView textView;

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
            public void requestPermissionFailed(boolean isTip, String permission) {

            }

        });

        registerLocationReceiver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterLocationReceiver();
    }

    private void addText(String txt) {
        if (null == textView) {
            textView = findViewById(R.id.text);
        }
        if (!TextUtils.isEmpty(textView.getText())) {
            textView.setText(txt);
        } else {
            textView.setText(textView.getText() + "\n" + txt);
        }
    }

    private void setText(String txt) {
        if (null == textView) {
            textView = findViewById(R.id.text);
        }
        textView.setText(txt);
    }

    private void stopLocationService() {
//        stopService()
        if (BuildConfig.DEBUG) L.i(TAG, "stopLocationService");
    }

    private void startLocationService() {
        if (BuildConfig.DEBUG) L.i(TAG, "startLocationService");
        startService(new Intent(this, MyLocationService.class));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (null != permissionUtil)
            permissionUtil.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }


    private void registerLocationReceiver() {
        IntentFilter intent = new IntentFilter(MyLocationService.ACTION_LOCATION_UPDATE);

        registerReceiver(locationReceiver, intent);

    }

    private void unregisterLocationReceiver() {
        unregisterReceiver(locationReceiver);
    }


    private void updateWeather(MyLocation data) {

        if (BuildConfig.DEBUG)
            L.i(TAG, "updateWeather:" + (null == data ? "null" : new Gson().toJson(data)));

        AccuWeatherApi api = new AccuWeatherApi();

        api.locations(getApplicationContext(), data, new IHttpCallback() {
            @Override
            public void result(HttpResponse httpResponse) {

                AccuLocationBean bean = AccuWeatherApi.parseJsonToObject(httpResponse, AccuLocationBean.class);
                if (null != bean) {
                    updateUI(bean);
                    if (BuildConfig.DEBUG) {
                        L.d(TAG, "bean:" + new Gson().toJson(bean));
                    }
                }
            }
        });

    }

    private void updateUI(final AccuLocationBean bean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                setText(new Gson().toJson(bean));
                setText(bean.getKey());
            }
        });
    }


}
