package com.example.palida.beacon_reminder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.Region;

import java.util.Collection;
import java.util.List;

/**
 * Created by Palida on 13-Nov-17.
 */

public class CustomAdapterAlarm extends BaseAdapter{
    Context mContext;
    private List<Item> items;
    private Collection<Beacon> inBeacon;

    public CustomAdapterAlarm(Context context, List<Item> items,Collection<Beacon> inBeacon) {
        this.mContext= context;
        this.items = items;
        this.inBeacon=inBeacon;
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

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater mInflater =
                (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null)
            view = mInflater.inflate(R.layout.singlerow_alarm, parent, false);

        TextView textView = (TextView)view.findViewById(R.id.name);
        textView.setText(items.get(position).getName());

        ImageView imageView = (ImageView)view.findViewById(R.id.pic);
        imageView.setBackgroundResource(items.get(position).getPic());

        Switch s = (Switch) view.findViewById(R.id.switch1);
        if(inBeacon!=null && inBeacon.contains(new Beacon.Builder().setId1(items.get(position).getBeacon_uuid()).setId2("0").setId3("0").build())){
            s.setChecked(true);
        }else s.setChecked(false);

        LinearLayout layout = (LinearLayout)view.findViewById(R.id.layout);
        if(position%2==1)
            layout.setBackgroundColor(mContext.getResources().getColor(R.color.one));
        else
            layout.setBackgroundColor(mContext.getResources().getColor(R.color.two));

        return view;
    }
}
