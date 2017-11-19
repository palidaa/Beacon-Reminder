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


/**
 * A simple {@link Fragment} subclass.
 */
public class TimeFragment extends Fragment {
    private View rootView;
    public static int pos;
    public static String start_or_end;

    public TimeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_time, container, false);
        Bundle bundle = this.getArguments();
        Bundle b1 = bundle.getBundle("bundle_pos");
        Bundle b2 = bundle.getBundle("start_or_end");
        if(b1!=null) pos = b1.getInt("pos");
        if(b2!=null) start_or_end = b2.getString("start_or_end");

        TextView titleName = (TextView) getActivity().findViewById(R.id.toolbar_title);
        if(start_or_end=="start") titleName.setText("Start Time");
        else titleName.setText("End Time");
        TextView edit = (TextView) getActivity().findViewById(R.id.edit);
        edit.setText("cancel");
        edit.setVisibility(View.VISIBLE);
        Button search = (Button) getActivity().findViewById(R.id.search);
        search.setBackgroundColor(Color.TRANSPARENT);
        TextView save = (TextView) getActivity().findViewById(R.id.save);
        save.setText("save");
        save.setVisibility(View.VISIBLE);
        String timme;
        String[] time;
        int hour;
        int min;

        if(start_or_end=="start") timme = WithInAlarmFragment.startTime1;
        else timme = WithInAlarmFragment.endTime1;

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

        getActivity().findViewById(R.id.edit).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                getFragmentManager().popBackStack();
            }
        });

        getActivity().findViewById(R.id.save).setOnClickListener(new View.OnClickListener(){
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
                if(start_or_end=="start") WithInAlarmFragment.startTime1=formattedTime;
                else WithInAlarmFragment.endTime1 = formattedTime;
                getFragmentManager().popBackStack();
            }
        });
        return  rootView;
    }

}
