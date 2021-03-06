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
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditFragment extends Fragment {
    private View rootView;

    String id;
    public static int[] picS = {R.drawable.key,R.drawable.medicine,R.drawable.umbrella,R.drawable.clothes,R.drawable.money,R.drawable.question};
    int pos;
    public int itemSelected=0;


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
//            pos = bundle.getInt("pos");
            id = bundle.getString(Item.Column.ID);

        }

        final DBHelper dbHelper = new DBHelper(getActivity());
        final HashMap queryItem = dbHelper.getBeacon(id);

        TextView titleName = (TextView) getActivity().findViewById(R.id.toolbar_title);
        titleName.setText("");
        TextView edit = (TextView) getActivity().findViewById(R.id.edit);
        edit.setText("cancel");
        edit.setVisibility(View.VISIBLE);
        Button search = (Button) getActivity().findViewById(R.id.search);
        search.setVisibility(View.INVISIBLE);
        TextView save = (TextView) getActivity().findViewById(R.id.save);
        save.setText("save");
        save.setVisibility(View.VISIBLE);

        ImageView key = (ImageView) rootView.findViewById(R.id.key);
        ImageView umbrella = (ImageView) rootView.findViewById(R.id.umbrella);
        ImageView question = (ImageView) rootView.findViewById(R.id.question);
        ImageView cloth = (ImageView) rootView.findViewById(R.id.cloth);
//        ImageView money = (ImageView) rootView.findViewById(R.id.money);
        ImageView medicine = (ImageView) rootView.findViewById(R.id.medicine);
        ImageView door = (ImageView) rootView.findViewById(R.id.door);

        if((int)queryItem.get(Item.Column.PIC)==WithInAddFragment.picS[0]) key.setBackgroundResource(R.drawable.black);;
        if((int)queryItem.get(Item.Column.PIC)==WithInAddFragment.picS[1]) medicine.setBackgroundResource(R.drawable.black);
        if((int)queryItem.get(Item.Column.PIC)==WithInAddFragment.picS[2]) umbrella.setBackgroundResource(R.drawable.black);
        if((int)queryItem.get(Item.Column.PIC)==WithInAddFragment.picS[3]) cloth.setBackgroundResource(R.drawable.black);
//        if((int)queryItem.get(Item.Column.PIC)==WithInAddFragment.picS[4]) money.setBackgroundResource(R.drawable.black);
        if((int)queryItem.get(Item.Column.PIC)==WithInAddFragment.picS[5]) door.setBackgroundResource(R.drawable.black);
        if((int)queryItem.get(Item.Column.PIC)==WithInAddFragment.picS[6]) question.setBackgroundResource(R.drawable.black);


        final EditText editName  = (EditText) rootView.findViewById(R.id.name);
        final EditText editDes  = (EditText) rootView.findViewById(R.id.des);
        editName.setText((CharSequence) queryItem.get(Item.Column.NAME));
        editDes.setText((CharSequence) queryItem.get(Item.Column.DESCRIPTION));
        Date currentTime = Calendar.getInstance().getTime();
        final String date = currentTime.getDate() +"/" + currentTime.getMonth() + "/2017";

        save.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                Toast.makeText(getActivity(), "Edit item complete",
                        Toast.LENGTH_LONG).show();

                String editN = editName.getText().toString();
                String editD = editDes.getText().toString();
                Item item = new Item((String) queryItem.get(Item.Column.ID), editN,
                        picS[itemSelected],editD,
                        (String)queryItem.get(Item.Column.INSTALL),
                        (int) queryItem.get(Item.Column.CHECKED),
                        (String)queryItem.get(Item.Column.START_TIME),
                        (String)queryItem.get(Item.Column.END_TIME),
                        (String)queryItem.get(Item.Column.REPEAT),
                        (String)queryItem.get(Item.Column.LABEL),
                        (int)queryItem.get(Item.Column.SNOOZE));
                dbHelper.updateBeacon(item);
