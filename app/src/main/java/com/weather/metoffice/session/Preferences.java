package com.weather.metoffice.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import java.util.Set;

public class Preferences {

    private static final String LOGGED_IN_USER = "LOGGED_IN_USER";
    private static final String IS_AUTHENTICATED = "IS_AUTHENTICATED";
    private static final String REMEMBER_ME = "REMEMBER_ME";
    private static final String PROFILE_PIC = "PROFILE_PIC";
    private static final String MOBILE_NO = "MOBILE_NO";
    private static final String FULL_NAME = "FULL_NAME";
    Set<String> strings;
    private Context context;

    public Preferences(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    protected SharedPreferences getSharedPreferences(String key) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String getString(String key, String def) {
        SharedPreferences prefs = getSharedPreferences(key);
        String s = prefs.getString(key, def);
        return s;
    }

    public void setString(String key, String val) {
        SharedPreferences prefs = getSharedPreferences(key);
        Editor e = prefs.edit();
        e.putString(key, val);
        e.apply();
    }

    public boolean getBoolean(String key, boolean def) {
        SharedPreferences prefs = getSharedPreferences(key);
        return prefs.getBoolean(key, def);
    }

    public void setBoolean(String key, boolean val) {
        SharedPreferences prefs = getSharedPreferences(key);
        Editor e = prefs.edit();
        e.putBoolean(key, val);
        e.apply();
    }

    public boolean isLoggedInUser() {
        String json = getString(LOGGED_IN_USER, null);
        return json != null;
    }

    public void logOutUser() {
        SharedPreferences prefs = getSharedPreferences(LOGGED_IN_USER);
        Editor e = prefs.edit();
        e.remove(LOGGED_IN_USER);
        e.apply();
    }


    private Set<String> getStringSet(String key, Set<String> def) {
        SharedPreferences prefs = getSharedPreferences(key);
        return prefs.getStringSet(key, def);
    }

    public void setStringSet(String key, Set<String> val) {
        SharedPreferences prefs = getSharedPreferences(key);
        Editor e = prefs.edit();
        e.putStringSet(key, val);
        e.apply();
    }

    public String getRememberMe() {
        return getString(REMEMBER_ME, "");
    }

    public void setRememberMe(String email) {
        setString(REMEMBER_ME, email);
    }

    public String getProfilePic() {
        return getString(PROFILE_PIC, "");
    }

    public void setProfilePic(String pic) {
        setString(PROFILE_PIC, pic);
    }

    public String getMobileNo() {
        return getString(MOBILE_NO, "");
    }

    public void setMobileNo(String no) {
        setString(MOBILE_NO, no);
    }

    public String getFullName() {
        return getString(FULL_NAME, "");
    }

    public void setFullName(String name) {
        setString(FULL_NAME, name);
    }

    public void setIsAuthenticated(boolean isRegisteredToApplozic) {
        setBoolean(IS_AUTHENTICATED, isRegisteredToApplozic);
    }

    public boolean isAuthenticated() {
        return getBoolean(IS_AUTHENTICATED, false);
    }
}
