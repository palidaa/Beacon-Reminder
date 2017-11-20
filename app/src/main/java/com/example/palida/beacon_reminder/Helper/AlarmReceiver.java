package com.example.palida.beacon_reminder.Helper;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.palida.beacon_reminder.MainActivity;
import com.example.palida.beacon_reminder.R;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;


public class AlarmReceiver extends BroadcastReceiver {
    private static final String TAG = AlarmReceiver.class.getName();

    // if go out & reach alarm time &
    @Override
    public void onReceive(Context context, Intent intent) {
//
//        Intent i = new Intent(context, MainActivity.class);
//        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(i);
//        Log.i(TAG, "onReceive");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Alarm actived!")
                .setContentText("THIS IS LABEL")
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
                .setContentInfo("info");

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,builder.build());

        // For our recurring task, we'll just display a message
//        int requestCode = intent.getExtras().getInt("requestCode");
//        Log.i("Alarm", "Alert : "+requestCode);
//        Toast.makeText(context, "Alert : " + requestCode, Toast.LENGTH_SHORT).show();
    }

//

}