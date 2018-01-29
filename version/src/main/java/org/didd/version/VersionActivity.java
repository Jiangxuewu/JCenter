package org.didd.version;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.transsnet.version.R;

import org.didd.common.log.L;

import java.util.List;

public class VersionActivity extends AppCompatActivity implements DialogInterface.OnKeyListener, DialogInterface.OnCancelListener, View.OnClickListener {

    private static final String TAG = VersionApi.class.getSimpleName();
    private static final boolean debug = true;

    private Context mContext;
    private VersionBean data;
    private String mAppName;
    private AlertDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        if (null != getIntent())
            handleIntent(getIntent());
    }

    @Override
    protected void onResume() {
        super.onResume();
        VersionApi.mResumed = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        VersionApi.mResumed = false;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(getIntent());
    }

    private void handleIntent(Intent intent) {

        if (null == intent) {
            finish();
            return;
        }

        mAppName = intent.getStringExtra("appName");

        data = (VersionBean) intent.getSerializableExtra("versionBean");

        if (null == data) {
            finish();
        }

        showDialog(data);
    }

    private void showDialog(VersionBean data) {
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
            tip = "Update";
            updateBtn.setText("");
        } else {
            cancelBtn.setOnClickListener(this);
            tip = "Try it now";
        }
        updateBtn.setText(tip);
        updateBtn.setOnClickListener(this);
    }


    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        return true;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        finish();
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void onClick(View v) {
        if (debug) L.d(TAG, "onClick, ");
        if (v.getId() == R.id.version_btn_cancel) {
            if (!"1".equals(data.getUpgradeStrategy())) {
                dismiss();
            }
        } else if (v.getId() == R.id.version_btn_update) {
            update();
        }
    }

    private void dismiss() {
        if (debug) L.d(TAG, "dismiss, ");
        try {
            if (null != mDialog) {
                mDialog.dismiss();
            }
        } catch (Exception ignored) {
        }
        finish();
    }

    private void update() {
        if (debug) L.d(TAG, "update, ");
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
        if (debug) L.d(TAG, "toBrowser, url = " + url);
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
        if (debug) L.d(TAG, "toMarket, url = " + url);
        try {
            startGooglePlay(url);
        } catch (Exception ignored) {
        }
    }

    private boolean isMarketUrl(String url) {
        if (debug) L.d(TAG, "isMarketUrl, url = " + url);
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

    private String getAppName() {

        return mAppName;
    }

}
