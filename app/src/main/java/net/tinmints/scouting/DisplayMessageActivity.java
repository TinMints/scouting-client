package net.tinmints.scouting;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.ParcelUuid;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
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
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        boolean ad = false;
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter != null) {
            message = message + " true";
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                message = message + " false";
            } else {
                message = message + " true ";
                BluetoothDevice device = findPair();
                if (device != null) {
                    try (BluetoothSocket socket = device.createRfcommSocketToServiceRecord(MY_UUID)) {
                        socket.connect();
                        message = message + " connected";
                        OutputStream out = socket.getOutputStream();
                        PrintStream printStream = new PrintStream(out);
                        printStream.println(message);

                        message = message + " sent";
                        InputStream in = socket.getInputStream();
                        BufferedReader bReader = new BufferedReader(new InputStreamReader(in));
                        message = message + " " + bReader.readLine();

                        message = message + " recieved";
                        out.close();
                        in.close();
                    } catch (IOException e) {
                        message = message + " " + e.getMessage();
                    }
                } else {
                    message = message + " device is null";
                }
            }
        }

        textView.setText(message);

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

}

