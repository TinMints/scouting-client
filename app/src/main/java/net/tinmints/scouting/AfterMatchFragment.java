package net.tinmints.scouting;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import net.tinmints.scouting.model.ScoutData;
import net.tinmints.scouting.model.ScoutDataFactory;


public class AfterMatchFragment extends Fragment {

    View v;
    EditText score;
    boolean init = false;

    public AfterMatchFragment() {
        // Required empty public constructor
    }


    public static AfterMatchFragment newInstance() {
        AfterMatchFragment fragment = new AfterMatchFragment();
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
        v = inflater.inflate(R.layout.fragment_after_match, container, false);

        score = (EditText)v.findViewById(R.id.score);

        ((Button)v.findViewById(R.id.send)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPause();
                ((MainActivity)getActivity()).sendMessage(v);
            }
        });
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

    public void sendData() {
        ((MainActivity)getActivity()).sendMessage(v);
    }

    @Override
    public void onPause() {
        super.onPause();
        if(init && score!=null) {
            ScoutData[] data = ScoutDataFactory.instanceOf().getData();
            if (score != null && score.getText() != null) {
                data[0].setScore(Integer.parseInt(score.getText().toString()));
                data[1].setScore(Integer.parseInt(score.getText().toString()));
                data[2].setScore(Integer.parseInt(score.getText().toString()));
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ScoutData data = ScoutDataFactory.instanceOf().getData(0);
        if(init && score!=null) {
            score.setText(data.getScore()+"");
        }
    }
}
