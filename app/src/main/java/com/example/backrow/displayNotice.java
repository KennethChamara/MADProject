package com.example.backrow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class displayNotice extends AppCompatActivity {
    NoticeDBhelper db = new NoticeDBhelper(this);
    TextView Title,Content,Date,Postby;
    String ID,title,postby,content,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_notice);

        Intent intent = getIntent();
        ID = intent.getStringExtra(noticePanel.EXTRA_MESSAGE_ID);

        Title = findViewById(R.id.user_notice_title);
        Content = findViewById(R.id.user_notice_content);
        Postby = findViewById(R.id.user_notice_postby);
        Date = findViewById(R.id.user_notice_date);

        getvalue();

        Title.setText(title);
        Content.setText(content);
        Postby.setText(postby);
        Date.setText(date);

    }
    public void getvalue(){

        title=db.getNoticeValue(ID,"TITLE");
        content=db.getNoticeValue(ID,"DES");
        postby="Mr.Avishka";
        date="11/09/2019";
    }
}