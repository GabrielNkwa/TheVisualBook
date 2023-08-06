package com.example.thevisualbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GiftMessageActivity extends AppCompatActivity {

    private EditText mText;
    private Button okButton;
    private DatabaseReference dbReference;
    private String gift_extractor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_message);

        gift_extractor = getIntent().getExtras().get("gift_key").toString();

        dbReference = FirebaseDatabase.getInstance().getReference();
        mText = (EditText) findViewById(R.id.editTextTextMultiLine);
        okButton = (Button) findViewById(R.id.okbutton);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbReference.child("GiftCollection").child(gift_extractor).child("Message").setValue(mText.getText().toString());

                Intent nextActivity = new Intent(GiftMessageActivity.this, GiftingActivity7.class);
                nextActivity.putExtra("gift_key", gift_extractor);
                startActivity(nextActivity);
            }
        });
    }
}