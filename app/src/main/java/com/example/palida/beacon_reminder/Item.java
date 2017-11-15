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

    private String beacon_uuid;
    private String name;
    private Integer pic;
    private String description;
    private String install;

    public Item() {
    }

    public Item(String beacon_uuid, String name, Integer pic, String description, String install) {
        this.beacon_uuid = beacon_uuid;
        this.name = name;
        this.pic = pic;
        this.description = description;
        this.install = install;
    }

    public String getBeacon_uuid() {
        return beacon_uuid;
    }

    public void setBeacon_uuid(String beacon_uuid) {
        this.beacon_uuid = beacon_uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPic() {
        return pic;
    }

    public void setPic(Integer pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstall() {
        return install;
    }

    public void setInstall(String install) {
        this.install = install;
    }

}
