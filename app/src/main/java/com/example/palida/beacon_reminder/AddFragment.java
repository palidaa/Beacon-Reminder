package com.example.palida.beacon_reminder;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment implements BeaconConsumer {
    private View rootView;
    public static List<String> name = new ArrayList<String>();
    public static List<Integer> pic = new ArrayList<Integer>();
    private ListView listView;
    private static final String REGION_ID = "Beacon_scanner_region";

    private Context context;
    private BeaconManager beaconManager;


    public AddFragment() {
        // Required empty public constructor
    }

    private void updateList(Collection<Beacon> beacons) {
//        if(name.isEmpty()) {
//            name.add("ID 000xxxx00");
//            name.add("ID 000xxxx00");
//            name.add("ID 000xxxx00");
//            for (int i = 0; i < name.size(); i++) {
//                pic.add(R.drawable.question);
//            }
//        }
        name.clear();
        pic.clear();
        for (Beacon beacon : beacons){
            name.add(beacon.getId1().toString()+" "+beacon.getId2().toString()+" "+beacon.getId3().toString());
            pic.add(R.drawable.question);
        }

        CustomAdapter adapter =new CustomAdapter(getActivity(), name,pic);
        listView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_list, container, false);
        // To detect proprietary beacons, you must add a line like below corresponding to your beacon
        // type.  Do a web search for "setBeaconLayout" to get the proper expression.
        context = rootView.getContext();
        beaconManager = BeaconManager.getInstanceForApplication(getActivity());
        beaconManager.bind(this);
        setUpBeaconManager();
        setBeaconLayouts();

        TextView titleName = (TextView) getActivity().findViewById(R.id.toolbar_title);
        titleName.setText("Add Item");
        TextView edit = (TextView) getActivity().findViewById(R.id.edit);
        edit.setText("");
        Button search = (Button) getActivity().findViewById(R.id.search);
        search.setBackgroundColor(Color.TRANSPARENT);
        TextView save = (TextView) getActivity().findViewById(R.id.save);
        save.setText("");

        listView = rootView.findViewById(R.id.listView);

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

    private void setUpBeaconManager() {
        // Sets scanning periods.
        beaconManager.setForegroundScanPeriod(1100);
        // Sets between scanning periods.
        beaconManager.setForegroundBetweenScanPeriod(0);
        // Initializing cache and setting tracking age.
        BeaconManager.setUseTrackingCache(true);
        beaconManager.setMaxTrackingAge(5000);
    }


    private void setBeaconLayouts() {
        beaconManager.getBeaconParsers().clear();
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout(BeaconParser.ALTBEACON_LAYOUT));
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout(BeaconParser.EDDYSTONE_UID_LAYOUT));
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout(BeaconParser.EDDYSTONE_TLM_LAYOUT));
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout(BeaconParser.EDDYSTONE_URL_LAYOUT));
    }

    @Override
    public void onBeaconServiceConnect() {
       beaconManager.setRangeNotifier(new RangeNotifier() {
        @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> collection, Region region) {
            //onScanBeaconsCallback.onScanBeacons(collection);
            if(collection.size()>0){
                updateList(collection);
            }
        }
    });

        try {
        // Starting ranging of beacons in our defined region.
        Region region = new Region(REGION_ID, null, null, null);
        beaconManager.startRangingBeaconsInRegion(region);
    } catch (RemoteException e) {
        e.printStackTrace();
    }
    }

    @Override
    public Context getApplicationContext() {
        return context.getApplicationContext();
    }

    @Override
    public void unbindService(ServiceConnection serviceConnection) {
        context.unbindService(serviceConnection);
    }

    @Override
    public boolean bindService(Intent intent, ServiceConnection serviceConnection, int i) {
        return context.bindService(intent, serviceConnection, i);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        beaconManager.unbind(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        beaconManager.unbind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        beaconManager.bind(this);
    }
}
