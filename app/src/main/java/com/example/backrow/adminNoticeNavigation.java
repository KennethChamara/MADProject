package com.example.backrow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class adminNoticeNavigation extends AppCompatActivity {
    Button addNotice;
    Button listnotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notice_navigation);

        addNotice = (Button) findViewById(R.id.adnoticebuton);

        listnotice = (Button) findViewById(R.id.listnoticebuton);

        addNotice.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent(adminNoticeNavigation.this,addNotice.class );
                startActivity(intent);
            }
        });

        listnotice.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent(adminNoticeNavigation.this,noticePanel.class );
                startActivity(intent);
            }
        });
    }
}
