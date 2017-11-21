//package com.example.palida.beacon_reminder;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import java.util.List;
//
///**
// * Created by Palida on 10-Nov-17.
// */
//
//public class CustomAdapter extends BaseAdapter {
//    Context mContext;
//    private List<String> name;
//    private List<Integer> pic;
//
//    public CustomAdapter(Context context, List<String> name, List<Integer> pic) {
//        this.mContext= context;
//        this.name = name;
//        this.pic = pic;
//    }
//
//    public int getCount() {
//        return name.size();
//    }
//
//    public Object getItem(int position) {
//        return null;
//    }
//
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    public View getView(int position, View view, ViewGroup parent) {
//        LayoutInflater mInflater =
//                (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        if(view == null)
//            view = mInflater.inflate(R.layout.singlerow, parent, false);
//
//        TextView textView = (TextView)view.findViewById(R.id.name);
//        textView.setText(name.get(position));
//
//        ImageView imageView = (ImageView)view.findViewById(R.id.pic);
//        imageView.setBackgroundResource(pic.get(position));
//
//        LinearLayout layout = (LinearLayout)view.findViewById(R.id.layout);
//        if(position%2==1)
//            layout.setBackgroundColor(mContext.getResources().getColor(R.color.one));
//        else
//            layout.setBackgroundColor(mContext.getResources().getColor(R.color.two));
//
//        return view;
//    }
//}

package com.example.palida.beacon_reminder;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.altbeacon.beacon.Beacon;

import java.util.Collection;
import java.util.List;

/**
 * Created by Palida on 10-Nov-17.
 */

public class CustomAdapter extends BaseAdapter {
    Context mContext;
    private List<Item> items;
    private Collection<Beacon> beacons;

    public CustomAdapter(Context context, List<Item> items, Collection<Beacon> beacons) {
        this.mContext= context;
        this.items = items;
        this.beacons=beacons;
//        this.name = name;
//        this.pic = pic;
    }

    public int getCount() {
        return items.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater mInflater =
                (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null)
            view = mInflater.inflate(R.layout.singlerow, parent, false);

        TextView textView = (TextView)view.findViewById(R.id.name);
        textView.setText(items.get(position).getName());

        ImageView imageView = (ImageView)view.findViewById(R.id.pic);
        imageView.setBackgroundResource(items.get(position).getPic());

        LinearLayout layout = (LinearLayout)view.findViewById(R.id.layout);
        if(beacons!=null && beacons.contains(new Beacon.Builder().setId1(items.get(position).getBeacon_uuid()).setId2("0").setId3("0").build())){
            if (position % 2 == 1)
                layout.setBackgroundColor(mContext.getResources().getColor(R.color.one));
            else
                layout.setBackgroundColor(mContext.getResources().getColor(R.color.two));
        }else {
            layout.setBackgroundColor(mContext.getResources().getColor(R.color.beaconInvisible));
        }
        return view;
    }
}

