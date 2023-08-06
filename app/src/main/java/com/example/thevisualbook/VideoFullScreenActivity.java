package com.example.thevisualbook;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import java.net.URI;

public class VideoFullScreenActivity extends AppCompatActivity {

    VideoView vView;
    MediaController mediaController;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_full_screen);

        vView = (VideoView) findViewById(R.id.singleVideoView);

        url = getIntent().getExtras().get("imgID").toString();

        Uri uri = Uri.parse(url);
        vView.setVideoURI(uri);

        mediaController = new MediaController(this);
        mediaController.setMediaPlayer(vView);

        vView.setMediaController(mediaController);
        vView.requestFocus();

        vView.start();
    }
}