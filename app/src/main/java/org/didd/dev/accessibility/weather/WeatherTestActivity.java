package org.didd.dev.accessibility.weather;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import org.didd.dev.R;
import org.didd.dev.tools.App;
import org.didd.dev.tools.Utils;

public class WeatherTestActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_test);
    }

    public void onClickStartTest(View view) {

        if (Utils.isExists(this, YoHelper.weather)) {
            App.uninstallApp(this, YoHelper.weather);
        } else {
            App.installAppFile(this, YoHelper.appApk);
        }
//        App.launchApp(this, YoHelper.weather);
    }
}
