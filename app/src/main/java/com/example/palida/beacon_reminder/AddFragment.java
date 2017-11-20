
package com.example.palida.beacon_reminder;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.palida.beacon_reminder.altBeacon.Scanner;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.Region;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;


/**
 * Created by vitas on 8/11/15.
 */
public class AddFragment extends Fragment implements Scanner.OnScanBeaconsListener, View.OnClickListener {

    public static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;

    protected Region mRegion;
    protected boolean isScanning;

    protected BeaconManager mBeaconManager;
    private Scanner scanner;
//    private BeaconAdapter.OnBeaconSelectedListener onBeaconSelectedCallback;
    private List<Beacon> beacons = new ArrayList<>();
    private List<Integer> beaconIcons = new ArrayList<>();
    private List<String> beaconNames = new ArrayList<>();
    private ProgressBar progressBar;
    private Button btn_scan;

    Toolbar toolbar;
    RecyclerView beaconRecycler;
    private DBHelper dbHelper;



    //''''''''''''''''''''''''''''''''''''
        private View rootView;
//    private Button btn_scan;
    public static List<String> name = new ArrayList<String>();
    public static List<Integer> pic = new ArrayList<Integer>();
    ListView listView;

    public static AddFragment newInstance() {
        return new AddFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        try {
//            onBeaconSelectedCallback = (BeaconAdapter.OnBeaconSelectedListener) context;
//        } catch (ClassCastException notImplementedException) {
//            throw new ClassCastException(context.toString()
//                    + " must implement OnBeaconSelectedListener");
//        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isScanning = false;
        dbHelper = new DBHelper(getActivity());
        beacons.clear();
        beaconIcons.clear();
        beaconNames.clear();
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_COARSE_LOCATION: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    final Dialog permFailedDialog = new AlertDialog.Builder(getActivity())
                            .setTitle(R.string.dialog_error_functionality_limited)
                            .setMessage(R.string.error_message_location_access_not_granted)
                            .setNeutralButton(R.string.dialog_action_ok, null).create();

                    permFailedDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            //finish();
                        }
                    });
                    permFailedDialog.show();
                }
                return;
            }
        }
    }

    @TargetApi(23)
    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Android M Permission check
            if (getActivity().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                final Dialog permDialog = new AlertDialog.Builder(getActivity())
                        .setTitle(R.string.dialog_error_need_location_access)
                        .setMessage(R.string.error_message_location_access_need_tobe_granted)
                        .setNeutralButton(R.string.dialog_action_ok, null).create();

                permDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @TargetApi(23)
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                                PERMISSION_REQUEST_COARSE_LOCATION);
                    }
                });
                permDialog.show();
            }
        }
    }

    @TargetApi(18)
    private void verifyBluetooth() {
        try {
            if (!mBeaconManager.checkAvailability()) {

                new AlertDialog.Builder(getActivity())
                        .setTitle(getString(R.string.bluetooth_not_enabled))
                        .setMessage(getString(R.string.please_enable_bluetooth))
                        .setPositiveButton(R.string.settings, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Initializing intent to go to bluetooth settings.
                                Intent bltSettingsIntent = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
                                startActivity(bltSettingsIntent);
                            }
                        })
                        .show();
            }
        } catch (RuntimeException e) {

            final Dialog bleDialog = new AlertDialog.Builder(getActivity())
                    .setTitle(R.string.dialog_error_ble_not_supported)
                    .setMessage(R.string.error_message_bluetooth_le_not_supported)
                    .setNeutralButton(R.string.dialog_action_ok, null).create();

            bleDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    getActivity().finish();
                    System.exit(0);
                }
            });
            bleDialog.show();
        }
    }

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        View beaconListView = inflater.inflate(R.layout.fragment_add, container, false);
//        btn_scan = getActivity().findViewById(R.id.search);
//        btn_scan.setOnClickListener(this);
//
//        beaconRecycler = (RecyclerView) beaconListView.findViewById(R.id.beacon_recycler_view);
//        progressBar = (ProgressBar) beaconListView.findViewById(R.id.progress_indicator);
//        // Setting linear layout manager as layout manager for the beacon recycler view.
//        beaconRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
//        // Getting instance of beacon manager.
//        mBeaconManager = BeaconManager.getInstanceForApplication(getActivity());
//        // Initializing scan service.
//        initBeaconScanService();
//        // Updates user interface so that all the right views are displayed.
//        updateUI();
//
//        return beaconListView;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_add, container, false);
        TextView titleName = (TextView) getActivity().findViewById(R.id.toolbar_title);
        titleName.setText("Add Item");
        progressBar = rootView.findViewById(R.id.progress_indicator);

        TextView edit = (TextView) getActivity().findViewById(R.id.edit);
        edit.setVisibility(View.INVISIBLE);

        btn_scan = (Button) getActivity().findViewById(R.id.search);
        btn_scan.setOnClickListener(this);
        btn_scan.setVisibility(View.VISIBLE);

        TextView save = (TextView) getActivity().findViewById(R.id.save);
        save.setVisibility(View.INVISIBLE);


         listView = rootView.findViewById(R.id.listView);
        final CustomAdapterAdd adapter = new CustomAdapterAdd(getActivity(),beacons, beaconNames, beaconIcons);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                stopScanning();
                MainActivity.l = 1;
                MainActivity.i = 2;

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                WithInAddFragment select1 = new WithInAddFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("pos", arg2);
                bundle.putString(Item.Column.ID,beacons.get(arg2).getId1().toString());
                select1.setArguments(bundle);
                transaction.replace(R.id.frame, select1);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

                mBeaconManager = BeaconManager.getInstanceForApplication(getActivity());
        // Initializing scan service.
        initBeaconScanService();
        // Updates user interface so that all the right views are displayed.
        updateUI();

        return rootView;
    }
    private void initBeaconScanService() {
        scanner = new Scanner(getActivity(), this, mBeaconManager);
    }

    private void updateUI() {
//        beaconRecycler.setAdapter(new BeaconAdapter(beacons, onBeaconSelectedCallback, getActivity(), beaconNames, beaconIcons));
        listView.setAdapter(new CustomAdapterAdd(getActivity(),beacons, beaconNames, beaconIcons));
        setSlidingList(beacons);
    }

    private void setSlidingList(List<Beacon> beacons) {
        if (!beacons.isEmpty()) {
            progressBar.setVisibility(View.INVISIBLE);
        } else if (beacons.isEmpty() && !isScanning) {
            progressBar.setVisibility(View.INVISIBLE);

        } else {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    private void compareBeaconWithDb(Beacon beacon){
       HashMap result = dbHelper.getBeacon(beacon.getId1().toString());
        // if match with db
        if(!result.isEmpty()){
            beaconNames.add((String) result.get(Item.Column.NAME));
            beaconIcons.add((Integer) result.get(Item.Column.PIC));
        }
        else{
            beaconNames.add("Unknown");
            beaconIcons.add(R.drawable.question);
        }
    }


    @Override
    public void onScanBeacons(Collection<Beacon> beacons) {
        if (this.beacons.isEmpty()) {
            this.beacons = (List<Beacon>) beacons;
            for (Beacon b : beacons) {
                compareBeaconWithDb(b);
            }
        }else {
            for (Beacon b : beacons) {
                if (!this.beacons.contains(b)) {
                    this.beacons.add(b);
                    compareBeaconWithDb(b);
                }
            }
        }
        if (isAdded()) getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (isScanning) {
                    updateUI();
                }
            }
        });
    }

    private void startScanning() {
        if (!mBeaconManager.checkAvailability()) {
            verifyBluetooth();
        } else {
            checkPermissions();
            isScanning = true;
            mBeaconManager.bind(scanner); // Beacon manager binds the beacon consumer and starts service.
            if (mBeaconManager.isBound(scanner))
                mBeaconManager.setBackgroundMode(false);
            progressBar.setVisibility(View.VISIBLE);
            btn_scan.setBackgroundResource(R.drawable.ic_menu_item_stop);
        }
    }

    private void stopScanning() {
        isScanning = false;
//        beacons.clear();
        if (mBeaconManager.isBound(scanner))
            mBeaconManager.setBackgroundMode(true);
        mBeaconManager.unbind(scanner); // Beacon manager unbinds the beacon consumer and stops service.
        progressBar.setVisibility(View.INVISIBLE);
        btn_scan.setBackgroundResource(R.drawable.ic_search_black_24dp);

    }


    @Override
    public void onPause() {
        super.onPause();
        if (isScanning) mBeaconManager.unbind(scanner);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mBeaconManager.isBound(scanner))
            mBeaconManager.setBackgroundMode(true);
    }

    public void onStartStopEvent() {
        if (!isScanning) {
            beacons.clear();
            beaconNames.clear();
            beaconIcons.clear();
            startScanning();
        } else {
            stopScanning();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search:
                onStartStopEvent();
                break;
        }
    }
}
//
//
//import android.graphics.Color;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentTransaction;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
///**
// * A simple {@link Fragment} subclass.
// */
//public class AddFragment extends Fragment implements View.OnClickListener {
//    private View rootView;
//    private Button btn_search;
//    public static List<String> name = new ArrayList<String>();
//    public static List<Integer> pic = new ArrayList<Integer>();
//
//    public AddFragment() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        rootView = inflater.inflate(R.layout.fragment_list, container, false);
//        TextView titleName = (TextView) getActivity().findViewById(R.id.toolbar_title);
//        titleName.setText("Add Item");
//        TextView edit = (TextView) getActivity().findViewById(R.id.edit);
//        edit.setText("");
//        btn_search = (Button) getActivity().findViewById(R.id.search);
//        btn_search.setOnClickListener(this);
////        search.setBackgroundColor(Color.TRANSPARENT);
//        TextView save = (TextView) getActivity().findViewById(R.id.save);
//        save.setText("");
//
//        if (name.isEmpty()) {
//            name.add("ID 000xxxx00");
//            name.add("ID 000xxxx00");
//            name.add("ID 000xxxx00");
//            for (int i = 0; i < name.size(); i++) {
//                pic.add(R.drawable.question);
//            }
//        }
//
//        ListView listView = rootView.findViewById(R.id.listView);
//        final CustomAdapter adapter = new CustomAdapter(getActivity(), name, pic);
//        listView.setAdapter(adapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//                MainActivity.l = 1;
//                MainActivity.i = 2;
//
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                WithInAddFragment select1 = new WithInAddFragment();
//                Bundle bundle = new Bundle();
//                bundle.putInt("pos", arg2);
//                select1.setArguments(bundle);
//                transaction.replace(R.id.frame, select1);
//                transaction.addToBackStack(null);
//                transaction.commit();
//            }
//        });
//
//        return rootView;
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.search:
//                break;
//        }
//    }
//}
