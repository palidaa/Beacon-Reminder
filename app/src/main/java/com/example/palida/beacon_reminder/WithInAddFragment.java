package com.example.palida.beacon_reminder;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
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
public class WithInAddFragment extends Fragment {
    private View rootView;
    String beacon_id;
// beacon_name;
    int pos;
    public int itemSelected=5;
    public static int[] picS = {R.drawable.key,R.drawable.medicine,R.drawable.umbrella,R.drawable.clothes,R.drawable.money,R.drawable.question};
    public static String[] nameS = {"key","medicine","umbrella","clothes","money","question"};
    public WithInAddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_with_in_add, container, false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            pos = bundle.getInt("pos");
            beacon_id = bundle.getString(Item.Column.ID);
//            beacon_name = bundle.getString(Item.Column.NAME);
        }
        TextView titleName = (TextView) getActivity().findViewById(R.id.toolbar_title);
        titleName.setText("");
        final TextView edit = (TextView) getActivity().findViewById(R.id.edit);
        edit.setText("cancel");
        edit.setVisibility(View.VISIBLE);
        Button search = (Button) getActivity().findViewById(R.id.search);
        search.setVisibility(View.INVISIBLE);
        TextView save = (TextView) getActivity().findViewById(R.id.save);
        save.setText("save");
        save.setVisibility(View.VISIBLE);

        final EditText editName  = (EditText) rootView.findViewById(R.id.name);
        final EditText editDes  = (EditText) rootView.findViewById(R.id.des);
//        editName.setText(beacon_name.equalsIgnoreCase("unknown")?"":beacon_name);
        editName.setText("");
        editDes.setText("");
        Date currentTime = Calendar.getInstance().getTime();
        final String date = currentTime.getDate() +"/" + currentTime.getMonth() + "/2017";
        getActivity().findViewById(R.id.save).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                Toast.makeText(getActivity(), "Add item complete",
                        Toast.LENGTH_LONG).show();

                String editN = editName.getText().toString();
                String editD = editDes.getText().toString();
//                ListFragment.name.add(editN);
//                ListFragment.description.add(editD);
//                ListFragment.install.add(date);
//                ListFragment.pic.add(picS[itemSelected]);
//                something about keyyyyyyy vvvvvv
//                ListFragment.key.add(AddFragment.name.get(pos));
                DBHelper dbHelper = new DBHelper(getActivity());
                Item itemBeacon = new Item(beacon_id, editN, picS[itemSelected], editD, date);
                dbHelper.addNewBeacon(itemBeacon);
                Log.e("DBBBBBBBB", String.valueOf(dbHelper.getItemList().size()));

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
