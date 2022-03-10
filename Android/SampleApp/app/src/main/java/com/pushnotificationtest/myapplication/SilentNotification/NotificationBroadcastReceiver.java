package com.pushnotificationtest.myapplication.SilentNotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.pushnotificationtest.myapplication.ScheduleWork;

public class NotificationBroadcastReceiver extends BroadcastReceiver {
    String NOTIFICATION_TITLE = "notification_title";
    String NOTIFICATION_MESSAGE = "notification_message";
    @Override
    public void onReceive(Context context, Intent intent) {
        String title= intent.getStringExtra(NOTIFICATION_TITLE);
        String text = intent.getStringExtra(NOTIFICATION_MESSAGE);

        Data data  = new Data.Builder()
                .putString(NOTIFICATION_TITLE, title)
                .putString(NOTIFICATION_MESSAGE, text)
                .build();

        OneTimeWorkRequest workRequest= new OneTimeWorkRequest.Builder(ScheduleWork.class).setInputData(data).build();

        WorkManager.getInstance().beginWith(workRequest).enqueue();
        Log.d("WorkerQueue", "WorkManager is Enqueued.");
    }
}
