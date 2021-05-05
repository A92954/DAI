package com.example.dai.Backend;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME ="session";
    String SESSION_KEY = "session_key";
    String EMAIL = "email";
    String ID_CHILD = "id_child";

    public SessionManagement(Context context){
        sharedPreferences= context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(User user,  Children ch){
        int id_user = user.getId_user();
        String email = user.getE_mail();
        int id_child = ch.getId_child();

        editor.putInt(SESSION_KEY, id_user).commit();
        editor.putString(EMAIL, email).commit();
        editor.putInt(ID_CHILD, id_child).commit();
    }

    //RETURN USER ID SESSION SAVED
    public int getSession(){
        return sharedPreferences.getInt(SESSION_KEY, -1);
    }

    public String getEMAIL() {
        return sharedPreferences.getString(EMAIL, "");
    }

    public int getID_CHILD() {
        return sharedPreferences.getInt(ID_CHILD, -1);
    }

    //LOGOUT AND REMOVE SESSION
    public void removeSession(){
        editor.putInt(SESSION_KEY, -1).commit();
        editor.putString(EMAIL, "").commit();
        editor.putInt(ID_CHILD, -1).commit();
    }
}
