package com.example.palida.beacon_reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import org.altbeacon.beacon.Beacon;
import java.util.Collection;
import com.example.palida.beacon_reminder.Helper.AlarmManagers;
import java.util.List;

/**
 * Created by Palida on 13-Nov-17.
 */

public class CustomAdapterAlarm extends BaseAdapter{
    Context mContext;
    private List<Item> items;

    public CustomAdapterAlarm(Context context, List<Item> items) {
        this.mContext= context;
        this.items = items;

    }


    public int getCount() {
        return items.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater mInflater =
                (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null)
            view = mInflater.inflate(R.layout.singlerow_alarm, parent, false);

        TextView textView = (TextView)view.findViewById(R.id.name);
        textView.setText(items.get(position).getName());

        ImageView imageView = (ImageView)view.findViewById(R.id.pic);
        imageView.setBackgroundResource(items.get(position).getPic());

        Switch switch_alarm = (Switch) view.findViewById(R.id.switch1);
        switch_alarm.setChecked(items.get(position).getChecked()==1?true:false);

        switch_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.get(position).setChecked(items.get(position).getChecked()==1?0:1);
                DBHelper dbHelper = new DBHelper(mContext);
                dbHelper.updateBeacon(items.get(position));

                AlarmManagers alarmManagers = new AlarmManagers(mContext);

                if(items.get(position).getChecked()==1){
                    String startTime = items.get(position).getStart_time();
                    String[] time = startTime.split(":");
                    int hour = Integer.parseInt(time[0].trim());
                    int min = Integer.parseInt(time[1].trim());
                    alarmManagers.setAlarm(mContext,hour,min,position,setRepeatDayOfWeekToInteger(items.get(position).getRepeat()));
                }
                else{
                    alarmManagers.cancelAlarm(mContext,position);
                }
                notifyDataSetChanged();
//                ListFragment.checked.set(position,!checked.get(position));
            }
        });

        LinearLayout layout = (LinearLayout)view.findViewById(R.id.layout);
        if(position%2==1)
            layout.setBackgroundColor(mContext.getResources().getColor(R.color.one));
        else
            layout.setBackgroundColor(mContext.getResources().getColor(R.color.two));

        return view;
    }

    private int setRepeatDayOfWeekToInteger(String repeatDayOfWeek){
        int dayOfWeek = 0;
        switch (repeatDayOfWeek){
            case "Never": dayOfWeek = 0; break;
            case "Every Sunday": dayOfWeek =  1; break;
            case "Every Monday": dayOfWeek =  2; break;
            case "Every Tuesday": dayOfWeek =  3; break;
            case "Every Wednesday": dayOfWeek =  4; break;
            case "Every Thursday": dayOfWeek =  5; break;
            case "Every Friday": dayOfWeek =  6; break;
            case "Every Saturday": dayOfWeek =  7; break;
        }
        return dayOfWeek;
    }


}
