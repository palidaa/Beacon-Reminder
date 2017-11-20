package com.example.palida.beacon_reminder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

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
                //toggle switch then save change to db
                items.get(position).setChecked(items.get(position).getChecked()==1?0:1);
                DBHelper dbHelper = new DBHelper(mContext);
                dbHelper.updateBeacon(items.get(position));

                //Set Alarm ------ not use--------
//                AlarmManagers alarmManagers = new AlarmManagers(mContext);
//                if(items.get(position).getChecked()==1){
//                    String startTime = items.get(position).getStart_time();
//                    String[] time = startTime.split(":");
//                    int hour = Integer.parseInt(time[0].trim());
//                    int min = Integer.parseInt(time[1].trim());
//                    alarmManagers.setAlarm(mContext,hour,min,position,setRepeatDayOfWeekToInteger(items.get(position).getRepeat()));
//                }
//                else{
//                    alarmManagers.cancelAlarm(mContext,position);
//                }
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



}
