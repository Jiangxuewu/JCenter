package org.didd.dev.version;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.didd.version.VersionApi;

import org.didd.dev.BuildConfig;
import org.didd.dev.R;

public class VersionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version);
//        String appName = getResources().getString(R.string.app_name);
//        String httpUrl = "http://test-yogame.palmplaystore.com/api/upGrade/upGradeManage/checkUpGrade";
//        VersionApi.getInstance().init(this, httpUrl, appName, "google", BuildConfig.VERSION_NAME);
//        finish();
    }
}
