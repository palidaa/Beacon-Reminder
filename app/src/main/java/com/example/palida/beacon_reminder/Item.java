package com.example.palida.beacon_reminder;

import android.provider.BaseColumns;

/**
 * Created by Palida on 13-Nov-17.
 */

public class Item {
    public static final String DATABASE_NAME = "bacon.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE = "list";

    public class Column {
        public static final String ID = BaseColumns._ID;
        public static final String NAME = "name";
        public static final String PIC = "pic";
        public static final String DESCRIPTION = "desription";
        public static final String INSTALL = "install";
    }
}
