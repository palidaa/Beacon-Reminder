package com.example.palida.beacon_reminder.Helper;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;


public class AlarmManagers {
    private static final String TAG = AlarmManagers.class.getName();
    private Context mContext;
    private AlarmManager mAlarmManager;

    public AlarmManagers(Context context) {
        mContext = context;
        mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }


    public void setAlarm(Context mContext, int timeHour, int timeMinute, int requestCode, int repeat) {
        mAlarmManager = (android.app.AlarmManager) mContext.getSystemService(ALARM_SERVICE);
        Intent myIntent = new Intent(mContext, AlarmReceiver.class);
        myIntent.putExtra("requestCode",requestCode);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, requestCode, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, timeHour);
        calendar.set(Calendar.MINUTE, timeMinute);

        if (repeat == 0) {
            mAlarmManager.setExact(android.app.AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        } else {
            calendar.set(Calendar.DAY_OF_WEEK, repeat);
            mAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(), 24 * 60 * 60 * 1000, pendingIntent);
        }
        Log.e("Alarm", requestCode + " - " + timeHour+ " : " + timeMinute + " - " + repeat);

    }

    public void cancelAlarm(Context mContext, int requestCode) {
        mAlarmManager = (android.app.AlarmManager) mContext.getSystemService(ALARM_SERVICE);
        if (mAlarmManager != null) {
            Intent myIntent = new Intent(mContext, AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, requestCode, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            mAlarmManager.cancel(pendingIntent);
            Log.e("Alarm", "cancel "+requestCode);

        }
    }

}