package com.example.palida.beacon_reminder.Helper;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.palida.beacon_reminder.DBHelper;
import com.example.palida.beacon_reminder.Item;
import com.example.palida.beacon_reminder.MainActivity;
import com.example.palida.beacon_reminder.R;
import com.example.palida.beacon_reminder.altBeacon.BeaconReferenceApplication;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import static android.content.Context.ALARM_SERVICE;


public class AlarmReceiver extends BroadcastReceiver {
    private static final String TAG = AlarmReceiver.class.getName();

    // if go out & reach alarm time &
    @Override
    public void onReceive(Context context, Intent intent) {
        int requestCode = intent.getExtras().getInt("requestCode");
        Log.i("Alarm", "Alert : "+requestCode);
        DBHelper dbHelper = new DBHelper(context);
        List<Item> items = dbHelper.getAllBeacons();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setAutoCancel(true)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.icon)
                .setContentTitle(items.get(requestCode).getName())
                .setContentText(items.get(requestCode).getLabel())
                .setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.shape_of_you))
                .setVibrate(new long[]{500, 500, 500, 500, 500, 500, 500, 500, 500});

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());

        // For our recurring task, we'll just display a message
//        int requestCode = intent.getExtras().getInt("requestCode");
//        Log.i("Alarm", "Alert : "+requestCode);
//        Toast.makeText(context, "Alert : " + requestCode, Toast.LENGTH_SHORT).show();
    }
}