//                ListFragment.name.set(pos, editN);
//                ListFragment.description.set(pos,editD);
//                ListFragment.pic.set(pos,WithInAddFragment.picS[itemSelected]);
                getFragmentManager().popBackStack();
            }
        });

        edit.setOnClickListener(new View.OnClickListener(){
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
//                ImageView money = (ImageView) rootView.findViewById(R.id.money);
                ImageView medicine = (ImageView) rootView.findViewById(R.id.medicine);
                ImageView door = (ImageView) rootView.findViewById(R.id.door);
                door.setBackgroundResource(0);
                key.setBackgroundResource(R.drawable.black);
                umbrella.setBackgroundResource(0);
                question.setBackgroundResource(0);
                cloth.setBackgroundResource(0);
//                money.setBackgroundResource(0);
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
//                ImageView money = (ImageView) rootView.findViewById(R.id.money);
                ImageView medicine = (ImageView) rootView.findViewById(R.id.medicine);
                ImageView door = (ImageView) rootView.findViewById(R.id.door);
                door.setBackgroundResource(0);
                key.setBackgroundResource(0);
                umbrella.setBackgroundResource(0);
                question.setBackgroundResource(0);
                cloth.setBackgroundResource(0);
//                money.setBackgroundResource(0);
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
//                ImageView money = (ImageView) rootView.findViewById(R.id.money);
                ImageView medicine = (ImageView) rootView.findViewById(R.id.medicine);
                ImageView door = (ImageView) rootView.findViewById(R.id.door);
                door.setBackgroundResource(0);
                key.setBackgroundResource(0);
                umbrella.setBackgroundResource(R.drawable.black);
                question.setBackgroundResource(0);
                cloth.setBackgroundResource(0);
//                money.setBackgroundResource(0);
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
//                ImageView money = (ImageView) rootView.findViewById(R.id.money);
                ImageView medicine = (ImageView) rootView.findViewById(R.id.medicine);
                ImageView door = (ImageView) rootView.findViewById(R.id.door);
                door.setBackgroundResource(0);
                key.setBackgroundResource(0);
                umbrella.setBackgroundResource(0);
                question.setBackgroundResource(R.drawable.black);
                cloth.setBackgroundResource(0);
//                money.setBackgroundResource(0);
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
//                ImageView money = (ImageView) rootView.findViewById(R.id.money);
                ImageView medicine = (ImageView) rootView.findViewById(R.id.medicine);
                ImageView door = (ImageView) rootView.findViewById(R.id.door);
                door.setBackgroundResource(0);
                key.setBackgroundResource(0);
                umbrella.setBackgroundResource(0);
                question.setBackgroundResource(0);
                cloth.setBackgroundResource(R.drawable.black);
//                money.setBackgroundResource(0);
                medicine.setBackgroundResource(0);
                itemSelected=3;
            }
        });
//        rootView.findViewById(R.id.money).setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                ImageView key = (ImageView) rootView.findViewById(R.id.key);
//                ImageView umbrella = (ImageView) rootView.findViewById(R.id.umbrella);
//                ImageView question = (ImageView) rootView.findViewById(R.id.question);
//                ImageView cloth = (ImageView) rootView.findViewById(R.id.cloth);
//                ImageView money = (ImageView) rootView.findViewById(R.id.money);
//                ImageView medicine = (ImageView) rootView.findViewById(R.id.medicine);
//                ImageView door = (ImageView) rootView.findViewById(R.id.door);
//                door.setBackgroundResource(0);
//                key.setBackgroundResource(0);
//                umbrella.setBackgroundResource(0);
//                question.setBackgroundResource(0);
//                cloth.setBackgroundResource(0);
//                money.setBackgroundResource(R.drawable.black);
//                medicine.setBackgroundResource(0);
//                itemSelected=4;
//            }
//        });
        rootView.findViewById(R.id.door).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ImageView key = (ImageView) rootView.findViewById(R.id.key);
                ImageView umbrella = (ImageView) rootView.findViewById(R.id.umbrella);
                ImageView question = (ImageView) rootView.findViewById(R.id.question);
                ImageView cloth = (ImageView) rootView.findViewById(R.id.cloth);
//                ImageView money = (ImageView) rootView.findViewById(R.id.money);
                ImageView medicine = (ImageView) rootView.findViewById(R.id.medicine);
                ImageView door = (ImageView) rootView.findViewById(R.id.door);
                door.setBackgroundResource(R.drawable.black);
                key.setBackgroundResource(0);
                umbrella.setBackgroundResource(0);
                question.setBackgroundResource(0);
                cloth.setBackgroundResource(0);
//                money.setBackgroundResource(0);
                medicine.setBackgroundResource(0);
                itemSelected=5;
            }
        });




        return rootView;
    }

}
