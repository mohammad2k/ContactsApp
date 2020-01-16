package com.example.mycontacts;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class MyPreferenceManager {

    private static MyPreferenceManager instance = null;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private MyPreferenceManager(Context context){
        sharedPreferences = context.getSharedPreferences("my_preference",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static MyPreferenceManager getInstance(Context context){
        if (instance == null){
            instance = new MyPreferenceManager(context);
        }
        return instance;
    }
    //================================================================



}
