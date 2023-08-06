package com.example.thevisualbook.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.thevisualbook.GiftingActivity2;
import com.example.thevisualbook.GiftingActivity3;
import com.example.thevisualbook.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GiftingActivity1 extends AppCompatActivity {

    private Button responseOne;
    private Button responseTwo;
    private TextView textView;
    private DatabaseReference mDatabaseRef;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gifting1);

        responseOne = (Button) findViewById(R.id.bookAnswer_1);
        responseTwo = (Button) findViewById(R.id.bookAnswer_2);

        textView = (TextView) findViewById(R.id.greeting_user);

        mAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("UserAccounts").child(mAuth.getCurrentUser().getUid()).child("User_Name");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String username = snapshot.getValue(String.class);
                textView.setText("Hi " + username + ",");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        responseOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextActivity = new Intent(GiftingActivity1.this, GiftingActivity2.class);
                startActivity(nextActivity);
            }
        });

        responseTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent proceedingActivity = new Intent(GiftingActivity1.this, GiftingActivity3.class);
                startActivity(proceedingActivity);
            }
        });
    }
}