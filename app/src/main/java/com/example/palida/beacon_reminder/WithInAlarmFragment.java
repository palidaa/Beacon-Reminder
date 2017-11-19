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


/**
 * A simple {@link Fragment} subclass.
 */
public class WithInAlarmFragment extends Fragment {
    private View rootView;
    public static int pos;
    public static String startTime1 = ListFragment.start_time.get(pos);
    public static String endTime1= ListFragment.end_time.get(pos);
    public static String repeat1= ListFragment.repeat.get(pos);
    public static String label1 = ListFragment.label.get(pos);
    public static boolean snooze1 = ListFragment.snooze.get(pos);

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
        edit.setVisibility(View.VISIBLE);
        Button search = (Button) getActivity().findViewById(R.id.search);
        search.setBackgroundColor(Color.TRANSPARENT);
        TextView save = (TextView) getActivity().findViewById(R.id.save);
        save.setText("save");
        save.setVisibility(View.VISIBLE);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            pos = bundle.getInt("pos");
        }

        getActivity().findViewById(R.id.edit).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                getFragmentManager().popBackStack();
            }
        });

        getActivity().findViewById(R.id.save).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ListFragment.start_time.set(pos,startTime1);
                ListFragment.end_time.set(pos,endTime1);
                ListFragment.repeat.set(pos,repeat1);
                ListFragment.label.set(pos,label1);
                ListFragment.snooze.set(pos,snooze1);
                getFragmentManager().popBackStack();
            }
        });

        ImageView imageView = (ImageView) rootView.findViewById(R.id.pic);
        imageView.setBackgroundResource(ListFragment.pic.get(pos));
        TextView name = (TextView) rootView.findViewById(R.id.name);
        name.setText(ListFragment.name.get(pos));

        Switch s = (Switch) rootView.findViewById(R.id.switch2);
        s.setChecked(snooze1);

        TextView repeat = (TextView) rootView.findViewById(R.id.repeat);
        repeat.setText(repeat1);

        TextView label = (TextView) rootView.findViewById(R.id.label);
        label.setText(label1);

        if(startTime1=="") startTime1 ="08:00";
        TextView start = (TextView) rootView.findViewById(R.id.start_time);
        start.setText(startTime1);
        if(endTime1=="") endTime1 ="08:00";
        TextView end = (TextView) rootView.findViewById(R.id.end_time);
        end.setText(endTime1);

        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snooze1= !snooze1;
            }
        });

        rootView.findViewById(R.id.start_time).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                MainActivity.l=2;
                MainActivity.i=2;

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                TimeFragment select1 = new TimeFragment();
                Bundle mainBundle = new Bundle();
                Bundle bundle_pos = new Bundle();
                bundle_pos.putInt("pos", pos);
                Bundle start_or_end = new Bundle();
                start_or_end.putString("start_or_end","start");

                mainBundle.putBundle("pos", bundle_pos);
                mainBundle.putBundle("start_or_end", start_or_end);

                select1.setArguments(mainBundle);

                transaction.replace(R.id.frame, select1);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        rootView.findViewById(R.id.end_time).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                MainActivity.l=2;
                MainActivity.i=2;

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                TimeFragment select1 = new TimeFragment();
                Bundle mainBundle = new Bundle();
                Bundle bundle_pos = new Bundle();
                bundle_pos.putInt("pos", pos);
                Bundle start_or_end = new Bundle();
                start_or_end.putString("start_or_end","end");

                mainBundle.putBundle("pos", bundle_pos);
                mainBundle.putBundle("start_or_end", start_or_end);

                select1.setArguments(mainBundle);

                transaction.replace(R.id.frame, select1);
                transaction.addToBackStack(null);
                transaction.commit();
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
