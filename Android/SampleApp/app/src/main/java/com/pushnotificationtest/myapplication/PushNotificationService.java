package com.pushnotificationtest.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationManagerCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class PushNotificationService  extends FirebaseMessagingService {

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
    }

    final String CHANNEL_NOTI_ID="PushChannel";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {

        Log.i("NotificationDate",remoteMessage.getNotification().toString());

        String title= remoteMessage.getNotification().getTitle();
        String text= remoteMessage.getNotification().getBody();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            NotificationChannel channel= new NotificationChannel(
                    CHANNEL_NOTI_ID,
                    "NotificationsHere", NotificationManager.IMPORTANCE_LOW);
            channel.setDescription("channelDesc");
            channel.setShowBadge(false);
            channel.enableLights(true);
            getSystemService(NotificationManager.class).createNotificationChannel(channel);
            Notification.Builder notification= new Notification.Builder(this, CHANNEL_NOTI_ID)
                    .setContentTitle(title)
                    .setContentText(text)
                    .setAutoCancel(true)
                    .setSmallIcon(R.drawable.noti_icon)
                    .setVisibility(Notification.VISIBILITY_PUBLIC)
                    .setPriority(Notification.PRIORITY_LOW)
                    .setAutoCancel(true);

            NotificationManagerCompat.from(this).notify(1,notification.build());
            super.onMessageReceived(remoteMessage);
        }

    }

}
