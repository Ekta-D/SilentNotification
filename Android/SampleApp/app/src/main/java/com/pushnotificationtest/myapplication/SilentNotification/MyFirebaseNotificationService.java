package com.pushnotificationtest.myapplication.SilentNotification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyFirebaseNotificationService extends FirebaseMessagingService {

     String NOTIFICATION_TITLE = "notification_title";
     String NOTIFICATION_MESSAGE = "notification_message";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
      //  if (!remoteMessage.getData().isEmpty()) {
            String title = remoteMessage.getNotification().getTitle();
            String text = remoteMessage.getNotification().getBody();


            //boolean isScheduled = Boolean.parseBoolean(remoteMessage.getData().get("isScheduled"));
//            if (isScheduled) {
//                String scheduleTime = remoteMessage.getData().get("scheduledTime");
//                scheduleAlarm(scheduleTime, title, text);
//            }
//            else {
              showNotification(title, text);
//            }

        }
//    }


    private void showNotification(String title, String text ) {
        new NotificationUtils(getApplicationContext()).showNotification(title, text);
    }
    private void scheduleAlarm(String scheduleTime, String title, String message) {

        AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        Intent alarmIntent = new Intent(getApplicationContext(), NotificationBroadcastReceiver.class);
        alarmIntent.putExtra(NOTIFICATION_TITLE, title);
        alarmIntent.putExtra(NOTIFICATION_MESSAGE, message);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, alarmIntent, 0);

        try {
            Date scheduleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                     .parse(scheduleTime);
            alarmManager.set(AlarmManager.RTC_WAKEUP,scheduleDate.getTime(),pendingIntent);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
