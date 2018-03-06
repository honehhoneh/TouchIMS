package com.mendoza.touchims.utilities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.mendoza.touchims.models.User;
import com.mendoza.touchims.views.LoginActivity;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "touchims_sharedpref";
    private static final String KEY_FIRSTNAME = "firstName";
    private static final String KEY_LASTNAME = "lastName";
    private static final String KEY_IDNUM = "idNum";
    private static final String KEY_CLASSIFICATION = "classification";
    private static final String KEY_COLLEGE = "college";

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context){
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context){
        if(mInstance == null){
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public boolean userLogin(User user){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_FIRSTNAME, user.getFirstName());
        editor.putString(KEY_LASTNAME, user.getLastName());
        editor.putInt(KEY_IDNUM, user.getIdNum());
        editor.putString(KEY_CLASSIFICATION, user.getClassification());
        editor.putString(KEY_COLLEGE, user.getCollege());
        editor.apply();

        return true;
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return (sharedPreferences.getInt(KEY_IDNUM, -1) != -1);
    }

    public User getUser(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString(KEY_FIRSTNAME, null),
                sharedPreferences.getString(KEY_LASTNAME, null),
                sharedPreferences.getInt(KEY_IDNUM, -1),
                sharedPreferences.getString(KEY_CLASSIFICATION, null),
                sharedPreferences.getString(KEY_COLLEGE, null));
    }

    public void logout(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, LoginActivity.class));
    }
}
