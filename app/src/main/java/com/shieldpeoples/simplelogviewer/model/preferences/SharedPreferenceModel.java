package com.shieldpeoples.simplelogviewer.model.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Shahanur on 1/12/2017.
 */

public class SharedPreferenceModel {
    SharedPreferences preferences;

    public SharedPreferenceModel(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String get(String key, String defaultValue) {
        return preferences.getString(key, defaultValue);
    }

    public boolean set(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public long get(String key, long defaultValue) {
        return preferences.getLong(key, defaultValue);
    }

    public boolean set(String key, long value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key, value);
        return editor.commit();
    }
}
