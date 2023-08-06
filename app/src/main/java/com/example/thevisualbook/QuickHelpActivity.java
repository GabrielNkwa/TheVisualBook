package com.example.thevisualbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class QuickHelpActivity extends AppCompatActivity {

    private ImageView insta, twitter, tiktok, youTub;
    private TextView emailSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_help);

        insta = (ImageView) findViewById(R.id.instaBttn);
        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.instagram.com/thevisualbook/");
            }
        });

        twitter = (ImageView) findViewById(R.id.twttrBttn);
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://pin.it/7eBDrlr");
            }
        });

        tiktok = (ImageView) findViewById(R.id.tiktikBttn);
        tiktok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.tiktok.com/@thevisualbook/");
            }
        });

        youTub = (ImageView) findViewById(R.id.youTBttn);
        youTub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.youtube.com/@the.visualbook");
            }
        });

        emailSend = (TextView) findViewById(R.id.supportEmail);
        emailSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmailToVB();
            }
        });

    }


    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    private void sendEmailToVB() {
        String[] TO_EMAil = {"system.thevisualbook@yahoo.com"};

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto"));
        intent.putExtra(Intent.EXTRA_EMAIL, TO_EMAil);

        intent.putExtra(Intent.EXTRA_SUBJECT, "");
        intent.putExtra(Intent.EXTRA_TEXT, "");

        startActivity(Intent.createChooser(intent, "Choose an email application"));
    }

}