package org.didd.dev;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.bbsz.mlibrary.permissions.PermissionUtil;

import org.didd.dev.accessibility.weather.WeatherTestActivity;
import org.didd.dev.template.BaseActivity;
import org.didd.dev.template.ItemListActivity;
import org.didd.dev.template.LoginActivity;
import org.didd.dev.template.NavigationDrawerActivity;
import org.didd.dev.template.ScrollingActivity;
import org.didd.dev.template.SettingsActivity;
import org.didd.dev.template.TabbedMainActivity;
import org.didd.dev.weather.WeatherActivity;
import org.didd.dev.weatheraccu.AccuWeatherActivity;

public class LauncherActivity extends AppCompatActivity {

    private static Class<?>[] cls = new Class<?>[]{BaseActivity.class, LoginActivity.class, ItemListActivity.class,
            NavigationDrawerActivity.class, ScrollingActivity.class, SettingsActivity.class, TabbedMainActivity.class,
            GoogleAdMobActivity.class, WeatherActivity.class, AccuWeatherActivity.class, WeatherTestActivity.class};

    private PermissionUtil permissionUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ListViewCompat listView = (ListViewCompat) findViewById(R.id.list_view);
        setSupportActionBar(toolbar);

        MainAdapter mainAdapter = new MainAdapter();

        listView.setAdapter(mainAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LauncherActivity.this, cls[position]);
                LauncherActivity.this.startActivity(intent);
            }
        });


        permissionUtil = new PermissionUtil();

        permissionUtil.requestPermission(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, new PermissionUtil.OnCheckPermissionCallback() {
            @Override
            public void requestPermissionSuccess() {

            }

            @Override
            public void requestPermissionFailed() {

            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (null != permissions)
            permissionUtil.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    class MainAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return cls.length;
        }

        @Override
        public Class<?> getItem(int position) {
            return cls[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (null == convertView) {
                convertView = new TextView(LauncherActivity.this);
                convertView.setLayoutParams(new AbsListView.LayoutParams(-1, LauncherActivity.this.getResources().getDimensionPixelSize(R.dimen.item_height_launcher)));
                ((TextView) convertView).setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
            }

            if (convertView instanceof TextView) {
                ((TextView) convertView).setText(getItem(position).getSimpleName());
            }
            return convertView;
        }
    }
}
