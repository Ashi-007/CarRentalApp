package com.example.finalcarrental;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    private final String INTRO = "FullName";
    private final String NAME = "Email";
    private final String HOBBY = "Password";
    private SharedPreferences app_prefs;
    private Context context;



    public SharedPrefManager(Context context) {

        app_prefs = context.getSharedPreferences("shared",Context.MODE_PRIVATE);
        this.context= context;

    }

    public void PutIsLogin(boolean loginorout){

        SharedPreferences.Editor editor = app_prefs.edit();
        editor.putBoolean(INTRO,loginorout);
        editor.commit();

    }

    public boolean getIsLogin(){
        return app_prefs.getBoolean(INTRO,false);

    }


    public void putName(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(NAME, loginorout);
        edit.commit();
    }
    public String getName() {
        return app_prefs.getString(NAME, "");
    }

    public void putHobby(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(HOBBY, loginorout);
        edit.commit();
    }
    public String getHobby() {
        return app_prefs.getString(HOBBY, "");
    }

}
