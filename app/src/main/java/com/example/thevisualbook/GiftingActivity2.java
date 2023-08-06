package com.example.thevisualbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GiftingActivity2 extends AppCompatActivity {

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gifting2);

        mButton = (Button) findViewById(R.id.bookAnswer_1);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent mIntent = new Intent(GiftingActivity2.this, BluetoothBookConnectActivity.class);
                Intent mIntent = new Intent(GiftingActivity2.this, GiftingActivity4.class);
                startActivity(mIntent);
            }
        });
    }
}