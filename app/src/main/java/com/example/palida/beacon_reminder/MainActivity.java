package com.example.palida.beacon_reminder;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static int i=0,j=0,l=0;
    private BottomNavigationView navigation;
    public static BeaconManager beaconManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            TextView title = (TextView)findViewById(R.id.toolbar_title);
            switch (item.getItemId()) {
                case R.id.navigation_list:
                    ListFragment select1 = new ListFragment();
                    transaction.replace(R.id.frame, select1);
                    transaction.commit();
                    return true;
                case R.id.navigation_add:
                    if (!beaconManager.checkAvailability()) {
                        requestBluetooth();
                    } else {
                        AddFragment select2 = new AddFragment();
                        transaction.replace(R.id.frame, select2);
                        transaction.commit();
                        return true;
                    }
                    return false;
                case R.id.navigation_clock:
                    AlarmFragment select3 = new AlarmFragment();
                    transaction.replace(R.id.frame, select3);
                    transaction.commit();
                    return true;
            }
            return false;
        }
    };

    private void requestBluetooth() {
        new AlertDialog.Builder(this)
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        beaconManager = BeaconManager.getInstanceForApplication(this);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, ListFragment.newInstance());
        transaction.commit();
        navigation.getMenu().getItem(0).setChecked(true);


    }


    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() == 0) {
            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            MainActivity.this.finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }else{
            navigation.getMenu().getItem(l).setChecked(true);
            super.onBackPressed();
        }
    }
}
