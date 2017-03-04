package net.tinmints.scouting;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.tinmints.scouting.model.ScoutData;
import net.tinmints.scouting.model.ScoutDataFactory;

public class TeamDataTabFragment extends Fragment {

    private ScoutData[] data;
    private TextView match;
    private boolean init=false;

    public TeamDataTabFragment() {
        // Required empty public constructor
    }


    public static TeamDataTabFragment newInstance() {
        TeamDataTabFragment fragment = new TeamDataTabFragment();
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
        View v = inflater.inflate(R.layout.fragment_team_data_tab, container, false);
        match = (TextView)v.findViewById(R.id.match_number);
        init=true;
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
        if(init && getChildFragmentManager()!=null && getChildFragmentManager().findFragmentById(R.id.team_data_fragment)!=null) {
            match.setText(ScoutDataFactory.instanceOf().getData(0).getMatchNumber() + "");
            ((TeamDataFragment) getChildFragmentManager().findFragmentById(R.id.team_data_fragment)).setData(ScoutDataFactory.instanceOf().getData(0));
            ((TeamDataFragment) getChildFragmentManager().findFragmentById(R.id.team_data_fragment1)).setData(ScoutDataFactory.instanceOf().getData(1));
            ((TeamDataFragment) getChildFragmentManager().findFragmentById(R.id.team_data_fragment2)).setData(ScoutDataFactory.instanceOf().getData(2));
            getChildFragmentManager().findFragmentById(R.id.team_data_fragment).onResume();
            getChildFragmentManager().findFragmentById(R.id.team_data_fragment1).onResume();
            getChildFragmentManager().findFragmentById(R.id.team_data_fragment2).onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(init && getChildFragmentManager()!=null && getChildFragmentManager().findFragmentById(R.id.team_data_fragment)!=null) {
            getChildFragmentManager().findFragmentById(R.id.team_data_fragment).onPause();
            getChildFragmentManager().findFragmentById(R.id.team_data_fragment1).onPause();
            getChildFragmentManager().findFragmentById(R.id.team_data_fragment2).onPause();
        }
    }

}
