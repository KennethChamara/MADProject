package com.example.backrow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class displayNotice extends AppCompatActivity {
    NoticeDBhelper db = new NoticeDBhelper(this);
    TextView Title,Content,Date,Postby;
    String ID,title,postby,content,date;
    ImageView image;

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
        image = findViewById(R.id.user_display_notice);

        setvalue();

    }
    public void setvalue(){
        byte[] img = null;

        Cursor cursor = db.getNoticeValue(ID);

        if(cursor.moveToFirst()){
            //int ID = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Notice._ID)));
            title = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Notice.COLUMN_NAME_TITLE));
            content = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Notice.COLUMN_NAME_DESCRIPTION));
            date = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Notice.COLUMN_NAME_DATE));
            img = cursor.getBlob(cursor.getColumnIndexOrThrow(UsersMaster.Notice.COLUMN_NAME_IMAGE));
        }
        cursor.close();

        Bitmap bitmap = BitmapFactory.decodeByteArray(img,0,img.length);

        Title.setText(title);
        Content.setText(content);
        Postby.setText("Mr.Avishka");
        Date.setText(date);
        image.setImageBitmap(bitmap);
    }
}