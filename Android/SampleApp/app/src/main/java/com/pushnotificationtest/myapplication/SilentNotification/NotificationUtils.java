package com.pushnotificationtest.myapplication.SilentNotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.pushnotificationtest.myapplication.MainActivity;
import com.pushnotificationtest.myapplication.R;

import java.util.Random;


public class NotificationUtils {

    final String CHANNEL_NOTI_ID="PushChannel";
    Context context;
    public NotificationUtils(Context context) {
        this.context= context;
    }

    private void createChannel() {
        NotificationChannel channel= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            channel = new NotificationChannel(
                    CHANNEL_NOTI_ID,
                    "NotificationsHere", NotificationManager.IMPORTANCE_LOW);
            channel.setDescription("channelDesc");
            channel.setShowBadge(false);
            channel.enableLights(true);
            context.getSystemService(NotificationManager.class).createNotificationChannel(channel);
        }

    }
    public void showNotification(String title, String text) {
        Intent intent=new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);


        Notification.Builder notification= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(context, CHANNEL_NOTI_ID)
                    .setContentTitle(title)
                    .setContentText(text)
                    .setAutoCancel(true)
                    .setSmallIcon(R.drawable.noti_icon)
                    .setVisibility(Notification.VISIBILITY_PUBLIC)
                    .setPriority(Notification.PRIORITY_LOW)
                    .setOnlyAlertOnce(true)
                    .setAutoCancel(true);
        }

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
        notificationManager.notify(new Random().nextInt(), notification.build());
    }

}
