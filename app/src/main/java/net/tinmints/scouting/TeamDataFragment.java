package net.tinmints.scouting;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import net.tinmints.scouting.listener.SpinnerListener;
import net.tinmints.scouting.model.ScoutData;


public class TeamDataFragment extends Fragment {

    private ScoutData data;
    private Spinner teamAll;
    private EditText teamNum;

    public TeamDataFragment() {
        // Required empty public constructor
    }

    public ScoutData getData() {
        return data;
    }

    public void setData(ScoutData data) {
        this.data = data;
        ((SpinnerListener)teamAll.getOnItemSelectedListener()).setData(data);
    }

    public static TeamDataFragment newInstance(ScoutData data) {
        TeamDataFragment fragment = new TeamDataFragment();
        fragment.setData(data);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_team_data, container, false);
        teamNum = (EditText)v.findViewById(R.id.team_num);
        teamAll = (Spinner) v.findViewById(R.id.team_all);

        ArrayAdapter<CharSequence> alliance = ArrayAdapter.createFromResource(this.getActivity().getApplicationContext(), R.array.alliance, R.layout.spinner);
        alliance.setDropDownViewResource(R.layout.spinner_list);

        teamAll.setAdapter(alliance);
        teamAll.setOnItemSelectedListener(new SpinnerListener(data,SpinnerListener.TYPE.TEAMALL));

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(data!=null) {
            if(data.getTeamNumber()!=-1) {
                teamNum.setText(data.getTeamNumber()+"");
            }

            if(data.getTeamAlliance()!=null && data.getTeamAlliance().length()!=0) {
                teamAll.setSelection(((ArrayAdapter) teamAll.getAdapter()).getPosition(data.getTeamAlliance()));
            } else {
                teamAll.setSelection(0);
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(data!=null) {
            if(teamNum.getText()!=null) {
                data.setTeamNumber(Integer.parseInt(teamNum.getText().toString()));
            }
            if(teamAll.getSelectedItem()!=null) {
                data.setTeamAlliance((String)teamAll.getSelectedItem());
            }
        }
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
