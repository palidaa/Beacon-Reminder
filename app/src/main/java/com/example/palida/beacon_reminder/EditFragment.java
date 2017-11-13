package com.example.palida.beacon_reminder;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditFragment extends Fragment {
    private View rootView;
    int pos;
    public int itemSelected=5;
    public static int[] picS = {R.drawable.key,R.drawable.medicine,R.drawable.umbrella,R.drawable.clothes,R.drawable.money,R.drawable.question};

    public EditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_edit, container, false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            pos = bundle.getInt("pos");
        }
        TextView titleName = (TextView) getActivity().findViewById(R.id.toolbar_title);
        titleName.setText("");
        TextView edit = (TextView) getActivity().findViewById(R.id.edit);
        edit.setText("cancel");
        Button search = (Button) getActivity().findViewById(R.id.search);
        search.setBackgroundColor(Color.TRANSPARENT);
        TextView save = (TextView) getActivity().findViewById(R.id.save);
        save.setText("save");

        ImageView key = (ImageView) rootView.findViewById(R.id.key);
        ImageView umbrella = (ImageView) rootView.findViewById(R.id.umbrella);
        ImageView question = (ImageView) rootView.findViewById(R.id.question);
        ImageView cloth = (ImageView) rootView.findViewById(R.id.cloth);
        ImageView money = (ImageView) rootView.findViewById(R.id.money);
        ImageView medicine = (ImageView) rootView.findViewById(R.id.medicine);

        if(ListFragment.pic.get(pos)==WithInAddFragment.picS[0]) key.setBackgroundResource(R.drawable.black);;
        if(ListFragment.pic.get(pos)==WithInAddFragment.picS[1]) medicine.setBackgroundResource(R.drawable.black);
        if(ListFragment.pic.get(pos)==WithInAddFragment.picS[2]) umbrella.setBackgroundResource(R.drawable.black);
        if(ListFragment.pic.get(pos)==WithInAddFragment.picS[3]) cloth.setBackgroundResource(R.drawable.black);
        if(ListFragment.pic.get(pos)==WithInAddFragment.picS[4]) money.setBackgroundResource(R.drawable.black);
        if(ListFragment.pic.get(pos)==WithInAddFragment.picS[5]) question.setBackgroundResource(R.drawable.black);

        final EditText editName  = (EditText) rootView.findViewById(R.id.name);
        final EditText editDes  = (EditText) rootView.findViewById(R.id.des);
        editName.setText(ListFragment.name.get(pos));
        editDes.setText(ListFragment.description.get(pos));
        Date currentTime = Calendar.getInstance().getTime();
        final String date = currentTime.getDate() +"/" + currentTime.getMonth() + "/2017";

        getActivity().findViewById(R.id.save).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                Toast.makeText(getActivity(), "Edit item complete",
                        Toast.LENGTH_LONG).show();

                String editN = editName.getText().toString();
                String editD = editDes.getText().toString();
                ListFragment.name.set(pos, editN);
                ListFragment.description.set(pos,editD);
                ListFragment.pic.set(pos,picS[itemSelected]);
                getFragmentManager().popBackStack();
            }
        });

        getActivity().findViewById(R.id.edit).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                getFragmentManager().popBackStack();
            }
        });


        rootView.findViewById(R.id.key).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ImageView key = (ImageView) rootView.findViewById(R.id.key);
                ImageView umbrella = (ImageView) rootView.findViewById(R.id.umbrella);
                ImageView question = (ImageView) rootView.findViewById(R.id.question);
                ImageView cloth = (ImageView) rootView.findViewById(R.id.cloth);
                ImageView money = (ImageView) rootView.findViewById(R.id.money);
                ImageView medicine = (ImageView) rootView.findViewById(R.id.medicine);
                key.setBackgroundResource(R.drawable.black);
                umbrella.setBackgroundResource(0);
                question.setBackgroundResource(0);
                cloth.setBackgroundResource(0);
                money.setBackgroundResource(0);
                medicine.setBackgroundResource(0);
                itemSelected=0;
            }
        });
        rootView.findViewById(R.id.medicine).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ImageView key = (ImageView) rootView.findViewById(R.id.key);
                ImageView umbrella = (ImageView) rootView.findViewById(R.id.umbrella);
                ImageView question = (ImageView) rootView.findViewById(R.id.question);
                ImageView cloth = (ImageView) rootView.findViewById(R.id.cloth);
                ImageView money = (ImageView) rootView.findViewById(R.id.money);
                ImageView medicine = (ImageView) rootView.findViewById(R.id.medicine);
                key.setBackgroundResource(0);
                umbrella.setBackgroundResource(0);
                question.setBackgroundResource(0);
                cloth.setBackgroundResource(0);
                money.setBackgroundResource(0);
                medicine.setBackgroundResource(R.drawable.black);
                itemSelected=1;
            }
        });
        rootView.findViewById(R.id.umbrella).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ImageView key = (ImageView) rootView.findViewById(R.id.key);
                ImageView umbrella = (ImageView) rootView.findViewById(R.id.umbrella);
                ImageView question = (ImageView) rootView.findViewById(R.id.question);
                ImageView cloth = (ImageView) rootView.findViewById(R.id.cloth);
                ImageView money = (ImageView) rootView.findViewById(R.id.money);
                ImageView medicine = (ImageView) rootView.findViewById(R.id.medicine);
                key.setBackgroundResource(0);
                umbrella.setBackgroundResource(R.drawable.black);
                question.setBackgroundResource(0);
                cloth.setBackgroundResource(0);
                money.setBackgroundResource(0);
                medicine.setBackgroundResource(0);
                itemSelected=2;
            }
        });
        rootView.findViewById(R.id.question).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ImageView key = (ImageView) rootView.findViewById(R.id.key);
                ImageView umbrella = (ImageView) rootView.findViewById(R.id.umbrella);
                ImageView question = (ImageView) rootView.findViewById(R.id.question);
                ImageView cloth = (ImageView) rootView.findViewById(R.id.cloth);
                ImageView money = (ImageView) rootView.findViewById(R.id.money);
                ImageView medicine = (ImageView) rootView.findViewById(R.id.medicine);
                key.setBackgroundResource(0);
                umbrella.setBackgroundResource(0);
                question.setBackgroundResource(R.drawable.black);
                cloth.setBackgroundResource(0);
                money.setBackgroundResource(0);
                medicine.setBackgroundResource(0);
                itemSelected=5;
            }
        });
        rootView.findViewById(R.id.cloth).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ImageView key = (ImageView) rootView.findViewById(R.id.key);
                ImageView umbrella = (ImageView) rootView.findViewById(R.id.umbrella);
                ImageView question = (ImageView) rootView.findViewById(R.id.question);
                ImageView cloth = (ImageView) rootView.findViewById(R.id.cloth);
                ImageView money = (ImageView) rootView.findViewById(R.id.money);
                ImageView medicine = (ImageView) rootView.findViewById(R.id.medicine);
                key.setBackgroundResource(0);
                umbrella.setBackgroundResource(0);
                question.setBackgroundResource(0);
                cloth.setBackgroundResource(R.drawable.black);
                money.setBackgroundResource(0);
                medicine.setBackgroundResource(0);
                itemSelected=3;
            }
        });
        rootView.findViewById(R.id.money).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ImageView key = (ImageView) rootView.findViewById(R.id.key);
                ImageView umbrella = (ImageView) rootView.findViewById(R.id.umbrella);
                ImageView question = (ImageView) rootView.findViewById(R.id.question);
                ImageView cloth = (ImageView) rootView.findViewById(R.id.cloth);
                ImageView money = (ImageView) rootView.findViewById(R.id.money);
                ImageView medicine = (ImageView) rootView.findViewById(R.id.medicine);
                key.setBackgroundResource(0);
                umbrella.setBackgroundResource(0);
                question.setBackgroundResource(0);
                cloth.setBackgroundResource(0);
                money.setBackgroundResource(R.drawable.black);
                medicine.setBackgroundResource(0);
                itemSelected=4;
            }
        });



        return rootView;
    }

}
