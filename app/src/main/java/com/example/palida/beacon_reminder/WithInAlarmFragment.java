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

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WithInAlarmFragment extends Fragment {
    private View rootView;
    public static int pos;
//    public static String startTime1 = ListFragment.start_time.get(pos);
//    public static String endTime1= ListFragment.end_time.get(pos);
//    public static String repeat1 = ListFragment.repeat.get(pos);
//    public static String label1 = ListFragment.label.get(pos);
//    public boolean snooze1 = ListFragment.snooze.get(pos);

    public static List<String>  startTime1 = ListFragment.start_time;
    public static List<String> endTime1= ListFragment.end_time;
    public static List<String> repeat1 = ListFragment.repeat;
    public static List<String> label1 = ListFragment.label;
    public List<Boolean> snooze1 = ListFragment.snooze;

    public WithInAlarmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_with_in_alarm, container, false);
        TextView titleName = (TextView) getActivity().findViewById(R.id.toolbar_title);
        titleName.setText("Add Alarm");
        TextView edit = (TextView) getActivity().findViewById(R.id.edit);
        edit.setText("cancel");
        Button search = (Button) getActivity().findViewById(R.id.search);
        search.setBackgroundColor(Color.TRANSPARENT);
        TextView save = (TextView) getActivity().findViewById(R.id.save);
        save.setText("save");

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            pos = bundle.getInt("pos");
        }
//        public static List<String>  startTime1 = ListFragment.start_time;
//        public static List<String> endTime1= ListFragment.end_time;
        repeat1 = ListFragment.repeat;
        label1 = ListFragment.label;
//        public List<Boolean> snooze1 = ListFragment.snooze;

        getActivity().findViewById(R.id.edit).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
//                startTime1 = ListFragment.start_time.get(pos);
//                endTime1= ListFragment.end_time.get(pos);
//                repeat1 = ListFragment.repeat.get(pos);
//                label1 = ListFragment.label.get(pos);
//                snooze1 = ListFragment.snooze.get(pos);
//                label1.set(pos,ListFragment.label.get(pos));
                getFragmentManager().popBackStack();
            }
        });

        getActivity().findViewById(R.id.save).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
//                ListFragment.repeat.set(pos,repeat1.get(pos));
//                ListFragment.label.set(pos,label1.get(pos));
//                ListFragment.snooze.set(pos,snooze1.get(pos));
                getFragmentManager().popBackStack();
            }
        });

        ImageView imageView = (ImageView) rootView.findViewById(R.id.pic);
        imageView.setBackgroundResource(ListFragment.pic.get(pos));
        TextView name = (TextView) rootView.findViewById(R.id.name);
        name.setText(ListFragment.name.get(pos));

        Switch s = (Switch) rootView.findViewById(R.id.switch2);
        s.setChecked(snooze1.get(pos));

        TextView repeat = (TextView) rootView.findViewById(R.id.repeat);
        repeat.setText(repeat1.get(pos));

        TextView label = (TextView) rootView.findViewById(R.id.label);
        label.setText(label1.get(pos));

        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snooze1.set(pos,!snooze1.get(pos));
            }
        });

        rootView.findViewById(R.id.start_time).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });
        rootView.findViewById(R.id.end_time).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });
        rootView.findViewById(R.id.repeat).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                MainActivity.l=2;
                MainActivity.i=2;

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                RepeatFragment select1 = new RepeatFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("pos", pos);
                select1.setArguments(bundle);
                transaction.replace(R.id.frame, select1);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        rootView.findViewById(R.id.label).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                MainActivity.l=2;
                MainActivity.i=2;

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                LabelFragment select1 = new LabelFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("pos", pos);
                select1.setArguments(bundle);
                transaction.replace(R.id.frame, select1);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });



        return rootView;
    }
}
