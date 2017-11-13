package com.example.palida.beacon_reminder;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlarmFragment extends Fragment {
    private View rootView;

    public AlarmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_list, container, false);
        TextView titleName = (TextView) getActivity().findViewById(R.id.toolbar_title);
        titleName.setText("Alarm");
        TextView edit = (TextView) getActivity().findViewById(R.id.edit);
        edit.setText("");
        Button search = (Button) getActivity().findViewById(R.id.search);
        search.setBackgroundResource(R.drawable.search);
        TextView save = (TextView) getActivity().findViewById(R.id.save);
        save.setText("");

        ListView listView = rootView.findViewById(R.id.listView);
        final CustomAdapterAlarm adapter =new CustomAdapterAlarm(getActivity(), ListFragment.name,ListFragment.pic,ListFragment.checked);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

            MainActivity.l=2;
            MainActivity.i=2;
            Switch s = (Switch) rootView.findViewById(R.id.switch1);

//            FragmentTransaction transaction = getFragmentManager().beginTransaction();
//            WithInListFragment select1 = new WithInListFragment();
//            Bundle bundle = new Bundle();
//            bundle.putInt("posL", arg2);
//            select1.setArguments(bundle);
//            transaction.replace(R.id.frame, select1);
//            transaction.addToBackStack(null);
//            transaction.commit();
            }
        });


        return rootView;
    }

}
