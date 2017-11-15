package com.example.palida.beacon_reminder;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {
    public static List<String> name = new ArrayList<String>();
    public static List<Integer> pic = new ArrayList<Integer>();
    public static List<String> description = new ArrayList<String>();
    public static List<String> install = new ArrayList<String>();
    public static List<String> key = new ArrayList<String>();
    public static List<Boolean> checked = new ArrayList<Boolean>();


    public static List<Item> items = new ArrayList<Item>();

    private View rootView;

    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();
        return fragment;
    }

    public ListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_list, container, false);
        TextView titleName = (TextView) getActivity().findViewById(R.id.toolbar_title);
        titleName.setText("List");
        TextView edit = (TextView) getActivity().findViewById(R.id.edit);
        edit.setText("");
        edit.setVisibility(View.INVISIBLE);
        Button search = (Button) getActivity().findViewById(R.id.search);
        search.setBackgroundResource(R.drawable.ic_search_black_24dp);
        search.setVisibility(View.INVISIBLE);
        TextView save = (TextView) getActivity().findViewById(R.id.save);
        save.setText("");
        save.setVisibility(View.INVISIBLE);

        ListView listView = rootView.findViewById(R.id.listView);
        DBHelper dbHelper = new DBHelper(getActivity());
        items = dbHelper.getAllBeacons();
        final CustomAdapter adapter =new CustomAdapter(getActivity(), items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

            MainActivity.l=0;
            MainActivity.i=2;

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            WithInListFragment select1 = new WithInListFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Item.Column.ID, items.get(arg2).getBeacon_uuid());
            select1.setArguments(bundle);

            items.clear();
            transaction.replace(R.id.frame, select1);
            transaction.addToBackStack(null);
            transaction.commit();
            }
        });

        return rootView;
    }

}
