package com.example.thevisualbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class BluetoothBookConnectActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 0;
    private static final int REQUEST_DISCOVER_BT = 1;
    private TextView statusBT, pairedBTd;
    private ImageView mBTimage;
    private Button onButton, offButton, discoverableBttn, pairedBttn;
    BluetoothAdapter mBlueAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_book_connect);

        statusBT = (TextView) findViewById(R.id.text1);
        pairedBTd = (TextView) findViewById(R.id.pairedDeviceView);
        mBTimage = (ImageView) findViewById(R.id.btImage);
        onButton = (Button) findViewById(R.id.onBttn);
        offButton = (Button) findViewById(R.id.offBttn);
        discoverableBttn = (Button) findViewById(R.id.discvrBttn);
        pairedBttn = (Button) findViewById(R.id.pairBttn);

        mBlueAdapter = BluetoothAdapter.getDefaultAdapter();

        if (mBlueAdapter == null) {
            statusBT.setText("Bluetooth is not available on this device");
        } else {
            statusBT.setText("Bluetooth is available on this device");
        }


        if (mBlueAdapter.isEnabled()) {
            mBTimage.setImageResource(R.drawable.baseline_bluetooth_24);
        } else {
            mBTimage.setImageResource(R.drawable.baseline_bluetooth_disabled_24);
        }


        onButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!mBlueAdapter.isEnabled()) {
                    showToast("Turning ON Bluetooth...");

                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    if (ActivityCompat.checkSelfPermission(BluetoothBookConnectActivity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivityForResult(intent, REQUEST_ENABLE_BT);
                } else {
                    showToast("Bluetooth is already ON");
                }

            }
        });

        offButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!mBlueAdapter.isEnabled()) {
                    if (ActivityCompat.checkSelfPermission(BluetoothBookConnectActivity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    mBlueAdapter.disable();
                    showToast("Turning Bluetooth Off");
                    mBTimage.setImageResource(R.drawable.baseline_bluetooth_disabled_24);
                } else {
                    showToast("Bluetooth is already Off");
                }
            }
        });

        discoverableBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ActivityCompat.checkSelfPermission(BluetoothBookConnectActivity.this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                if (!mBlueAdapter.isDiscovering()) {
                    showToast("Make your Device Discoverable");

                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    startActivityForResult(intent, REQUEST_DISCOVER_BT);
                }

            }
        });

        pairedBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mBlueAdapter.isEnabled()) {
                    pairedBTd.setText("Paired Devices");
                    if (ActivityCompat.checkSelfPermission(BluetoothBookConnectActivity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    Set<BluetoothDevice> devices = mBlueAdapter.getBondedDevices();
                    for (BluetoothDevice device : devices) {
                        pairedBTd.append("\nDevices: " + device.getName() + "," + device);
                    }
                }else{
                        showToast("Turn on Bluetooth to get paired devices");
                }

            }

        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case REQUEST_ENABLE_BT:
                if (resultCode == RESULT_OK) {
                    mBTimage.setImageResource(R.drawable.baseline_bluetooth_24);
                    showToast("Bluetooth is ON");
                }else {
                    showToast("Bluetooth is OFF");
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}