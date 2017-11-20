package com.example.palida.beacon_reminder;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class WithInAlarmFragment extends Fragment {
    private View rootView;
//    public static int pos;
    String id;
    DBHelper dbHelper;
    HashMap queryItem;
    Item item;


//    public static String startTime1 = ListFragment.start_time.get(pos);
//    public static String endTime1= ListFragment.end_time.get(pos);
//    public static String repeat1= ListFragment.repeat.get(pos);
//    public static String label1 = ListFragment.label.get(pos);
//    public static boolean snooze1 = ListFragment.snooze.get(pos);

    String startTime1;
    String endTime1;
    String repeat1;
    String label1;
    boolean snooze1;

    public WithInAlarmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_with_in_alarm, container, false);
        final Bundle bundle = this.getArguments();
        if (bundle != null) {
            id = bundle.getString(Item.Column.ID);
//            pos = bundle.getInt("pos");
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

         startTime1 = item.getStart_time();
         endTime1= item.getEnd_time();
         repeat1= item.getRepeat();
         label1= item.getLabel();
         snooze1= item.getSnooze()==1?true:false;


        TextView titleName = (TextView) getActivity().findViewById(R.id.toolbar_title);
        titleName.setText("Add Alarm");
        TextView btn_cancel = (TextView) getActivity().findViewById(R.id.edit);
        btn_cancel.setText("cancel");
        btn_cancel.setVisibility(View.VISIBLE);
        Button search = (Button) getActivity().findViewById(R.id.search);
        search.setBackgroundColor(Color.TRANSPARENT);
        TextView btn_save = (TextView) getActivity().findViewById(R.id.save);
        btn_save.setText("save");
        btn_save.setVisibility(View.VISIBLE);


        btn_cancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                getFragmentManager().popBackStack();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                item.setChecked(0);
                item.setStart_time(startTime1);
                item.setEnd_time(endTime1);
                item.setRepeat(repeat1);
                item.setLabel(label1);
                item.setSnooze(snooze1?1:0);
                dbHelper.updateBeacon(item);

//                ListFragment.start_time.set(pos,startTime1);
//                ListFragment.end_time.set(pos,endTime1);
//                ListFragment.repeat.set(pos,repeat1);
//                ListFragment.label.set(pos,label1);
//                ListFragment.snooze.set(pos,snooze1);
                getFragmentManager().popBackStack();
            }
        });

        ImageView imageView = (ImageView) rootView.findViewById(R.id.pic);
        imageView.setBackgroundResource(item.getPic());
        TextView name = (TextView) rootView.findViewById(R.id.name);
        name.setText(item.getName());

        Switch switch_alarm = (Switch) rootView.findViewById(R.id.switch2);
        switch_alarm.setChecked(snooze1);

        TextView btn_repeat = (TextView) rootView.findViewById(R.id.repeat);
        btn_repeat.setText(repeat1);

        TextView btn_label = (TextView) rootView.findViewById(R.id.label);
        btn_label.setText(label1);

        if(startTime1=="") startTime1 ="08:00";
        TextView btn_start_time = (TextView) rootView.findViewById(R.id.start_time);
        btn_start_time.setText(startTime1);
        if(endTime1=="") endTime1 ="08:00";
        TextView btn_end_time = (TextView) rootView.findViewById(R.id.end_time);
        btn_end_time.setText(endTime1);

        switch_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snooze1= !snooze1;
            }
        });

        btn_start_time.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                MainActivity.l=2;
                MainActivity.i=2;

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                TimeFragment select1 = new TimeFragment();
                Bundle mainBundle = new Bundle();
//                Bundle bundle_pos = new Bundle();

//                bundle_pos.putInt("pos", pos);
//                Bundle start_or_end = new Bundle();
                mainBundle.putString("start_or_end","start");
                mainBundle.putString(Item.Column.ID, id);
//
//                mainBundle.putBundle("pos", bundle_pos);
//                mainBundle.putBundle("start_or_end", start_or_end);

                select1.setArguments(mainBundle);

                transaction.replace(R.id.frame, select1);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        btn_end_time.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                MainActivity.l=2;
                MainActivity.i=2;

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                TimeFragment select1 = new TimeFragment();

                Bundle mainBundle = new Bundle();
                mainBundle.putString("start_or_end","end");
                mainBundle.putString(Item.Column.ID, id);
                select1.setArguments(mainBundle);

                transaction.replace(R.id.frame, select1);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        btn_repeat.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                MainActivity.l=2;
                MainActivity.i=2;

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                RepeatFragment select1 = new RepeatFragment();
                Bundle bundle = new Bundle();
                bundle.putString(Item.Column.ID, id);
                select1.setArguments(bundle);
                transaction.replace(R.id.frame, select1);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        btn_label.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                MainActivity.l=2;
                MainActivity.i=2;

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                LabelFragment select1 = new LabelFragment();
                Bundle bundle = new Bundle();
                bundle.putString(Item.Column.ID, id);
                select1.setArguments(bundle);
                transaction.replace(R.id.frame, select1);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });



        return rootView;
    }
}
