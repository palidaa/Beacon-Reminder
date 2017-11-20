package com.example.palida.beacon_reminder;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class RepeatFragment extends Fragment {
    private View rootView;
    int pos;
    String id;
    DBHelper dbHelper;
    HashMap queryItem;
    Item item;
    int itemSelected = 0;
    String []day= {"Never","Every Sunday","Every Monday","Every Tuesday","Every Wednesday","Every Thursday","Every Friday","Every Saturday"};

    public RepeatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_repeat, container, false);
        TextView titleName = (TextView) getActivity().findViewById(R.id.toolbar_title);
        titleName.setText("Repeat");
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
//            pos = bundle.getInt("pos");
            id = bundle.getString(Item.Column.ID);
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

        getActivity().findViewById(R.id.edit).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                getFragmentManager().popBackStack();
            }
        });

        getActivity().findViewById(R.id.save).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                item.setRepeat(day[itemSelected]);
                dbHelper.updateBeacon(item);
//                WithInAlarmFragment.repeat1=day[itemSelected];
                getFragmentManager().popBackStack();
            }
        });

        rootView.findViewById(R.id.q1).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ImageView check1 = (ImageView) rootView.findViewById(R.id.check1);
                ImageView check2 = (ImageView) rootView.findViewById(R.id.check2);
                ImageView check3 = (ImageView) rootView.findViewById(R.id.check3);
                ImageView check4 = (ImageView) rootView.findViewById(R.id.check4);
                ImageView check5 = (ImageView) rootView.findViewById(R.id.check5);
                ImageView check6 = (ImageView) rootView.findViewById(R.id.check6);
                ImageView check7 = (ImageView) rootView.findViewById(R.id.check7);

                check1.setBackgroundResource(R.drawable.check);
                check2.setBackgroundResource(0);
                check3.setBackgroundResource(0);
                check4.setBackgroundResource(0);
                check5.setBackgroundResource(0);
                check6.setBackgroundResource(0);
                check7.setBackgroundResource(0);
                itemSelected=1;
            }
        });
        rootView.findViewById(R.id.q2).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ImageView check1 = (ImageView) rootView.findViewById(R.id.check1);
                ImageView check2 = (ImageView) rootView.findViewById(R.id.check2);
                ImageView check3 = (ImageView) rootView.findViewById(R.id.check3);
                ImageView check4 = (ImageView) rootView.findViewById(R.id.check4);
                ImageView check5 = (ImageView) rootView.findViewById(R.id.check5);
                ImageView check6 = (ImageView) rootView.findViewById(R.id.check6);
                ImageView check7 = (ImageView) rootView.findViewById(R.id.check7);

                check1.setBackgroundResource(0);
                check2.setBackgroundResource(R.drawable.check);
                check3.setBackgroundResource(0);
                check4.setBackgroundResource(0);
                check5.setBackgroundResource(0);
                check6.setBackgroundResource(0);
                check7.setBackgroundResource(0);
                itemSelected=2;
            }
        });
        rootView.findViewById(R.id.q3).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ImageView check1 = (ImageView) rootView.findViewById(R.id.check1);
                ImageView check2 = (ImageView) rootView.findViewById(R.id.check2);
                ImageView check3 = (ImageView) rootView.findViewById(R.id.check3);
                ImageView check4 = (ImageView) rootView.findViewById(R.id.check4);
                ImageView check5 = (ImageView) rootView.findViewById(R.id.check5);
                ImageView check6 = (ImageView) rootView.findViewById(R.id.check6);
                ImageView check7 = (ImageView) rootView.findViewById(R.id.check7);

                check1.setBackgroundResource(0);
                check2.setBackgroundResource(0);
                check3.setBackgroundResource(R.drawable.check);
                check4.setBackgroundResource(0);
                check5.setBackgroundResource(0);
                check6.setBackgroundResource(0);
                check7.setBackgroundResource(0);
                itemSelected=3;
            }
        });
        rootView.findViewById(R.id.q4).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ImageView check1 = (ImageView) rootView.findViewById(R.id.check1);
                ImageView check2 = (ImageView) rootView.findViewById(R.id.check2);
                ImageView check3 = (ImageView) rootView.findViewById(R.id.check3);
                ImageView check4 = (ImageView) rootView.findViewById(R.id.check4);
                ImageView check5 = (ImageView) rootView.findViewById(R.id.check5);
                ImageView check6 = (ImageView) rootView.findViewById(R.id.check6);
                ImageView check7 = (ImageView) rootView.findViewById(R.id.check7);

                check1.setBackgroundResource(0);
                check2.setBackgroundResource(0);
                check3.setBackgroundResource(0);
                check4.setBackgroundResource(R.drawable.check);
                check5.setBackgroundResource(0);
                check6.setBackgroundResource(0);
                check7.setBackgroundResource(0);
                itemSelected=4;
            }
        });
        rootView.findViewById(R.id.q5).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ImageView check1 = (ImageView) rootView.findViewById(R.id.check1);
                ImageView check2 = (ImageView) rootView.findViewById(R.id.check2);
                ImageView check3 = (ImageView) rootView.findViewById(R.id.check3);
                ImageView check4 = (ImageView) rootView.findViewById(R.id.check4);
                ImageView check5 = (ImageView) rootView.findViewById(R.id.check5);
                ImageView check6 = (ImageView) rootView.findViewById(R.id.check6);
                ImageView check7 = (ImageView) rootView.findViewById(R.id.check7);

                check1.setBackgroundResource(0);
                check2.setBackgroundResource(0);
                check3.setBackgroundResource(0);
                check4.setBackgroundResource(0);
                check5.setBackgroundResource(R.drawable.check);
                check6.setBackgroundResource(0);
                check7.setBackgroundResource(0);
                itemSelected=5;
            }
        });
        rootView.findViewById(R.id.q6).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ImageView check1 = (ImageView) rootView.findViewById(R.id.check1);
                ImageView check2 = (ImageView) rootView.findViewById(R.id.check2);
                ImageView check3 = (ImageView) rootView.findViewById(R.id.check3);
                ImageView check4 = (ImageView) rootView.findViewById(R.id.check4);
                ImageView check5 = (ImageView) rootView.findViewById(R.id.check5);
                ImageView check6 = (ImageView) rootView.findViewById(R.id.check6);
                ImageView check7 = (ImageView) rootView.findViewById(R.id.check7);

                check1.setBackgroundResource(0);
                check2.setBackgroundResource(0);
                check3.setBackgroundResource(0);
                check4.setBackgroundResource(0);
                check5.setBackgroundResource(0);
                check6.setBackgroundResource(R.drawable.check);
                check7.setBackgroundResource(0);
                itemSelected=6;
            }
        });
        rootView.findViewById(R.id.q7).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ImageView check1 = (ImageView) rootView.findViewById(R.id.check1);
                ImageView check2 = (ImageView) rootView.findViewById(R.id.check2);
                ImageView check3 = (ImageView) rootView.findViewById(R.id.check3);
                ImageView check4 = (ImageView) rootView.findViewById(R.id.check4);
                ImageView check5 = (ImageView) rootView.findViewById(R.id.check5);
                ImageView check6 = (ImageView) rootView.findViewById(R.id.check6);
                ImageView check7 = (ImageView) rootView.findViewById(R.id.check7);

                check1.setBackgroundResource(0);
                check2.setBackgroundResource(0);
                check3.setBackgroundResource(0);
                check4.setBackgroundResource(0);
                check5.setBackgroundResource(0);
                check6.setBackgroundResource(0);
                check7.setBackgroundResource(R.drawable.check);
                itemSelected=7;
            }
        });
        return rootView;
    }

}
