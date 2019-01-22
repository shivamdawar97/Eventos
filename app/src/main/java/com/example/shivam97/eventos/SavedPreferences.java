package com.example.shivam97.eventos;

import android.content.Context;
import android.content.SharedPreferences;


public class SavedPreferences  {

    private String userID,token;
    private SharedPreferences preferences;


    public SavedPreferences(Context context) {
        preferences=context.getSharedPreferences("default",Context.MODE_PRIVATE);
        userID=preferences.getString("userID",null);
        token=preferences.getString("token",null);
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("userID",userID);
        editor.apply();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("token",token);
        editor.apply();
    }
}
