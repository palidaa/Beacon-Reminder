package com.example.palida.beacon_reminder;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.palida.beacon_reminder.altBeacon.BeaconReferenceApplication;
import com.example.palida.beacon_reminder.altBeacon.Scanner;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.Region;

import java.util.ArrayList;
import java.util.Collection;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlarmFragment extends Fragment {
    private View rootView;
    Collection<Beacon> beacons = null;
    ListView listView;

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
        CustomAdapterAlarm adapter = new CustomAdapterAlarm(getActivity(), ListFragment.items,beacons);
//        final CustomAdapter adapter =new CustomAdapter(getActivity(), ListFragment.name,ListFragment.pic);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

            MainActivity.l=2;
            MainActivity.i=2;

            WithInAlarmFragment.startTime1 = ListFragment.start_time.get(arg2);
            WithInAlarmFragment.endTime1 = ListFragment.end_time.get(arg2);
            WithInAlarmFragment.repeat1 = ListFragment.repeat.get(arg2);
            WithInAlarmFragment.label1 = ListFragment.label.get(arg2);
            WithInAlarmFragment.snooze1 = ListFragment.snooze.get(arg2);

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            WithInAlarmFragment select1 = new WithInAlarmFragment();
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


    @Override
    public void onResume() {
        super.onResume();
        ((BeaconReferenceApplication) getActivity().getApplicationContext()).setAlarmFragment(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        ((BeaconReferenceApplication) getActivity().getApplicationContext()).setAlarmFragment(null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((BeaconReferenceApplication) getActivity().getApplicationContext()).setAlarmFragment(null);
    }

    public void updateList(final Collection<Beacon> aBeacons){
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                CustomAdapterAlarm adapter = new CustomAdapterAlarm(getActivity(), ListFragment.items, aBeacons);
                listView.setAdapter(adapter);
                beacons=aBeacons;
            }
        });
    }

}
