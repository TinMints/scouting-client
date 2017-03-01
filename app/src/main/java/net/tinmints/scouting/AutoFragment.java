package net.tinmints.scouting;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.tinmints.scouting.model.ScoutData;


public class AutoFragment extends Fragment {

    private ScoutData data;

    public AutoFragment() {
        // Required empty public constructor
    }

    public static AutoFragment newInstance() {
        AutoFragment fragment = new AutoFragment();
        return fragment;
    }

    public void setData(ScoutData data) {
        this.data = data;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auto, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
