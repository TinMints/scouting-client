package net.tinmints.scouting;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import net.tinmints.scouting.listener.SpinnerListener;
import net.tinmints.scouting.model.ScoutData;
import net.tinmints.scouting.model.ScoutDataFactory;

public class TeleOpTabFragment extends Fragment {

    private TextView match;

    public TeleOpTabFragment() {
        // Required empty public constructor
    }

    public static TeleOpTabFragment newInstance() {
        TeleOpTabFragment fragment = new TeleOpTabFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_tele_op_tab, container, false);
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
        if(getChildFragmentManager()!=null && getChildFragmentManager().findFragmentById(R.id.tele_fragment)!=null) {
            match.setText(ScoutDataFactory.instanceOf().getData(0).getMatchNumber() + "");
            ((TeleOpFragment) getChildFragmentManager().findFragmentById(R.id.tele_fragment)).setData(ScoutDataFactory.instanceOf().getData(0));
            ((TeleOpFragment) getChildFragmentManager().findFragmentById(R.id.tele_fragment1)).setData(ScoutDataFactory.instanceOf().getData(1));
            ((TeleOpFragment) getChildFragmentManager().findFragmentById(R.id.tele_fragment2)).setData(ScoutDataFactory.instanceOf().getData(2));
            getChildFragmentManager().findFragmentById(R.id.tele_fragment).onResume();
            getChildFragmentManager().findFragmentById(R.id.tele_fragment1).onResume();
            getChildFragmentManager().findFragmentById(R.id.tele_fragment2).onResume();
            if(ScoutDataFactory.instanceOf().getData(1).getTeamNumber()<=0) {
                ((TeleOpFragment) getChildFragmentManager().findFragmentById(R.id.tele_fragment1)).getView().setVisibility(View.GONE);
            } else {
                ((TeleOpFragment) getChildFragmentManager().findFragmentById(R.id.tele_fragment1)).getView().setVisibility(View.VISIBLE);
            }
            if(ScoutDataFactory.instanceOf().getData(2).getTeamNumber()<=0) {
                ((TeleOpFragment) getChildFragmentManager().findFragmentById(R.id.tele_fragment2)).getView().setVisibility(View.GONE);
            } else {
                ((TeleOpFragment) getChildFragmentManager().findFragmentById(R.id.tele_fragment2)).getView().setVisibility(View.VISIBLE);
            }
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        if(getChildFragmentManager()!=null && getChildFragmentManager().findFragmentById(R.id.tele_fragment)!=null) {
            getChildFragmentManager().findFragmentById(R.id.tele_fragment).onPause();
            getChildFragmentManager().findFragmentById(R.id.tele_fragment1).onPause();
            getChildFragmentManager().findFragmentById(R.id.tele_fragment2).onPause();
        }
    }

}
