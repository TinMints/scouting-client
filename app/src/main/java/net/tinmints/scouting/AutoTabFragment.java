package net.tinmints.scouting;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.tinmints.scouting.model.ScoutData;
import net.tinmints.scouting.model.ScoutDataFactory;


public class AutoTabFragment extends Fragment {

    private TextView match;

    public AutoTabFragment() {
        // Required empty public constructor
    }

    public static AutoTabFragment newInstance() {
        AutoTabFragment fragment = new AutoTabFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_auto_tab, container, false);
        match = (TextView)v.findViewById(R.id.match_number);
        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getChildFragmentManager()!=null && getChildFragmentManager().findFragmentById(R.id.auto_fragment)!=null) {
            match.setText(ScoutDataFactory.instanceOf().getData(0).getMatchNumber() + "");
            ((AutoFragment) getChildFragmentManager().findFragmentById(R.id.auto_fragment)).setData(ScoutDataFactory.instanceOf().getData(0));
            ((AutoFragment) getChildFragmentManager().findFragmentById(R.id.auto_fragment1)).setData(ScoutDataFactory.instanceOf().getData(1));
            ((AutoFragment) getChildFragmentManager().findFragmentById(R.id.auto_fragment2)).setData(ScoutDataFactory.instanceOf().getData(2));
        }
        if(ScoutDataFactory.instanceOf().getData(1).getTeamNumber()<=0) {
            ((AutoFragment) getChildFragmentManager().findFragmentById(R.id.auto_fragment1)).getView().setVisibility(View.GONE);
        } else {
            ((AutoFragment) getChildFragmentManager().findFragmentById(R.id.auto_fragment1)).getView().setVisibility(View.VISIBLE);
        }
        if(ScoutDataFactory.instanceOf().getData(2).getTeamNumber()<=0) {
            ((AutoFragment) getChildFragmentManager().findFragmentById(R.id.auto_fragment2)).getView().setVisibility(View.GONE);
        } else {
            ((AutoFragment) getChildFragmentManager().findFragmentById(R.id.auto_fragment2)).getView().setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(getChildFragmentManager()!=null && getChildFragmentManager().findFragmentById(R.id.auto_fragment)!=null) {
            getChildFragmentManager().findFragmentById(R.id.auto_fragment).onPause();
            getChildFragmentManager().findFragmentById(R.id.auto_fragment1).onPause();
            getChildFragmentManager().findFragmentById(R.id.auto_fragment2).onPause();
        }
    }


}
