package com.example.thevisualbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class GiftPictureActivity extends AppCompatActivity {

    ImageView mViewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_picture);

        mViewer = (ImageView) findViewById(R.id.viewImager);

        Intent intentnew = getIntent();
        //String address = intentnew.getExtras().getString("imgID");
        int image = intentnew.getExtras().getInt("imgID");

        mViewer.setImageResource(image);
    }
}