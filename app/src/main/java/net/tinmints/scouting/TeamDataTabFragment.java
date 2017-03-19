package net.tinmints.scouting;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import net.tinmints.scouting.model.ScoutData;
import net.tinmints.scouting.model.ScoutDataFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Set;

public class TeamDataTabFragment extends Fragment {

    private ScoutData[] data;
    private TextView match;
    private Button get;
    private TextView response;
    private BluetoothAdapter mBluetoothAdapter;

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
        get = (Button)v.findViewById(R.id.get_team_data);
        response = (TextView)v.findViewById(R.id.server_respone);

        get.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ScoutData[] data = ScoutDataFactory.instanceOf().getData();
                String teamNum = ((EditText)((TeamDataFragment) getChildFragmentManager().findFragmentById(R.id.team_data_fragment)).getView().findViewById(R.id.team_num)).getText().toString();
                String serverResponse = setData(data[0], data[0].getMatchNumber()+"", teamNum);
                response.setText(serverResponse);
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

    private String setData(ScoutData data, String match, String team) {
        String serverResponse = "No data for team " + team + " in match " + match;
        try {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (mBluetoothAdapter != null) {
                if (mBluetoothAdapter.isEnabled()) {
                    BluetoothDevice device = findPair();
                    if(device!=null) {
                        serverResponse = getInfo(device, match, team, data);
                    } else {
                        serverResponse = "Error: No device";
                    }
                } else {
                    serverResponse = "Error: No adapter";
                }
            } else {
                serverResponse = "Error: Adapter not enabled";
            }

        } catch(Exception e) {
            serverResponse = "Error: " + e.getMessage();
        }
        return serverResponse;
    }

    private BluetoothDevice findPair() {
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

        if (pairedDevices.size() > 0) {
            // There are paired devices. Get the name and address of each paired device.
            for (BluetoothDevice device : pairedDevices) {
                ParcelUuid[] parceUuids = device.getUuids();
                for (int i = 0; i < parceUuids.length; i++) {
                    if (parceUuids[i].getUuid().equals(DisplayMessageActivity.MY_UUID)) {
                        return device;
                    }
                }
            }
        }
        return null;
    }

    private String getInfo(BluetoothDevice device, String match, String team, ScoutData data) {
        String recMessage = null;
        OutputStream out = null;
        InputStream in = null;
        try (BluetoothSocket socket = device.createRfcommSocketToServiceRecord(DisplayMessageActivity.MY_UUID)) {
            socket.connect();
            //message = message + " connected";
            out = socket.getOutputStream();
            PrintStream printStream = new PrintStream(out);
            printStream.println("GET:" + match + ":" + team);
            printStream.println("done");

            in = socket.getInputStream();
            BufferedReader bReader = new BufferedReader(new InputStreamReader(in));
            String lineRead = null;
            boolean noData = false;
            while(!(lineRead=bReader.readLine()).equals("done") ) {
                if(lineRead.equals("no data")) {
                    noData = true;
                }
                String[] p = lineRead.split(":");
                String action = p[0];
                String value = null;
                if(p.length>1) {
                    value = p[1];
                    setData(action,value,data);
                }
            }
            if(noData) {
                recMessage = "No data for match " + match + " and team " + team;
            } else {
                recMessage = "Data recieved for match " + match + " and team " + team;
            }
        } catch (IOException e) {
            recMessage = "Error: " + e.getMessage();
        } finally {
            try {
                out.close();
            } catch (Exception e) {}
            try {
                in.close();
            } catch (Exception e) {}
        }
        return recMessage;
    }

    public void setData(String action, String value, ScoutData data) {
        try {
            if(action.startsWith("is")) {
                String field = action.replaceFirst("is", "");
                field = field.substring(0,1).toLowerCase() + field.substring(1);
                set(data,field,Boolean.parseBoolean(value));
            } else {
                String field = action.replaceFirst("get", "");
                field = field.substring(0,1).toLowerCase() + field.substring(1);
                if(data.getClass().getMethod(action).getReturnType().equals(Integer.TYPE)) {
                    set(data,field,Integer.parseInt(value));
                } else {
                    set(data,field,value);
                }
            }
        }catch(Throwable e) {
            System.out.println(action + " " + value);
            e.printStackTrace();
        }

    }

    public boolean set(Object object, String fieldName, Object fieldValue) {
        //System.out.println(fieldName + " " + fieldValue);
        Class<?> clazz = object.getClass();
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(object, fieldValue);
                //System.out.println("WAS SET"  + fieldName + "->" + fieldValue);
                return true;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return false;
    }

}
