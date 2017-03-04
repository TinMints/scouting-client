package net.tinmints.scouting;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.ParcelUuid;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import net.tinmints.scouting.model.ScoutData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.UUID;

public class DisplayMessageActivity extends AppCompatActivity {

    private static int REQUEST_ENABLE_BT = 32;
    private static UUID MY_UUID = UUID.fromString("5c058be0-dc18-11e6-bf26-cec0c932ce01");
    private String serverHardwareAddress = null;
    private String serverName = null;
    private BluetoothAdapter mBluetoothAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        ScoutData[] data = (ScoutData[])intent.getSerializableExtra(MainActivity.DATA);
        String message = serialize(data[0]);
        String message1 = null;
        if(data[1].getTeamNumber()>0) {
           message1 = serialize(data[1]);
        }
        String message2 = null;
        if(data[2].getTeamNumber()>0) {
            message2 = serialize(data[2]);
        }
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        Button button = new Button(this);
        boolean ad = false;
        String recMessage = "No message from Server";
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter != null) {
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                //message = message + " false";
            } else {
                //message = message + " true ";
                BluetoothDevice device = findPair();
                if (device != null) {
                    try (BluetoothSocket socket = device.createRfcommSocketToServiceRecord(MY_UUID)) {
                        socket.connect();
                        //message = message + " connected";
                        OutputStream out = socket.getOutputStream();
                        PrintStream printStream = new PrintStream(out);
                        printStream.println(message);
                        if(message1!=null) {
                            printStream.println("next");
                            printStream.println(message1);
                        }
                        if(message2!=null) {
                            printStream.println("next");
                            printStream.println(message2);
                        }
                        printStream.println("done");

                        InputStream in = socket.getInputStream();
                        BufferedReader bReader = new BufferedReader(new InputStreamReader(in));
                        recMessage = bReader.readLine();

                        out.close();
                        in.close();
                    } catch (IOException e) {
                        recMessage = "Error: " + e.getMessage();
                    }
                } else {
                    recMessage = "Device is null";
                }
            }
        }

        textView.setText(recMessage);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        layout.addView(textView);
    }


    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == RESULT_OK) {
                //TextView textView = new TextView(this);
                //textView.setTextSize(40);
                //textView.setText("bluetooth enabled");

                //ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
                //layout.addView(textView);
            }
        }
    }

    private BluetoothDevice findPair() {
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

        if (pairedDevices.size() > 0) {
            // There are paired devices. Get the name and address of each paired device.
            for (BluetoothDevice device : pairedDevices) {
                ParcelUuid[] parceUuids = device.getUuids();
                for (int i = 0; i < parceUuids.length; i++) {
                    if (parceUuids[i].getUuid().equals(MY_UUID)) {
                        serverName = device.getName();
                        serverHardwareAddress = device.getAddress();
                        return device;
                    }
                }
            }
        }
        return null;
    }


    private String serialize(ScoutData data) {

        Method[] methods = data.getClass().getMethods();
        StringBuilder string = new StringBuilder();
        for(int i=0;i<methods.length;i++) {
            try {
                String name = methods[i].getName();
                if((name.startsWith("get") || name.startsWith("is")) && !name.startsWith("getClass")) {
                    string.append(methods[i].getName()).append(":").append(methods[i].invoke(data)).append(System.lineSeparator());
                }
            }catch(Exception e) {

            }

        }

        return string.toString();
    }

    public void back(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}

