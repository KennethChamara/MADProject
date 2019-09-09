package com.example.backrow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VideoActivity1 extends AppCompatActivity {
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video1);

        btn = (Button) findViewById(R.id.button18);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViedeos();
            }
        });
    }

    public void findViedeos(){
        Intent intent = new Intent(VideoActivity1.this,VideoActivity2.class);
        startActivity(intent);
    }
}
