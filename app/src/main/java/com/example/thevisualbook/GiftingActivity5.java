package com.example.thevisualbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GiftingActivity5 extends AppCompatActivity {

    private EditText receipient_name;
    private Button proceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gifting5);

        receipient_name = (EditText) findViewById(R.id.intro_Sendname);
        proceed = (Button) findViewById(R.id.bookResponse_2);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(receipient_name.getText().toString())){
                    //email is empty
                    Toast.makeText(GiftingActivity5.this, "Please the recipient name or title", Toast.LENGTH_SHORT).show();
                    // stop the function from executing further
                    //  return;
                } else {
                    Intent nextActivity = new Intent(GiftingActivity5.this, GiftingActivity6.class);
                    nextActivity.putExtra("recipient_name1", (receipient_name.getText().toString()));
                    startActivity(nextActivity);
                }




            }
        });
    }
}