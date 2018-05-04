package com.example.asd.com.healthcare;

/**
 * Created by Assaduzzaman Noor on 4/29/2016.
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;



public class RemainderNotifications extends BroadcastReceiver {
    private int MY_NOTIFICATION_ID=1;
    NotificationManager notificationManager;
    Notification myNotification;


    @Override
    public void onReceive(final Context context, Intent intent) {
        String msg=intent.getExtras().getString("msg");


        myNotification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Family Health Care Remainder")
                .setContentText(msg)
                .setTicker("Notification")
                .setDefaults(Notification.DEFAULT_SOUND)
                .setAutoCancel(true)
                .build();

        notificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(MY_NOTIFICATION_ID, myNotification);


    }
}
