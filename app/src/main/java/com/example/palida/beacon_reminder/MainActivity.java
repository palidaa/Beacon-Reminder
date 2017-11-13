package com.example.palida.beacon_reminder;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static int i=0,j=0,l=0;
    private BottomNavigationView navigation;
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
                    AddFragment select2 = new AddFragment();
                    transaction.replace(R.id.frame, select2);
                    transaction.commit();
                    return true;
                case R.id.navigation_clock:
                    AlarmFragment select3 = new AlarmFragment();
                    transaction.replace(R.id.frame, select3);
                    transaction.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
