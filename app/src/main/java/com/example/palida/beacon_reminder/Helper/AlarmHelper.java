package com.example.palida.beacon_reminder.Helper;

import android.app.NotificationManager;
import android.content.Context;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.example.palida.beacon_reminder.DBHelper;
import com.example.palida.beacon_reminder.Item;
import com.example.palida.beacon_reminder.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by orraphan on 11/20/2017.
 */

public class AlarmHelper {
    private static final String TAG = AlarmHelper.class.getName();
    SimpleDateFormat inputParser = new SimpleDateFormat(inputFormat, Locale.US);
    public static final String inputFormat = "HH:mm";

    private Context context;

    public AlarmHelper(Context context) {
        this.context = context.getApplicationContext();
    }

    public void checkInAlarmPeriod(List<Item> items) {
        List<Item> mustAlertItem = new ArrayList<Item>();
        Log.e("AlarmHelper", " " + items.size());

        for (Item e : items) {
            Log.e("AlarmHelper", "enter : " + e.getBeacon_uuid());
        }

        for (Item each : items) {
            if (each.getChecked()==1 && isInRangeTime(each) && isOnRepeatDate(each)) {
                mustAlertItem.add(each);
            }
        }
        if(mustAlertItem.size()>0) {
            Log.e("AlarmHelper", "Alet to meennnnnnnnnnnnnn : ");
            notification(mustAlertItem);
        }

//        for (Item e: mustAlertItem) {
//            Log.e("AlarmHelper","check out : "+ e.getBeacon_uuid());
//        }
//        Log.e("AlarmHelper","----------------------------------------------------------");
        // send to meen


    }

    private boolean isInRangeTime(Item item) {
        Calendar now = Calendar.getInstance();

        int hour = now.get(Calendar.HOUR);
        int minute = now.get(Calendar.MINUTE);

        Date currentTime = parseDate(hour + ":" + minute);
        Date startTime = parseDate(item.getStart_time());
        Date endTime = parseDate(item.getEnd_time());

//        Log.e("AlarmHelper","currentTime : "+ currentTime);
//        Log.e("AlarmHelper","startTime : "+ startTime);
//        Log.e("AlarmHelper","endTime : "+ endTime);

        if (startTime.before(currentTime) && endTime.after(currentTime)) {
            Log.e("AlarmHelper", "True Time");
            return true;
        }
        return false;
    }

    private Date parseDate(String date) {
        try {
            return inputParser.parse(date);
        } catch (java.text.ParseException e) {
            return new Date(0);
        }
    }

    private boolean isOnRepeatDate(Item item) {
        int repeatDay = setRepeatDayOfWeekToInteger(item.getRepeat());
        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
//        Log.e("AlarmHelper","repeatDay : "+ repeatDay);
//        Log.e("AlarmHelper","currentDay : "+ currentDay);

        if (repeatDay == currentDay) {
            Log.e("AlarmHelper", "True Date");
            return true;
        }
        return false;
    }

    private int setRepeatDayOfWeekToInteger(String repeatDayOfWeek) {
        int dayOfWeek = 0;
        switch (repeatDayOfWeek) {
            case "Never":
                dayOfWeek = 0;
                break;
            case "Every Sunday":
                dayOfWeek = 1;
                break;
            case "Every Monday":
                dayOfWeek = 2;
                break;
            case "Every Tuesday":
                dayOfWeek = 3;
                break;
            case "Every Wednesday":
                dayOfWeek = 4;
                break;
            case "Every Thursday":
                dayOfWeek = 5;
                break;
            case "Every Friday":
                dayOfWeek = 6;
                break;
            case "Every Saturday":
                dayOfWeek = 7;
                break;
        }
        return dayOfWeek;
    }

    private void notification(List<Item> items) {
        int notificationId = 0;
//        Log.e("AlarmHelper", "Call meennnnnnnnnnnnnn : ");
        NotificationCompat.InboxStyle inboxStyle =
                new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle("You forget : ");
        for (Item item : items) {
            inboxStyle.addLine(item.getName());
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setAutoCancel(true)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.icon)
                    .setContentTitle("Alarm actived!")
                    .setContentText("Slide here to see which thing you forgot...")
                    .setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.shape_of_you))
                    .setVibrate(new long[]{500, 500, 500, 500, 500, 500, 500, 500, 500})
                    .setContentInfo("info")
                    .setVisibility(android.support.v4.app.NotificationCompat.VISIBILITY_PUBLIC)
                    .setStyle(inboxStyle);

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(notificationId++, builder.build());

//        for (Item i : items) {
//            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
//            builder.setAutoCancel(true)
//                    .setWhen(System.currentTimeMillis())
//                    .setSmallIcon(R.drawable.icon)
//                    .setContentTitle("Alarm actived!")
//                    .setContentText("You forget : " + i.getName())
//                    .setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.shape_of_you))
//                    .setVibrate(new long[]{500, 500, 500, 500, 500, 500, 500, 500, 500})
//                    .setContentInfo("info")
//                    .setVisibility(android.support.v4.app.NotificationCompat.VISIBILITY_PUBLIC);
//
//            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//            notificationManager.notify(notificationId++, builder.build());
//        }
    }


}
