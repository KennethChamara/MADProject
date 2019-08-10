package com.example.backrow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class displayNotice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_notice);

        Intent intent = getIntent();
        String message = intent.getStringExtra(noticePanel.EXTRA_MESSAGE);

        TextView textView = findViewById(R.id.textView3);
        textView.setText(message);
    }
}