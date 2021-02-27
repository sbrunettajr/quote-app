package br.com.sbrunettajr.quote.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Preference {

    private static final String PREF_ID = "QUOTE";

    private static final int DEFAULT_UPDATE_TIME = 30;

    public static void setInteger(Context context, String key, int value) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_ID, 0);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt(key, value);
        editor.commit();
    }

    public static int getInteger(Context context, String key) {
        return context.getSharedPreferences(PREF_ID, 0).getInt(key, DEFAULT_UPDATE_TIME);
    }

    public enum PreferenceName {
        PREF_UPD_TYPE
    };

}
