package com.example.palida.beacon_reminder;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimeFragment extends Fragment {
    private View rootView;
    public static int pos;
    public static String start_or_end;

    String id;
    DBHelper dbHelper;
    HashMap queryItem;
    Item item;

    public TimeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_time, container, false);
        Bundle bundle = this.getArguments();
//        Bundle b1 = bundle.getBundle("bundle_pos");
//        Bundle b2 = bundle.getBundle("start_or_end");

        if (bundle != null) {
            id = bundle.getString(Item.Column.ID);
            start_or_end = bundle.getString("start_or_end");
        }

        dbHelper = new DBHelper(getActivity());
        queryItem = dbHelper.getBeacon(id);

        item = new Item((String) queryItem.get(Item.Column.ID),
                (String) queryItem.get(Item.Column.NAME),
                (int) queryItem.get(Item.Column.PIC),
                (String) queryItem.get(Item.Column.DESCRIPTION),
                (String)queryItem.get(Item.Column.INSTALL),
                (int) queryItem.get(Item.Column.CHECKED),
                (String)queryItem.get(Item.Column.START_TIME),
                (String)queryItem.get(Item.Column.END_TIME),
                (String)queryItem.get(Item.Column.REPEAT),
                (String)queryItem.get(Item.Column.LABEL),
                (int)queryItem.get(Item.Column.SNOOZE));

//        if(b1!=null) pos = b1.getInt("pos");
//        if(b2!=null) start_or_end = b2.getString("start_or_end");

        TextView titleName = (TextView) getActivity().findViewById(R.id.toolbar_title);
        if(start_or_end=="start") titleName.setText("Start Time");
        else titleName.setText("End Time");

        TextView btn_cancel = (TextView) getActivity().findViewById(R.id.edit);
        btn_cancel.setText("cancel");
        btn_cancel.setVisibility(View.VISIBLE);
        Button search = (Button) getActivity().findViewById(R.id.search);
        search.setBackgroundColor(Color.TRANSPARENT);
        TextView btn_save = (TextView) getActivity().findViewById(R.id.save);
        btn_save.setText("save");
        btn_save.setVisibility(View.VISIBLE);

        String timme;
        String[] time;
        int hour;
        int min;

        if(start_or_end=="start") timme = item.getStart_time();
        else timme = item.getEnd_time();

        if(timme=="") {
            hour = 8;
            min = 0;
        }else {
            time = timme.split(":");
            hour = Integer.parseInt(time[0].trim());
            min = Integer.parseInt(time[1].trim());
        }

        final TimePicker simpleTimePicker=(TimePicker)rootView.findViewById(R.id.timePicker);
        simpleTimePicker.setCurrentHour(hour);
        simpleTimePicker.setCurrentMinute(min);

        btn_cancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                getFragmentManager().popBackStack();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String formattedTime = "";
                int hour = simpleTimePicker.getCurrentHour();
                String sHour = "00";
                if(hour < 10){
                    sHour = "0"+hour;
                } else {
                    sHour = String.valueOf(hour);
                }

                int minute = simpleTimePicker.getCurrentMinute();
                String sMinute = "00";
                if(minute < 10){
                    sMinute = "0"+minute;
                } else {
                    sMinute = String.valueOf(minute);
                }

                formattedTime = sHour+":"+sMinute;
                if(start_or_end=="start") item.setStart_time(formattedTime);
                else  item.setEnd_time(formattedTime);

                dbHelper.updateBeacon(item);
                getFragmentManager().popBackStack();
            }
        });
        return  rootView;
    }

}
