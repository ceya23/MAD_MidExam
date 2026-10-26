package com.example.week04;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {
    static final String KEY_NAME = "name";
    static final String KEY_DARK = "darkMode";

    private static SharedPreferences getSharedPreference(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setName(Context context, String name) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_NAME, name);
        editor.apply();
    }

    public static String getName(Context context) {
        return getSharedPreference(context).getString(KEY_NAME, "Guest");  // default to Guest
    }
    public static void setIsDarkMode(Context context, boolean isDarkMode) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putBoolean(KEY_DARK, isDarkMode);
        editor.apply();
    }

    public static boolean getIsDarkMode(Context context) {
        return getSharedPreference(context).getBoolean(KEY_DARK, true);
    }

    public static void clearData(Context context) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.remove(KEY_NAME);
        editor.remove(KEY_DARK);
        editor.apply();
    }
}
