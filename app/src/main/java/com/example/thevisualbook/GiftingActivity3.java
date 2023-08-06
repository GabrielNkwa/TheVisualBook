package com.example.thevisualbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.thevisualbook.ui.GiftingActivity1;

public class GiftingActivity3 extends AppCompatActivity {

    private Button responseOne;
    private Button responseTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gifting3);

        responseOne = (Button) findViewById(R.id.bookResponse_1);
        responseTwo = (Button) findViewById(R.id.bookResponse_2);

        responseOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextActivity = new Intent(GiftingActivity3.this, GiftingActivity4.class);
                startActivity(nextActivity);
            }
        });


        responseTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextActivity = new Intent(GiftingActivity3.this, GiftingActivity5.class);
                startActivity(nextActivity);
            }
        });
    }
}