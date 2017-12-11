package org.didd.common.sp;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;
import java.util.Set;

/**
 * Created by Jiangxuewu on 2017/12/11.
 * <p>Shared preferences utils</p>
 */

public class SPPro {
    private SharedPreferences sp;

    private SPPro(Context context, String fileName) {
        init(context, fileName);
    }

    public static SPPro newInstance(Context context, String fileName) {
        return new SPPro(context.getApplicationContext(), fileName);
    }

    private void init(Context context, String fileName) {
        sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

    public void setInt(String key, int value) {
        if (null == sp) return;
        sp.edit().putInt(key, value).apply();
    }

    public void setFloat(String key, float value) {
        if (null == sp) return;
        sp.edit().putFloat(key, value).apply();
    }

    public void setLong(String key, long value) {
        if (null == sp) return;
        sp.edit().putLong(key, value).apply();
    }

    public void setStringSet(String key, Set<String> values) {
        if (null == sp) return;
        sp.edit().putStringSet(key, values).apply();
    }

    public void setBoolean(String key, boolean value) {
        if (null == sp) return;
        sp.edit().putBoolean(key, value).apply();
    }

    public void setString(String key, String value) {
        sp.edit().putString(key, value).apply();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        if (null == sp) return defaultValue;
        return sp.getBoolean(key, defaultValue);
    }

    public String getString(String key, String defaultValue) {
        if (null == sp) return defaultValue;
        return sp.getString(key, defaultValue);
    }

    public int getInt(String key, int defaultValue) {
        if (null == sp) return defaultValue;
        return sp.getInt(key, defaultValue);
    }

    public float getFloat(String key, float defaultValue) {
        if (null == sp) return defaultValue;
        return sp.getFloat(key, defaultValue);
    }

    public long getLong(String key, long defaultValue) {
        if (null == sp) return defaultValue;
        return sp.getLong(key, defaultValue);
    }

    public Set<String> get(String key, Set<String> values) {
        return sp.getStringSet(key, values);
    }

    public Map getAll() {
        return sp.getAll();
    }
}
