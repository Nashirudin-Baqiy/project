package id.co.rsnasionaldiponegoro.epublic.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import id.co.rsnasionaldiponegoro.epublic.Model.Models.User;
import id.co.rsnasionaldiponegoro.epublic.Model.Models.PendaftaranLogin;

/**
 * Created by xsanz on 9/18/2018.
 */

public class Session {
    private SharedPreferences sharedPreferences;

    public Session(Context context) {
        sharedPreferences = context.getSharedPreferences("Session", 0);
    }

    /*String*/
    public void setSessionString(String name, String value) {
        sharedPreferences.edit().putString(name, value).apply();
    }

    public String getSessionString(String name, String value) {
        return sharedPreferences.getString(name, value);
    }

    /*Boolean*/
    public void setSessionBoolean(String name, Boolean value) {
        sharedPreferences.edit().putBoolean(name, value).apply();
    }

    public Boolean getSessionBoolean(String name, Boolean value) {
        return sharedPreferences.getBoolean(name, value);
    }

    /*Integer*/
    public void setSessionInteger(String name, Integer value) {
        sharedPreferences.edit().putInt(name, value).apply();
    }

    public Integer getSessionInteger(String name, Integer value) {
        return sharedPreferences.getInt(name, value);
    }

    /*Long*/
    public void setSessionLong(String name, Long value) {
        sharedPreferences.edit().putLong(name, value).apply();
    }

    public Long getSessionLong(String name, Long value) {
        return sharedPreferences.getLong(name, value);
    }

    /**
     * User
     **/
    public void setSessionUser(User user) {
        Gson gson = new Gson();
        String json = gson.toJson(user);
        sharedPreferences.edit().putString("user", json).apply();
    }

    public User getSessionUser() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString("user", null);
        Type type = new TypeToken<User>() {
        }.getType();
        User user = gson.fromJson(json, type);
        return user;
    }


    /**
     * User
     **/
    public void setSessionUserPend(PendaftaranLogin userPend) {
        Gson gson = new Gson();
        String json = gson.toJson(userPend);
        sharedPreferences.edit().putString("userPend", json).apply();
    }

    public PendaftaranLogin getSessionUserPend() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString("userPend", null);
        Type type = new TypeToken<PendaftaranLogin>() {
        }.getType();
        PendaftaranLogin userPend = gson.fromJson(json, type);
        return userPend;
    }


    /************************ Command *********************************/
    /* Clear Session*/
    public void clearSession() {
        sharedPreferences.edit().clear().commit();
    }

    /** User **/
//    public void setSessionUserPrimer(UserPrimer userPrimer){
//        Gson gson   = new Gson();
//        String json = gson.toJson(userPrimer);
//        sharedPreferences.edit().putString("UserPrimer",json).apply();
//    }
//
//    public UserPrimer getSessionUserPrimer(){
//        Gson gson   = new Gson();
//        String json = sharedPreferences.getString("UserPrimer",null);
//        Type type   = new TypeToken<UserPrimer>(){}.getType();
//        UserPrimer userPrimer   = gson.fromJson(json,type);
//        return userPrimer;
//    }
}
