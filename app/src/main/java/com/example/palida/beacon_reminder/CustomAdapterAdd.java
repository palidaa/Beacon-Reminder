package com.example.palida.beacon_reminder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.altbeacon.beacon.Beacon;

import java.util.List;

/**
 * Created by Palida on 10-Nov-17.
 */

public class CustomAdapterAdd extends BaseAdapter {
    Context mContext;
    private List<Beacon> beacons;
    private List<String> name;
    private List<Integer> pic;

    public CustomAdapterAdd(Context context, List<Beacon> beacons, List<String> name, List<Integer> pic) {
        this.mContext= context;
        this.beacons = beacons;
        this.name = name;
        this.pic = pic;
    }

    public int getCount() {
        return name.size();
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
            view = mInflater.inflate(R.layout.singlerow_add, parent, false);

        TextView textView = (TextView)view.findViewById(R.id.id);
        textView.setText(beacons.get(position).getId1().toString());

        TextView textView2 = (TextView)view.findViewById(R.id.name);
        textView2.setText(name.get(position));

        ImageView imageView = (ImageView)view.findViewById(R.id.pic);
        imageView.setBackgroundResource(pic.get(position));

        LinearLayout layout = (LinearLayout)view.findViewById(R.id.layout);
        if(position%2==1)
            layout.setBackgroundColor(mContext.getResources().getColor(R.color.one));
        else
            layout.setBackgroundColor(mContext.getResources().getColor(R.color.two));

        return view;
    }
}
