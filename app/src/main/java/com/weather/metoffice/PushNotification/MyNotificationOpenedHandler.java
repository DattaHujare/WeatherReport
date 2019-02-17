package com.weather.metoffice.PushNotification;

import android.content.Context;
import android.util.Log;

import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;
import com.weather.metoffice.session.Preferences;
import com.weather.metoffice.utils.Utils;

import org.json.JSONObject;


public class MyNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
    private Context context;
    private Preferences preferences;

    public MyNotificationOpenedHandler(Context context) {
        this.context = context;
        preferences = new Preferences(context);
    }
    // This fires when a notification is opened by tapping on it.

    @Override
    public void notificationOpened(OSNotificationOpenResult result) {
        OSNotificationAction.ActionType actionType = result.action.type;
        JSONObject data = result.notification.payload.additionalData;
        int notificationType = 0;
        if (preferences.isLoggedInUser()) {
            if (data != null) {

                notificationType = data.optInt("notification_type");

                if (result.notification.displayType.name().equalsIgnoreCase("InAppAlert")) {

                } else {
                   /* Intent intent = new Intent(WeatherMetOfficeApp.getContext(), NotificationActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                    WeatherMetOfficeApp.getContext().startActivity(intent);*/

                }

            }

            //If we send notification with action buttons we need to specidy the button id's and retrieve it to
            //do the necessary operation.
            if (actionType == OSNotificationAction.ActionType.ActionTaken) {
                Log.i("OneSignalExample", "Button pressed with id: " + result.action.actionID);
                if (result.action.actionID.equals("ActionOne")) {
//                    Toast.makeText(OOCApp.getContext(), "ActionOne Button was pressed", Toast.LENGTH_LONG).show();
                } else if (result.action.actionID.equals("ActionTwo")) {
//                    Toast.makeText(OOCApp.getContext(), "ActionTwo Button was pressed", Toast.LENGTH_LONG).show();
                }
            }
        } else {
            Utils.showErrorToast(context, "Please Login in Weather MetOffice app");
        }
    }
}