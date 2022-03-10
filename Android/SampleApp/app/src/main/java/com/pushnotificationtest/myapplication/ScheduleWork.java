package com.pushnotificationtest.myapplication;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.pushnotificationtest.myapplication.SilentNotification.NotificationUtils;

public class ScheduleWork extends Worker {

    String NOTIFICATION_TITLE = "notification_title";
    String NOTIFICATION_MESSAGE = "notification_message";

    public ScheduleWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d("TAG", "Work START");

        String  title = getInputData().getString(NOTIFICATION_TITLE);
        String message = getInputData().getString(NOTIFICATION_MESSAGE);


        new NotificationUtils(getApplicationContext()).showNotification(title, message);

        return Result.success();
    }
}
