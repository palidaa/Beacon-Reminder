package com.example.palida.beacon_reminder.altBeacon;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.palida.beacon_reminder.R;

import org.altbeacon.beacon.Beacon;

import java.util.List;


public class BeaconAdapter extends RecyclerView.Adapter<BeaconAdapter.BeaconHolder> {

    /**
     * Callback which has to be implemented by the hosting activity.
     * <p/>
     * Callback interface allows for a component to be a completely self-contained,
     * modular component that defines its own layout and behaviour.
     */
    public interface OnBeaconSelectedListener {

        /**
         * Handles on beacon selected event.
         *
         * @param beacon Selected beacon.
         */
        void onBeaconSelected(Beacon beacon, String name);
    }

    private List<Beacon> mBeacons;
    private List<String> mBeaconsName;
    private  List<Integer> mBeaconIcon;
    private OnBeaconSelectedListener mCallback;
    private Context mContext;

    /**
     * Creates a new Beacon adapter.
     *
     * @param beacons  List of beacons.
     * @param callback OnBeaconSelected callback.
     * @param context  Context.
     */
    public BeaconAdapter(List<Beacon> beacons, OnBeaconSelectedListener callback, Context context,List<String> beaconNames,List<Integer> beaconIcons ) {
        mBeacons = beacons;
        mCallback = callback;
        mContext = context;
        mBeaconsName=beaconNames;
        mBeaconIcon = beaconIcons;
    }

    @Override
    public BeaconHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View beaconItemView = LayoutInflater.from(mContext).inflate(R.layout.list_item_beacon, parent, false);
        return new BeaconHolder(beaconItemView);
    }

    @Override
    public void onBindViewHolder(BeaconHolder holder, int position) {
        // Binds beacon to view holder.
        holder.bindBeacon(mBeacons.get(position), mBeaconsName.get(position), mBeaconIcon.get(position));
    }

    @Override
    public int getItemCount() {
        return mBeacons.size();
    }

    /**
     * View holder for list items displaying beacon data.
     */
    class BeaconHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mBeaconTitle;
        TextView mBeaconId;
        //        TextView mBeaconMajorMinor;
        ImageView mBeaconIcon;

        private Beacon mBeacon;

        /**
         * Creates a new Beacon holder.
         *
         * @param itemView View holder layout.
         */
        public BeaconHolder(View itemView) {
            super(itemView);
            mBeaconTitle = (TextView) itemView.findViewById(R.id.list_item_beacon_title);
            mBeaconId = (TextView) itemView.findViewById(R.id.list_item_beacon_id);
            mBeaconIcon = itemView.findViewById(R.id.image_view_icon);
            itemView.setOnClickListener(this);
        }

        /**
         * Binds beacon to view holder to display its data.
         *
         * @param beacon Beacon
         */
        public void bindBeacon(Beacon beacon, String beaconName, Integer icon) {
            mBeacon = beacon;
            mBeaconTitle.setText(beaconName);
            mBeaconId.setText("UUID : "+mBeacon.getId1().toString());
            mBeaconIcon.setBackgroundResource(icon);
        }

        @Override
        public void onClick(View v) {
                mCallback.onBeaconSelected(mBeacon,mBeaconTitle.getText().toString());
    }
    }
}
