package com.example.mycontacts;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mycontacts.models.ModelCalls;
import com.example.mycontacts.models.ModelContacts;
import com.example.mycontacts.models.ModelFav;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

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


    public void putContactList(List<ModelContacts> contactList){

        Gson gson = new Gson();
        String contactListJson = gson.toJson(contactList);
        editor.putString("contact_list",contactListJson);
        editor.apply();

    }

    public List<ModelContacts> getContactList(){

        String contactListJson = sharedPreferences.getString("contact_list",null);
        Gson gson = new Gson();
        Type type = new TypeToken<List<ModelContacts>>() {}.getType();
        return gson.fromJson(contactListJson,type);

    }

    //===================================================================

    public void putFavList(ModelFav modelFav){
        Gson gson = new Gson();
        String favListJson = gson.toJson(modelFav , ModelFav.class);
        editor.putString("fav_list",favListJson);
        editor.apply();
    }

    public ModelFav getFavList(){
        String favListJson = sharedPreferences.getString("fav_list",null);
        if (favListJson == null){
            return new ModelFav();
        }
        Gson gson = new Gson();
        return gson.fromJson(favListJson,ModelFav.class);
    }
}
