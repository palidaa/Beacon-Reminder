package com.example.palida.beacon_reminder.altBeacon;

import android.app.Application;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.RemoteException;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.palida.beacon_reminder.DBHelper;
import com.example.palida.beacon_reminder.Helper.AlarmHelper;
import com.example.palida.beacon_reminder.Item;
import com.example.palida.beacon_reminder.MainActivity;
import com.example.palida.beacon_reminder.R;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.powersave.BackgroundPowerSaver;
import org.altbeacon.beacon.startup.BootstrapNotifier;
import org.altbeacon.beacon.startup.RegionBootstrap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by dyoung on 12/13/13.
 */
public class BeaconReferenceApplication extends Application implements BootstrapNotifier,RangeNotifier,BeaconConsumer {
    private static final String TAG = "BeaconReferenceApp";
    private RegionBootstrap regionBootstrap;
    private BackgroundPowerSaver backgroundPowerSaver;
    BeaconManager beaconManager;
    private SharedPreferences preferences;
    private ArrayList<Beacon> doorBeacons = new ArrayList<>();
    private ArrayList<Beacon> homeBeacons = new ArrayList<>();
    private boolean isEnterDoorRegion;
    private boolean isSleep;
    private Collection<Beacon> beacons;
    private List<Item> checkedItem;
    Region region;

    public void onCreate() {
        super.onCreate();
        beaconManager = BeaconManager.getInstanceForApplication(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        updateItemList();

        isEnterDoorRegion=false;
        isSleep=false;

        Log.d(TAG, "setting up background monitoring for beacons and power saving");
        // wake up the app when a beacon is seen
        region = new Region("backgroundRegion",
                null, null, null);
        regionBootstrap = new RegionBootstrap(this, region);
        // simply constructing this class and holding a reference to it in your custom Application
        // class will automatically cause the BeaconLibrary to save battery whenever the application
        // is not visible.  This reduces bluetooth power usage by about 60%

        backgroundPowerSaver = new BackgroundPowerSaver(this);

        // If you wish to test beacon detection in the Android Emulator, you can use code like this:
        // BeaconManager.setBeaconSimulator(new TimedBeaconSimulator() );
        // ((TimedBeaconSimulator) BeaconManager.getBeaconSimulator()).createTimedSimulatedBeacons();
        beaconManager.setBackgroundBetweenScanPeriod(7100);
        beaconManager.setForegroundBetweenScanPeriod(1000);
        //beaconManager.setBackgroundScanPeriod(5100);
        beaconManager.bind(this);
    }

    public void updateItemList(){
        DBHelper dbHelper = new DBHelper(getApplicationContext());

        List<Item> items = dbHelper.getBeaconsFromPicType(R.drawable.door);
        for (Item item:items) {
            doorBeacons.add(new Beacon.Builder().setId1(item.getBeacon_uuid()).setId2("0").setId3("0").build());
        }
        items = dbHelper.getBeaconsFromPicType(R.drawable.question);
        for (Item item:items) {
            homeBeacons.add(new Beacon.Builder().setId1(item.getBeacon_uuid()).setId2("0").setId3("0").build());
        }
    }

    @Override
    public void didEnterRegion(Region arg0) {
        // In this example, this class sends a notification to the user whenever a Beacon
        // matching a Region (defined above) are first seen.
        try {
            beaconManager.startRangingBeaconsInRegion(arg0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        //beaconManager.bind(scanner);
    }

    @Override
    public void didExitRegion(Region region) {
        if(!isEnterDoorRegion) {
            try {
                beaconManager.stopRangingBeaconsInRegion(region);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return;
        }
    }

    @Override
    public void didDetermineStateForRegion(int state, Region region) {
        Log.d(TAG, "determenie Sending notification.");
        if(state==1){
            this.didEnterRegion(region);
        }
    }

    private void leaveHome() {
        String haveBeacons = "";
        updateItemList();
        checkedItem = new DBHelper(getApplicationContext()).getCheckedBeacon();
        ArrayList<Item> lossItem = new ArrayList<>();
        for (Item item : checkedItem) {
            if (beacons.contains(new Beacon.Builder().setId1(item.getBeacon_uuid()).setId2("0").setId3("0").build())) {
                haveBeacons += item.getName() + " ";
            } else {
                lossItem.add(item);
            }
        }
        if(lossItem.size()>0){
            String message = "forget arai mai?";
            sendNotification(message);
        }else{
            setSleep();
        }

    }

    private void sendNotification(String message){
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setContentTitle("Beacon Reminder")
                        .setContentText(message)
                        .setSmallIcon(R.drawable.icon   );

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntent(new Intent(this, MainActivity.class));
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        builder.setContentIntent(resultPendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());

        //call alarm's function to check these beacons are in range alarm's period or not.
        //should call function after you check that these beacons are far from owner
        //then alert that he forget them.
        AlarmHelper alarmHelper = new AlarmHelper(getApplicationContext());
//        Log.e("Beacon size", " "+ beaconItem.size());
        alarmHelper.checkInAlarmPeriod(beaconItem);
    }

    private void setSleep(){
        isEnterDoorRegion=false;
        isSleep=true;
        beaconManager.setBackgroundBetweenScanPeriod(10800000);
        try {
            beaconManager.updateScanPeriods();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void didRangeBeaconsInRegion(Collection<Beacon> collection, Region region) {
        beacons = collection;
        if(isSleep){
            for (Beacon beacon:homeBeacons) {
                if(collection.contains(beacon)){
                    beaconManager.setBackgroundBetweenScanPeriod(10100);
                    try {
                        beaconManager.updateScanPeriods();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    isSleep = false;
                    return;
                }
            }
        }else{
            if(!isEnterDoorRegion && collection.size()==0) {
                try {
                    beaconManager.stopRangingBeaconsInRegion(region);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                return;
            }

            if(!isEnterDoorRegion){
                for (Beacon beacon:doorBeacons) {
                    if(collection.contains(beacon)){
                        isEnterDoorRegion = true;
                        return;
                    }
                }
            }else{
                for (Beacon beacon:doorBeacons) {
                    if(collection.contains(beacon)){
                        return;
                    }
                }
                for (Beacon beacon:homeBeacons) {
                    if(collection.contains(beacon)){
                        isEnterDoorRegion = false;
                        return;
                    }
                }
                Log.d(TAG, "Sending notification.");
                leaveHome();
            }
        }

    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.addRangeNotifier(this);
    }
}