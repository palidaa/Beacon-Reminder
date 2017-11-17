package com.example.palida.beacon_reminder;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class WithInListFragment extends Fragment {
    private View rootView;
    int pos;

    public WithInListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_with_in_list, container, false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            pos = bundle.getInt("posL");
        }
        TextView titleName = (TextView) getActivity().findViewById(R.id.toolbar_title);
        titleName.setText("");
        TextView edit = (TextView) getActivity().findViewById(R.id.edit);
        edit.setText("edit");
        Button search = (Button) getActivity().findViewById(R.id.search);
        search.setBackgroundResource(R.drawable.bin);
        TextView save = (TextView) getActivity().findViewById(R.id.save);
        save.setText("");

        ImageView pic = (ImageView) rootView.findViewById(R.id.pic);
        pic.setBackgroundResource(ListFragment.pic.get(pos));
        TextView name = (TextView) rootView.findViewById(R.id.name);
        name.setText("Name: " + ListFragment.name.get(pos));
        TextView des = (TextView) rootView.findViewById(R.id.description);
        des.setText("Description: "+ListFragment.description.get(pos));
        TextView install = (TextView) rootView.findViewById(R.id.install_on);
        install.setText("Install on: "+ ListFragment.install.get(pos));


        getActivity().findViewById(R.id.search).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ListFragment.name.remove(pos);
                ListFragment.pic.remove(pos);
                Toast.makeText(getActivity(), "Delete item complete",
                        Toast.LENGTH_LONG).show();
                getFragmentManager().popBackStack();
            }
        });

        getActivity().findViewById(R.id.edit).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                EditFragment select1 = new EditFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("pos", pos);
                select1.setArguments(bundle);
                transaction.replace(R.id.frame, select1);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return rootView;
    }

}
