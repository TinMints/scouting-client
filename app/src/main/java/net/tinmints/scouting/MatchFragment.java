package net.tinmints.scouting;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import net.tinmints.scouting.model.ScoutData;
import net.tinmints.scouting.model.ScoutDataFactory;


public class MatchFragment extends Fragment {

    private EditText recorder;
    private EditText match;
    private boolean init=false;

    public MatchFragment() {
        // Required empty public constructor
    }

    public static MatchFragment newInstance() {
        MatchFragment fragment = new MatchFragment();
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
        View v = inflater.inflate(R.layout.fragment_match, container, false);

        recorder = (EditText)v.findViewById(R.id.recorder_name);
        match = (EditText)v.findViewById(R.id.match_number);
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
    public void onPause() {
        super.onPause();
        if(init && recorder!=null) {
            ScoutData[] data = ScoutDataFactory.instanceOf().getData();
            if (recorder != null && recorder.getText() != null) {
                data[0].setRecorder(recorder.getText().toString());
                data[1].setRecorder(recorder.getText().toString());
                data[2].setRecorder(recorder.getText().toString());
            }
            if (match != null && match.getText() != null) {
                data[0].setMatchNumber(Integer.parseInt(match.getText().toString()));
                data[1].setMatchNumber(Integer.parseInt(match.getText().toString()));
                data[2].setMatchNumber(Integer.parseInt(match.getText().toString()));
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ScoutData data = ScoutDataFactory.instanceOf().getData(0);
        if(init && recorder!=null) {
            if(data.getRecorder()!=null && recorder!=null) {
                recorder.setText(data.getRecorder());
            }
            if(data.getMatchNumber()!=-1 && match!=null) {
                match.setText(data.getMatchNumber()+"");
            }
        }
    }
}
