package com.example.thevisualbook;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.thevisualbook.databinding.ActivityQrbookNamingBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class QRBookNamingActivity extends AppCompatActivity {

    private EditText vbook_name;
    private Button proceed;
    private String userAuthor_name;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrbook_naming);

        userAuthor_name = getIntent().getExtras().get("bookAuthor_QRcode").toString();
        vbook_name = (EditText) findViewById(R.id.intro_Sendname);
        proceed = (Button) findViewById(R.id.bookResponse_2);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("GiftCollection").child(userAuthor_name);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(vbook_name.getText().toString())){
                    //email is empty
                    Toast.makeText(QRBookNamingActivity.this, "Please provide a name or title for your Visual Book", Toast.LENGTH_SHORT).show();
                    // stop the function from executing further
                    //  return;
                } else {

                    databaseReference.child("Author").setValue(vbook_name.getText().toString());

                    Intent nextActivity = new Intent(QRBookNamingActivity.this, GiftingActivity7.class);
                    nextActivity.putExtra("gift_key", userAuthor_name);
                    nextActivity.putExtra("recipient_name1", (vbook_name.getText().toString()));
                    startActivity(nextActivity);
                }




            }
        });

    }

}