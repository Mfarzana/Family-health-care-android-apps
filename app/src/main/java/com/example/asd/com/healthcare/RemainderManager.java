package com.example.asd.com.healthcare;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import java.util.Calendar;


/**
 * Created by Assaduzzaman Noor on 4/28/2016.
 */


public class RemainderManager {
    Context context,BaseContex;
    NotificationManager notificationManager;
    int alarmId;
    String msg;
    final static int RQS_1 = 1;

    public RemainderManager(Context context,Context BaseContex,String msg) {
        this.context = context;
        this.BaseContex=BaseContex;
        this.msg=msg;

    }


    public void setRemainder(int year,int month,int day,int hour,int minite,int alarmId){
        Calendar cal = Calendar.getInstance();
        this.alarmId = alarmId;
        cal.set(year, month, day, hour, minite, 00);
        setAlarm(cal);
    }
    public void deleteRemainder(int alarmId){
        this.alarmId = alarmId;
        cencelAlarm();
    }
    public void updateRemainder(int year,int month,int day,int hour,int minite,int alarmId){
        Calendar cal = Calendar.getInstance();
        this.alarmId = alarmId;
        cal.set(year, month, day, hour, minite, 00);
        updateAlarm(cal);
    }

    private void setAlarm(Calendar targetCal){
        Intent intent = new Intent(BaseContex, RemainderNotifications.class);
        intent.putExtra("msg",msg);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(BaseContex, alarmId, intent, 0);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
    }

    private void cencelAlarm(){
        Intent intent = new Intent(BaseContex, RemainderNotifications.class);
        intent.putExtra("msg",msg);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(BaseContex, alarmId, intent, 0);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);

    }

    private void updateAlarm(Calendar targetCal){
        cencelAlarm();
        Intent intent = new Intent(BaseContex, RemainderNotifications.class);
        intent.putExtra("msg",msg);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(BaseContex, alarmId, intent, 0);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);

    }





}
