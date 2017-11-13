package com.example.palida.beacon_reminder;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {
    private View rootView;
    public static List<String> name = new ArrayList<String>();
    public static List<Integer> pic = new ArrayList<Integer>();

    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_list, container, false);
        TextView titleName = (TextView) getActivity().findViewById(R.id.toolbar_title);
        titleName.setText("Add Item");
        TextView edit = (TextView) getActivity().findViewById(R.id.edit);
        edit.setText("");
        Button search = (Button) getActivity().findViewById(R.id.search);
        search.setBackgroundColor(Color.TRANSPARENT);
        TextView save = (TextView) getActivity().findViewById(R.id.save);
        save.setText("");

        if(name.isEmpty()) {
            name.add("ID 000xxxx00");
            name.add("ID 000xxxx00");
            name.add("ID 000xxxx00");
            for (int i = 0; i < name.size(); i++) {
                pic.add(R.drawable.question);
            }
        }

        ListView listView = rootView.findViewById(R.id.listView);
        final CustomAdapter adapter =new CustomAdapter(getActivity(), name,pic);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            MainActivity.l=1;
            MainActivity.i=2;

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            WithInAddFragment select1 = new WithInAddFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("pos", arg2);
            select1.setArguments(bundle);
            transaction.replace(R.id.frame, select1);
            transaction.addToBackStack(null);
            transaction.commit();
            }
        });

        return rootView;
    }

}
