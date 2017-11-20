package com.example.palida.beacon_reminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.altbeacon.beacon.Beacon;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Palida on 13-Nov-17.
 */

public class DBHelper extends SQLiteOpenHelper {

    private final String TAG = getClass().getSimpleName();
    private SQLiteDatabase sqLiteDatabase;
    String []day= {"Never","Every Sunday","Every Monday","Every Tuesday","Every Wednesday","Every Thursday","Every Friday","Every Saturday"};


    public DBHelper(Context context) {
        super(context, "beacon.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LIST_TABLE = String.format("CREATE TABLE %s " +
            "(%s TEXT PRIMARY KEY , %s TEXT, %s INTEGER, %s TEXT, %s TEXT ,%s INTEGER,%s TEXT, %s TEXT ,%s TEXT, %s TEXT ,%s INTEGER)",
                Item.TABLE,
                Item.Column.ID,
                Item.Column.NAME,
                Item.Column.PIC,
                Item.Column.DESCRIPTION,
                Item.Column.INSTALL,
                Item.Column.CHECKED,
                Item.Column.START_TIME,
                Item.Column.END_TIME,
                Item.Column.REPEAT,
                Item.Column.LABEL,
                Item.Column.SNOOZE
        );

        Log.i(TAG, CREATE_LIST_TABLE);
        db.execSQL(CREATE_LIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_FRIEND_TABLE = "DROP TABLE IF EXISTS " + Item.TABLE;

        db.execSQL(DROP_FRIEND_TABLE);

        Log.i(TAG, "Upgrade Database from " + oldVersion + " to " + newVersion);

        onCreate(db);

    }

    public List<String> getItemList() {
        List<String> name = new ArrayList<String>();

        sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query
                (Item.TABLE, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        while(!cursor.isAfterLast()) {

            name.add(cursor.getLong(0) + " " +
                    cursor.getString(1) + " " +
                    cursor.getString(2));

            cursor.moveToNext();
        }

        sqLiteDatabase.close();

        return name;
    }

    // Adding new
    public void addNewBeacon(Item beaconItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Item.Column.ID, beaconItem.getBeacon_uuid()); // uuid
        values.put(Item.Column.NAME, beaconItem.getName()); // name
        values.put(Item.Column.PIC, beaconItem.getPic());
        values.put(Item.Column.DESCRIPTION, beaconItem.getDescription());
        values.put(Item.Column.INSTALL, beaconItem.getInstall());

        values.put(Item.Column.CHECKED, beaconItem.getChecked());
        values.put(Item.Column.START_TIME, beaconItem.getStart_time());
        values.put(Item.Column.END_TIME, beaconItem.getEnd_time());
        values.put(Item.Column.REPEAT, beaconItem.getRepeat());
        values.put(Item.Column.LABEL, beaconItem.getLabel());
        values.put(Item.Column.SNOOZE, beaconItem.getSnooze());

        // Inserting Row
        db.insert(Item.TABLE, null, values);
        db.close(); // Closing database connection
    }

    // Deleting single beacon
    public void deleteBeacon(String beacon_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Item.TABLE, Item.Column.ID + " = ?",
                new String[] { beacon_id });
        db.close();
    }

    // Getting single beacon
    public HashMap getBeacon(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Item.TABLE, new String[] { Item.Column.ID,
                        Item.Column.NAME,
                        Item.Column.PIC,
                        Item.Column.DESCRIPTION,
                        Item.Column.INSTALL,
                        Item.Column.CHECKED,
                        Item.Column.START_TIME,
                        Item.Column.END_TIME,
                        Item.Column.REPEAT,
                        Item.Column.LABEL,
                        Item.Column.SNOOZE}, Item.Column.ID + "=?",
                new String[] { id }, null, null, null, null);

