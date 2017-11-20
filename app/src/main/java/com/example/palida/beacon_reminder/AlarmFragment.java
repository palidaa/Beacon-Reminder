package com.example.palida.beacon_reminder;


import android.app.AlarmManager;
import android.app.PendingIntent;
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

import org.altbeacon.beacon.Beacon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlarmFragment extends Fragment {
    private View rootView;
    Collection<Beacon> beacons = null;
    ListView listView;
    public static List<Item> items = new ArrayList<Item>();

    AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    public AlarmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_list, container, false);
        TextView titleName = (TextView) getActivity().findViewById(R.id.toolbar_title);
        titleName.setText("Alarm");
        TextView edit = (TextView) getActivity().findViewById(R.id.edit);
        edit.setText("");
        edit.setVisibility(View.INVISIBLE);
        Button search = (Button) getActivity().findViewById(R.id.search);
        search.setBackgroundResource(R.drawable.ic_search_black_24dp);
        search.setVisibility(View.INVISIBLE);
        TextView save = (TextView) getActivity().findViewById(R.id.save);
        save.setText("");
        save.setVisibility(View.INVISIBLE);

        listView = rootView.findViewById(R.id.listView);
        DBHelper dbHelper = new DBHelper(getActivity());
        items = dbHelper.getAllBeaconsAlarm();
        final CustomAdapterAlarm adapter = new CustomAdapterAlarm(getActivity(), items);
//        final CustomAdapter adapter =new CustomAdapter(getActivity(), ListFragment.name,ListFragment.pic);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                MainActivity.l = 2;
                MainActivity.i = 2;

//            WithInAlarmFragment.startTime1 = ListFragment.start_time.get(arg2);
//            WithInAlarmFragment.endTime1 = ListFragment.end_time.get(arg2);
//            WithInAlarmFragment.repeat1 = ListFragment.repeat.get(arg2);
//            WithInAlarmFragment.label1 = ListFragment.label.get(arg2);
//            WithInAlarmFragment.snooze1 = ListFragment.snooze.get(arg2);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                WithInAlarmFragment select1 = new WithInAlarmFragment();
                Bundle bundle = new Bundle();
//            bundle.putInt("pos", arg2);
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
