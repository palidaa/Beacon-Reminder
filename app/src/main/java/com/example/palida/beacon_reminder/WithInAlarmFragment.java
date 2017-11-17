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
    int pos;

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

        getActivity().findViewById(R.id.edit).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                getFragmentManager().popBackStack();
            }
        });

        getActivity().findViewById(R.id.save).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
//                getFragmentManager().popBackStack();
            }
        });

        ImageView imageView = (ImageView) rootView.findViewById(R.id.pic);
        imageView.setBackgroundResource(ListFragment.pic.get(pos));

        TextView name = (TextView) rootView.findViewById(R.id.name);
        name.setText(ListFragment.name.get(pos));

        Switch s = (Switch) rootView.findViewById(R.id.switch2);
        s.setChecked(ListFragment.checked.get(pos));

//        TextView repeat = (TextView) rootView.findViewById(R.id.repeat);
//        repeat.setText(ListFragment.repeat.get(pos));

        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListFragment.checked.set(pos,!ListFragment.checked.get(pos));
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

            }
        });



        return rootView;
    }
}
