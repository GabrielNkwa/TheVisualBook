package com.example.thevisualbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class GiftingActivity6 extends AppCompatActivity {

    private Button proceed;
    private TextView recipient_name_txtV;
    private TextView random_virtual_code;
    String userAccount_email;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gifting6);

        userAccount_email = getIntent().getExtras().get("recipient_name1").toString();
        recipient_name_txtV = (TextView) findViewById(R.id.intro_exp21);
        recipient_name_txtV.setText(userAccount_email);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("GiftCollection");

        random_virtual_code = (TextView) findViewById(R.id.intro_code);

        final Random random = new Random();
        String randomNumber = String.valueOf(random.nextInt(999));
        String randomNumber2 = String.valueOf(random.nextInt(999));
        random_virtual_code.setText(randomNumber+"-"+randomNumber2);

        databaseReference.child(random_virtual_code.getText().toString()).child("Gift_Code").setValue(random_virtual_code.getText().toString());


        proceed = (Button) findViewById(R.id.bookAnswer_1);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child(random_virtual_code.getText().toString()).child("Recipient").setValue(recipient_name_txtV.getText().toString());

                Intent nextActivity = new Intent(GiftingActivity6.this, GiftingActivity7.class);
                nextActivity.putExtra("gift_recipient", (recipient_name_txtV.getText().toString()));
                nextActivity.putExtra("gift_key", (random_virtual_code.getText().toString()));
                startActivity(nextActivity);
            }
        });

    }
}