        HashMap resultItem = new HashMap();
        if (cursor != null && cursor.moveToFirst()) {
            resultItem.put(Item.Column.ID,cursor.getString(0));
            resultItem.put(Item.Column.NAME,cursor.getString(1));
            resultItem.put(Item.Column.PIC,cursor.getInt(2));
            resultItem.put(Item.Column.DESCRIPTION,cursor.getString(3));
            resultItem.put(Item.Column.INSTALL,cursor.getString(4));
            resultItem.put(Item.Column.CHECKED,cursor.getInt(5));
            resultItem.put(Item.Column.START_TIME,cursor.getString(6));
            resultItem.put(Item.Column.END_TIME,cursor.getString(7));
            resultItem.put(Item.Column.REPEAT,cursor.getString(8));
            resultItem.put(Item.Column.LABEL,cursor.getString(9));
            resultItem.put(Item.Column.SNOOZE,cursor.getInt(10));
        }
        cursor.close();
        db.close();
        // return beacon
        return resultItem;
    }

    // Getting All Beacons
    public List<Item> getAllBeacons() {
        List<Item> beaconList = new ArrayList<Item>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + Item.TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Item item = new Item();
                item.setBeacon_uuid(cursor.getString(0));
                item.setName(cursor.getString(1));
                item.setPic(cursor.getInt(2));
                item.setDescription(cursor.getString(3));
                item.setInstall(cursor.getString(4));
                item.setChecked(cursor.getInt(5));
                item.setStart_time(cursor.getString(6));
                item.setEnd_time(cursor.getString(7));
                item.setRepeat(cursor.getString(8));
                item.setLabel(cursor.getString(9));
                item.setSnooze(cursor.getInt(10));
                // Adding item to list
                beaconList.add(item);
            } while (cursor.moveToNext());
        }

        // return item list
        return beaconList;
    }

    public List<Item> getAllBeaconsAlarm() {
        List<Item> beaconList = new ArrayList<Item>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + Item.TABLE + " WHERE " +Item.Column.PIC+" not in (?,?)";

        String[] vauleQuery = {Integer.toString(R.drawable.door),Integer.toString(R.drawable.question)};

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, vauleQuery);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Item item = new Item();
                item.setBeacon_uuid(cursor.getString(0));
                item.setName(cursor.getString(1));
                item.setPic(cursor.getInt(2));
                item.setDescription(cursor.getString(3));
                item.setInstall(cursor.getString(4));
                item.setChecked(cursor.getInt(5));
                item.setStart_time(cursor.getString(6));
                item.setEnd_time(cursor.getString(7));
                item.setRepeat(cursor.getString(8));
                item.setLabel(cursor.getString(9));
                item.setSnooze(cursor.getInt(10));
                // Adding item to list
                beaconList.add(item);
            } while (cursor.moveToNext());
        }

        // return item list
        return beaconList;
    }

    public List<Item> getBeaconsFromPicType(int pic) {
        List<Item> beaconList = new ArrayList<Item>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + Item.TABLE + " WHERE "+Item.Column.PIC+" = ?";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{Integer.toString(pic)});

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Item item = new Item();
                item.setBeacon_uuid(cursor.getString(0));
                item.setName(cursor.getString(1));
                item.setPic(cursor.getInt(2));
                item.setDescription(cursor.getString(3));
                item.setInstall(cursor.getString(4));
                item.setChecked(cursor.getInt(5));
                item.setStart_time(cursor.getString(6));
                item.setEnd_time(cursor.getString(7));
                item.setRepeat(cursor.getString(8));
                item.setLabel(cursor.getString(9));
                item.setSnooze(cursor.getInt(10));
                // Adding item to list
                beaconList.add(item);
            } while (cursor.moveToNext());
        }

        // return item list
        return beaconList;
    }

    public List<Item> getCheckedBeacon() {
        List<Item> beaconList = new ArrayList<Item>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + Item.TABLE + " WHERE "+Item.Column.CHECKED+" = ? and "
                +Item.Column.START_TIME+" <= ? and "+Item.Column.END_TIME+" >= ? and "
                +Item.Column.PIC+" not in (?,?) and ("+Item.Column.REPEAT+" = ? or "+Item.Column.REPEAT+" = ? )";
        Calendar c = Calendar.getInstance();
        String time = "";
        if(c.get(Calendar.HOUR_OF_DAY)<10) time += "0"+c.get(Calendar.HOUR_OF_DAY);
        else time += Integer.toString(c.get(Calendar.HOUR_OF_DAY));
        if(c.get(Calendar.MINUTE)<10) time += ":0"+c.get(Calendar.MINUTE);
        else time += ":"+Integer.toString(c.get(Calendar.MINUTE));

        String[] vauleQuery = {"1",time,time,Integer.toString(R.drawable.door),Integer.toString(R.drawable.question),day[c.get(Calendar.DAY_OF_WEEK)],day[0]};

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, vauleQuery);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Item item = new Item();
                item.setBeacon_uuid(cursor.getString(0));
                item.setName(cursor.getString(1));
                item.setPic(cursor.getInt(2));
                item.setDescription(cursor.getString(3));
                item.setInstall(cursor.getString(4));
                item.setChecked(cursor.getInt(5));
                item.setStart_time(cursor.getString(6));
                item.setEnd_time(cursor.getString(7));
                item.setRepeat(cursor.getString(8));
                item.setLabel(cursor.getString(9));
                item.setSnooze(cursor.getInt(10));
                // Adding item to list
                beaconList.add(item);
            } while (cursor.moveToNext());
        }

        // return item list
        return beaconList;
    }

    // Updating single beacon
    public int updateBeacon(Item beaconItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Item.Column.ID, beaconItem.getBeacon_uuid()); // uuid
        values.put(Item.Column.NAME, beaconItem.getName()); // name
        values.put(Item.Column.PIC, beaconItem.getPic());
        values.put(Item.Column.DESCRIPTION, beaconItem.getDescription());
        values.put(Item.Column.INSTALL, beaconItem.getInstall());
        values.put(Item.Column.CHECKED, beaconItem.getChecked());
        values.put(Item.Column.START_TIME, beaconItem.getStart_time());
        values.put(Item.Column.END_TIME, beaconItem.getEnd_time());
        values.put(Item.Column.REPEAT, beaconItem.getRepeat());
        values.put(Item.Column.LABEL, beaconItem.getLabel());
        values.put(Item.Column.SNOOZE, beaconItem.getSnooze());

        // updating row
        return db.update(Item.TABLE, values, Item.Column.ID + " = ?",
                new String[] { beaconItem.getBeacon_uuid() });
    }

}
