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

import java.util.List;

/**
 * Created by Palida on 13-Nov-17.
 */

public class CustomAdapterAlarm extends BaseAdapter{
        Context mContext;
        private List<String> name;
        private List<Integer> pic;
        private List<Boolean> checked;

    public CustomAdapterAlarm(Context context, List<String> name, List<Integer> pic,List<Boolean> checked) {
        this.mContext= context;
        this.name = name;
        this.pic = pic;
        this.checked = checked;
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
            view = mInflater.inflate(R.layout.singlerow_alarm, parent, false);

        TextView textView = (TextView)view.findViewById(R.id.name);
        textView.setText(name.get(position));

        ImageView imageView = (ImageView)view.findViewById(R.id.pic);
        imageView.setBackgroundResource(pic.get(position));

        Switch s = (Switch) view.findViewById(R.id.switch1);
        s.setChecked(checked.get(position));

        ListFragment.checked.set(position,s.isChecked());

        LinearLayout layout = (LinearLayout)view.findViewById(R.id.layout);
        if(position%2==1)
            layout.setBackgroundColor(mContext.getResources().getColor(R.color.one));
        else
            layout.setBackgroundColor(mContext.getResources().getColor(R.color.two));

        return view;
    }
}
