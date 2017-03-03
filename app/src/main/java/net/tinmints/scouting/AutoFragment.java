package net.tinmints.scouting;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import net.tinmints.scouting.listener.SpinnerListener;
import net.tinmints.scouting.model.ScoutData;


public class AutoFragment extends Fragment {

    private ScoutData data;

    Spinner autoRotors;
    ToggleButton autoMiddle;
    ToggleButton autoCross;
    ToggleButton autoHigh;
    ToggleButton autoLow;
    ToggleButton autoGear;

    TextView team;

    public AutoFragment() {
        // Required empty public constructor
    }

    public static AutoFragment newInstance() {
        AutoFragment fragment = new AutoFragment();
        return fragment;
    }

    public void setData(ScoutData data) {
        this.data = data;
        ((SpinnerListener)autoRotors.getOnItemSelectedListener()).setData(data);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_auto, container, false);

        autoRotors = (Spinner) v.findViewById(R.id.auto_rotors);

        ArrayAdapter<CharSequence> rotors = ArrayAdapter.createFromResource(this.getActivity().getApplicationContext(), R.array.rotors, R.layout.spinner);
        rotors.setDropDownViewResource(R.layout.spinner_list);

        autoRotors.setAdapter(rotors);
        autoRotors.setOnItemSelectedListener(new SpinnerListener(data,SpinnerListener.TYPE.AUTOROTORS));

        autoMiddle = (ToggleButton) v.findViewById(R.id.auto_middle);
        autoCross = (ToggleButton) v.findViewById(R.id.auto_cross);
        autoHigh = (ToggleButton) v.findViewById(R.id.auto_high);
        autoLow = (ToggleButton) v.findViewById(R.id.auto_low);
        autoGear = (ToggleButton) v.findViewById(R.id.auto_gears);

        team = (TextView) v.findViewById(R.id.team_num);

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
        if(data!=null) {
            data.setAutoStartInMiddle(autoMiddle.isChecked());
            data.setAutoCrossedLine(autoCross.isChecked());
            data.setAutoHighBoiler(autoHigh.isChecked());
            data.setAutoLowBoiler(autoLow.isChecked());
            data.setAutoMadePeg(autoGear.isChecked());

            if(autoRotors.getSelectedItem()!=null) {
                data.setAutoRotors(Integer.parseInt((String) autoRotors.getSelectedItem()));
            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if(data!=null) {
            team.setText(data.getTeamNumber()+"");
            autoMiddle.setChecked(data.isAutoStartInMiddle());
            autoCross.setChecked(data.isAutoCrossedLine());
            autoHigh.setChecked(data.isAutoHighBoiler());
            autoLow.setChecked(data.isAutoLowBoiler());
            autoGear.setChecked(data.isAutoMadePeg());

            if(data.getAutoRotors()>0) {
                autoRotors.setSelection(((ArrayAdapter) autoRotors.getAdapter()).getPosition(data.getAutoRotors()+""));
            } else {
                autoRotors.setSelection(0);
            }

        }


    }


}
