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
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class WithInListFragment extends Fragment {
    private View rootView;
//    int pos;
    String id;

    public WithInListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_with_in_list, container, false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
//            pos = bundle.getInt("posL");
            id = bundle.getString(Item.Column.ID);
        }

        final DBHelper dbHelper = new DBHelper(getActivity());
        final HashMap queryItem = dbHelper.getBeacon(id);

        TextView titleName = (TextView) getActivity().findViewById(R.id.toolbar_title);
        titleName.setText("");
        TextView edit = (TextView) getActivity().findViewById(R.id.edit);
        edit.setText("edit");
        edit.setVisibility(View.VISIBLE);
        Button btn_delete = (Button) getActivity().findViewById(R.id.search);
        btn_delete.setBackgroundResource(R.drawable.ic_delete_black_24dp);
        btn_delete.setVisibility(View.VISIBLE);
        TextView save = (TextView) getActivity().findViewById(R.id.save);
        save.setText("");
        save.setVisibility(View.INVISIBLE);



        ImageView pic = (ImageView) rootView.findViewById(R.id.pic);
        pic.setBackgroundResource((Integer) queryItem.get(Item.Column.PIC));
        TextView name = (TextView) rootView.findViewById(R.id.name);
        name.setText("Name: " + queryItem.get(Item.Column.NAME));
        TextView des = (TextView) rootView.findViewById(R.id.description);
        des.setText("Description: "+queryItem.get(Item.Column.DESCRIPTION));
        TextView install = (TextView) rootView.findViewById(R.id.install_on);

        install.setText("Install on: "+queryItem.get(Item.Column.INSTALL));

//        install.setText("Install on: "+ ListFragment.install.get(pos));



        btn_delete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                dbHelper.deleteBeacon((String) queryItem.get(Item.Column.ID));
                Toast.makeText(getActivity(), "Delete item complete",
                        Toast.LENGTH_LONG).show();
                getFragmentManager().popBackStack();
            }
        });

        edit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                EditFragment select1 = new EditFragment();
                Bundle bundle = new Bundle();
                bundle.putString(Item.Column.ID, (String) queryItem.get(Item.Column.ID));
                select1.setArguments(bundle);
                transaction.replace(R.id.frame, select1);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return rootView;
    }

}
