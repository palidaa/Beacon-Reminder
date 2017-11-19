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


/**
 * A simple {@link Fragment} subclass.
 */
public class LabelFragment extends Fragment {
    private View rootView;
    int pos;

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
            pos = bundle.getInt("pos");
        }


        final EditText editLabel  = (EditText) rootView.findViewById(R.id.label);
        editLabel.setText(WithInAlarmFragment.label1);

        getActivity().findViewById(R.id.edit).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                getFragmentManager().popBackStack();
            }
        });

        getActivity().findViewById(R.id.save).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String editL = editLabel.getText().toString();
                WithInAlarmFragment.label1=editL;
                getFragmentManager().popBackStack();
            }
        });

        return rootView;
    }

}
