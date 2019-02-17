package com.weather.metoffice.PushNotification;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.onesignal.NotificationExtenderService;
import com.onesignal.OSNotificationDisplayedResult;
import com.onesignal.OSNotificationReceivedResult;
import com.weather.metoffice.R;
import com.weather.metoffice.WeatherMetOfficeApp;
import com.weather.metoffice.session.Preferences;

import org.json.JSONObject;

import java.math.BigInteger;


public class MyNotificationExtenderService extends NotificationExtenderService {
    Preferences preferences;

    @Override
    protected boolean onNotificationProcessing(OSNotificationReceivedResult receivedResult) {
        preferences = new Preferences(WeatherMetOfficeApp.getContext());
        JSONObject data = receivedResult.payload.additionalData;
        Boolean inApp = receivedResult.isAppInFocus;
        int notificationType = 0;
        if (data != null) {
            notificationType = data.optInt("notification_type");


            OverrideSettings overrideSettings = new OverrideSettings();
            overrideSettings.extender = new NotificationCompat.Extender() {
                @Override
                public NotificationCompat.Builder extend(NotificationCompat.Builder builder) {
                    // Sets the background notification color to Red on Android 5.0+ devices.
                    Bitmap icon = BitmapFactory.decodeResource(WeatherMetOfficeApp.getContext().getResources(), R.mipmap.ic_launcher);
                    builder.setLargeIcon(icon);

                    return builder.setColor(new BigInteger("FF0000FF", 16).intValue());
                }
            };

            OSNotificationDisplayedResult displayedResult = displayNotification(overrideSettings);
            Log.d("OneSignalExample", "Notification displayed with id: " + displayedResult.androidNotificationId);

        }
        return true;
    }

}