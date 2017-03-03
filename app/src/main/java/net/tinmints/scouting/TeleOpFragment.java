package net.tinmints.scouting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import net.tinmints.scouting.listener.SpinnerListener;
import net.tinmints.scouting.model.ScoutData;


public class TeleOpFragment extends Fragment {
    ScoutData data;
    TextView team;
    Spinner teleRotors;

    TextView teleFuelLow;
    int fuelLow = 0;
    TextView teleFuelHigh;
    int fuelHigh = 0;
    TextView teleFuelStation;
    int fuelStation = 0;
    TextView teleFuelFloor;
    int fuelFloor = 0;
    TextView teleGearsGot;
    int gearsGot = 0;
    TextView teleGearsDelivered;
    int gearsDelivered = 0;
    TextView teleGearsPicked;
    int gearsPicked = 0;
    TextView teleHopper;
    int hopper = 0;
    TextView teleFouls;
    int fouls = 0;

    ToggleButton lifts;
    ToggleButton offense;
    ToggleButton defense;
    ToggleButton broke;
    ToggleButton recovered;


    public TeleOpFragment() {
        // Required empty public constructor
    }

    public void setData(ScoutData data) {
        this.data = data;
        //((SpinnerListener)teleRotors.getOnItemSelectedListener()).setData(data);
    }

