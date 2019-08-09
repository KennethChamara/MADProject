package com.example.backrow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity2 extends AppCompatActivity {
    VideoView videoView;

    MediaController mController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video2);

        videoView = (VideoView) findViewById(R.id.videoViewId);

        mController = new MediaController(this);

        videoView.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.viedeo1);

        mController.setAnchorView(videoView);

        videoView.setMediaController(mController);

        videoView.start();
    }
}
