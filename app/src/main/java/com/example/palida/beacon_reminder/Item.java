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
        public static final String CHECKED = "checked";
        public static final String START_TIME = "start_time";
        public static final String END_TIME = "end_time";
        public static final String REPEAT = "repeat";
        public static final String LABEL = "label";
        public static final String SNOOZE = "snooze";
    }

    private String beacon_uuid;
    private String name;
    private Integer pic;
    private String description;
    private String install;
    private Integer checked;
    private String start_time;
    private String end_time;
    private String repeat;
    private String label;
    private Integer snooze;

    public Item() {
    }

    public Item(Item item) {
        this.beacon_uuid = item.getBeacon_uuid();
        this.name = item.getName();
        this.pic = item.getPic();
        this.description = item.getDescription();
        this.install = item.getInstall();
        this.checked = item.getChecked();
        this.start_time = item.getStart_time();
        this.end_time = item.getEnd_time();
        this.repeat = item.getRepeat();
        this.label = item.getLabel();
        this.snooze = item.getSnooze();
    }

    public Item(String beacon_uuid, String name, Integer pic, String description, String install, int checked, String start_time, String end_time, String repeat, String label, int snooze) {
        this.beacon_uuid = beacon_uuid;
        this.name = name;
        this.pic = pic;
        this.description = description;
        this.install = install;
        this.checked = checked;
        this.start_time = start_time;
        this.end_time = end_time;
        this.repeat = repeat;
        this.label = label;
        this.snooze = snooze;
    }


    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }

    public Integer getSnooze() {
        return snooze;
    }

    public void setSnooze(Integer snooze) {
        this.snooze = snooze;
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