    public static TeleOpFragment newInstance(String param1, String param2) {
        TeleOpFragment fragment = new TeleOpFragment();
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
        View v = inflater.inflate(R.layout.fragment_tele_op, container, false);

        team = (TextView) v.findViewById(R.id.team_num);

        teleRotors = (Spinner) v.findViewById(R.id.tele_rotors);

        ArrayAdapter<CharSequence> rotors = ArrayAdapter.createFromResource(this.getActivity().getApplicationContext(), R.array.rotors, R.layout.spinner);
        rotors.setDropDownViewResource(R.layout.spinner_list);

        teleRotors.setAdapter(rotors);
        teleRotors.setOnItemSelectedListener(new SpinnerListener(data,SpinnerListener.TYPE.TELEROTORS));

        lifts = (ToggleButton)v.findViewById(R.id.tele_lifts);
        offense = (ToggleButton)v.findViewById(R.id.tele_offense);
        defense = (ToggleButton)v.findViewById(R.id.tele_defense);
        broke = (ToggleButton)v.findViewById(R.id.tele_brokedown);
        recovered = (ToggleButton)v.findViewById(R.id.tele_recover);

        teleFuelLow = (TextView)v.findViewById(R.id.tele_low);
        teleFuelHigh = (TextView)v.findViewById(R.id.tele_high);
        teleFuelFloor = (TextView)v.findViewById(R.id.tele_floor);
        teleFuelStation = (TextView)v.findViewById(R.id.tele_station);
        teleGearsGot = (TextView)v.findViewById(R.id.tele_got);
        teleGearsDelivered = (TextView)v.findViewById(R.id.tele_deliver);
        teleGearsPicked = (TextView)v.findViewById(R.id.tele_picked);
        teleHopper = (TextView)v.findViewById(R.id.tele_hoppers);
        teleFouls = (TextView)v.findViewById(R.id.tele_fouls);

        ((Button)v.findViewById(R.id.tele_low_add)).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                fuelLow++;
                teleFuelLow.setText(fuelLow+"");
            }
        });

        ((Button)v.findViewById(R.id.tele_low_minus)).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                fuelLow--;
                if(fuelLow<0)
                    fuelLow=0;

                teleFuelLow.setText(fuelLow+"");
            }
        });

        ((Button)v.findViewById(R.id.tele_high_add)).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                fuelHigh++;
                teleFuelHigh.setText(fuelHigh+"");
            }
        });

        ((Button)v.findViewById(R.id.tele_high_minus)).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                fuelHigh--;
                if(fuelHigh<0)
                    fuelHigh=0;

                teleFuelHigh.setText(fuelHigh+"");
            }
        });


        ((Button)v.findViewById(R.id.tele_station_add)).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                fuelStation++;
                teleFuelStation.setText(fuelStation+"");
            }
        });

        ((Button)v.findViewById(R.id.tele_station_minus)).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                fuelStation--;
                if(fuelStation<0)
                    fuelStation=0;

                teleFuelStation.setText(fuelStation+"");
            }
        });


        ((Button)v.findViewById(R.id.tele_floor_add)).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                fuelFloor++;
                teleFuelFloor.setText(fuelFloor+"");
            }
        });

        ((Button)v.findViewById(R.id.tele_floor_minus)).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                fuelFloor--;
                if(fuelFloor<0)
                    fuelFloor=0;

                teleFuelFloor.setText(fuelFloor+"");
            }
        });

        ((Button)v.findViewById(R.id.tele_got_add)).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                gearsGot++;
                teleGearsGot.setText(gearsGot+"");
            }
        });

        ((Button)v.findViewById(R.id.tele_got_minus)).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                gearsGot--;
                if(gearsGot<0)
                    gearsGot=0;

                teleGearsGot.setText(gearsGot+"");
            }
        });

        ((Button)v.findViewById(R.id.tele_deliver_add)).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                gearsDelivered++;
                teleGearsDelivered.setText(gearsDelivered+"");

            }
        });

        ((Button)v.findViewById(R.id.tele_deliver_minus)).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                gearsDelivered--;
                if(gearsDelivered<0)
                    gearsDelivered=0;

                teleGearsDelivered.setText(gearsDelivered+"");
            }
        });

        ((Button)v.findViewById(R.id.tele_picked_add)).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                gearsPicked++;
                teleGearsPicked.setText(gearsPicked+"");
            }
        });

        ((Button)v.findViewById(R.id.tele_picked_minus)).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                gearsPicked--;
                if(gearsPicked<0)
                    gearsPicked=0;

                teleGearsPicked.setText(gearsPicked+"");
            }
        });

        ((Button)v.findViewById(R.id.tele_hoppers_add)).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                hopper++;
                teleHopper.setText(hopper+"");
            }
        });

        ((Button)v.findViewById(R.id.tele_hoppers_minus)).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                hopper--;
                if(hopper<0)
                    hopper=0;

                teleHopper.setText(hopper+"");
            }
        });

        ((Button)v.findViewById(R.id.tele_fouls_add)).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                fouls++;
                teleFouls.setText(fouls+"");
            }
        });

        ((Button)v.findViewById(R.id.tele_fouls_minus)).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                fouls--;
                if(fouls<0)
                    fouls=0;

                teleFouls.setText(fouls+"");
            }
        });

        return v;
    }


    @Override
    public void onPause() {
        super.onPause();
        if (data != null) {
            data.setTeleLowBoiler(fuelLow);
            data.setTeleHighBoiler(fuelHigh);
            data.setTeleFuelFloor(fuelFloor);
            data.setTeleFuelStation(fuelStation);
            data.setTeleHopperTrigger(hopper);
            data.setTeleGearsDeliverd(gearsDelivered);
            data.setTeleGearsGot(gearsGot);
            data.setTeleGearsPickedUp(gearsPicked);
            data.setFouls(fouls);

            data.setTeleLifts(lifts.isChecked());
            data.setTeleOffense(offense.isChecked());
            data.setTeleDefense(defense.isChecked());
            data.setTeleBreakdown(broke.isChecked());
            data.setTeleRecover(recovered.isChecked());

            if(teleRotors.getSelectedItem()!=null) {
                data.setTeleRotors(Integer.parseInt((String) teleRotors.getSelectedItem()));
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (data != null) {
            team.setText(data.getTeamNumber()+"");

            fuelLow = data.getTeleLowBoiler();
            teleFuelLow.setText(fuelLow+"");
            fuelHigh = data.getTeleHighBoiler();
            teleFuelHigh.setText(fuelHigh+"");
            fuelFloor = data.getTeleFuelFloor();
            teleFuelFloor.setText(fuelFloor+"");
            fuelStation = data.getTeleFuelStation();
            teleFuelStation.setText(fuelStation+"");
            hopper = data.getTeleHopperTrigger();
            teleHopper.setText(hopper+"");
            gearsDelivered = data.getTeleGearsDeliverd();
            teleGearsDelivered.setText(gearsDelivered+"");
            gearsGot = data.getTeleGearsGot();
            teleGearsGot.setText(gearsGot+"");
            gearsPicked = data.getTeleGearsPickedUp();
            teleGearsPicked.setText(gearsPicked+"");
            fouls = data.getFouls();
            teleFouls.setText(fouls+"");


            lifts.setChecked(data.isTeleLifts());
            offense.setChecked(data.isTeleOffense());
            defense.setChecked(data.isTeleDefense());
            broke.setChecked(data.isTeleBreakdown());
            recovered.setChecked(data.isTeleRecover());

            if(data.getTeleRotors()>0) {
                teleRotors.setSelection(((ArrayAdapter) teleRotors.getAdapter()).getPosition(data.getTeleRotors()+""));
            } else {
                teleRotors.setSelection(0);
            }
        }
    }
}
