package com.example.backrow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity1_Admin extends AppCompatActivity {
    VideoView videoView;
    MediaController mController;
     Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_activity1__admin);

        videoView = (VideoView) findViewById(R.id.videoView_admin);

        mController = new MediaController(this);

        videoView.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.viedeo1);

        mController.setAnchorView(videoView);

        videoView.setMediaController(mController);

        videoView.start();

        btn = (Button) findViewById(R.id.btnAdmin3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViedeos();
            }
        });
    }
    public void findViedeos(){
        Intent intent = new Intent(VideoActivity1_Admin.this,VideoActivity2_Admin.class);
        startActivity(intent);
    }
}
