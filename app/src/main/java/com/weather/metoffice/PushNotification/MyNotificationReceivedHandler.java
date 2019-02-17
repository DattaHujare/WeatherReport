package com.weather.metoffice.PushNotification;

import android.content.Context;
import android.util.Log;

import com.onesignal.OSNotification;
import com.onesignal.OneSignal;
import com.weather.metoffice.WeatherMetOfficeApp;
import com.weather.metoffice.session.Preferences;
import com.weather.metoffice.utils.ConnectionDetector;

import org.json.JSONObject;


//This will be called when a notification is received while your app is running.
public class MyNotificationReceivedHandler implements OneSignal.NotificationReceivedHandler {

    private Context context;
    private Preferences preferences;
    private ConnectionDetector cd;
    private WeatherMetOfficeApp weatherMetOfficeApp;

    public MyNotificationReceivedHandler(Context context) {
        this.context = context;
        preferences = new Preferences(context);
        cd = new ConnectionDetector(context);
        weatherMetOfficeApp = ((WeatherMetOfficeApp) context.getApplicationContext());

    }

    @Override
    public void notificationReceived(OSNotification notification) {
        JSONObject data = notification.payload.additionalData;
        String notificationId = notification.payload.notificationID;
        String notificationTitle = notification.payload.title;
        String notificationBody = notification.payload.body;
        String customKey, appId = "";
        int notificationType = 0, restId = 0;
        if (data != null) {
            //While sending a Push notification from OneSignal dashboard

            customKey = data.optString("customkey", null);
            if (customKey != null)
                Log.i("OneSignalExample", "customkey set with value: " + customKey);
        }
    }
}