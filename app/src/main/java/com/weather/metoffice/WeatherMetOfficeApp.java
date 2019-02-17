package com.weather.metoffice;

import android.app.Activity;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.onesignal.OneSignal;
import com.weather.metoffice.PushNotification.MyNotificationOpenedHandler;
import com.weather.metoffice.PushNotification.MyNotificationReceivedHandler;
import com.weather.metoffice.api.ApiRequestHelper;
import com.weather.metoffice.db.WeatherDataDbManager;
import com.weather.metoffice.session.Preferences;


public class WeatherMetOfficeApp extends MultiDexApplication {

    private static Context context;
    private static WeatherMetOfficeApp mInstance;
    private Preferences preferences;
    private ApiRequestHelper apiRequestHelper;
    private Activity mCurrentActivity = null;

    public static synchronized WeatherMetOfficeApp getInstance() {
        return mInstance;
    }

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        MultiDex.install(this);

        WeatherDataDbManager.init(this);

        doInit();
    }

    public Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    public void setCurrentActivity(Activity mCurrentActivity) {
        this.mCurrentActivity = mCurrentActivity;
    }

    private void doInit() {
        context = getApplicationContext();
        this.preferences = new Preferences(this);
//        this.apiRequestHelper = ApiRequestHelper.init(this);
//        MultiDex.install(this);

        OneSignal.startInit(this)
                .setNotificationOpenedHandler(new MyNotificationOpenedHandler(context))
                .setNotificationReceivedHandler(new MyNotificationReceivedHandler(context))
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .init();

    }

    //
    public synchronized ApiRequestHelper getApiRequestHelper() {
        return apiRequestHelper;
    }

    public synchronized Preferences getPreferences() {
        return preferences;
    }


}
