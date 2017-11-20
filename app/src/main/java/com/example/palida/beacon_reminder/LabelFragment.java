package com.example.palida.beacon_reminder;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class LabelFragment extends Fragment {
    private View rootView;
    int pos;
    String id;
    DBHelper dbHelper;
    HashMap queryItem;
    Item item;
    public LabelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_label, container, false);
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


        final EditText editLabel  = (EditText) rootView.findViewById(R.id.label);
        editLabel.setText(item.getLabel());

        edit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                getFragmentManager().popBackStack();
            }
        });

        save.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String editL = editLabel.getText().toString();
//                WithInAlarmFragment.label1=editL;
                item.setLabel(editL);
                dbHelper.updateBeacon(item);
                getFragmentManager().popBackStack();
            }
        });

        return rootView;
    }

}
