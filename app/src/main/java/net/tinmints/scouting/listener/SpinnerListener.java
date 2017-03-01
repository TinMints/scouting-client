package net.tinmints.scouting.listener;

import android.view.View;
import android.widget.AdapterView;

import net.tinmints.scouting.model.ScoutData;

/**
 * Created by wpohlhau on 2/11/2017.
 */

public class SpinnerListener implements AdapterView.OnItemSelectedListener {

    private ScoutData data;
    private TYPE type;
    public enum TYPE {TEAMPOS,ROBOTPOS,TEAMALL}


    public SpinnerListener(ScoutData data, TYPE type) {
        this.data=data;
        this.type=type;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selected = parent.getItemAtPosition(position).toString();
        if(data!=null) {
            switch (type) {
                case TEAMALL:
                    data.setTeamAlliance(selected);
                    break;
                case TEAMPOS:
                    data.setTeamStartPos(Integer.parseInt(selected));
                    break;
                case ROBOTPOS:
                    data.setRobotStartPos(Integer.parseInt(selected));
                    break;
                default:
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void setData(ScoutData data) {
        this.data = data;
    }
}
