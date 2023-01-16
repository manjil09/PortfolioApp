package com.profile.manjilkoju;

import android.content.Context;
import android.content.SharedPreferences;

public class IntroSliderPref {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int MODE_PRIVATE = 0;
    private static final String PREF_NAME = "";
    private static final String IS_FIRST_TIME = "isFirstTime";
    Context context;

    public IntroSliderPref(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setIsFirstTime(boolean firstTime){
        editor.putBoolean(IS_FIRST_TIME, firstTime);
        editor.commit();
    }

    public boolean isFirstTime(){
        return sharedPreferences.getBoolean(IS_FIRST_TIME, true);
    }
}
