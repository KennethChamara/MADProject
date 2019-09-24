package com.example.backrow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminDisplayNotice extends AppCompatActivity {
    Button Update,Delete;
    EditText Title,Content,postBy,date;
    NoticeDBhelper db = new NoticeDBhelper(this);
    String title,content,postby,Date,ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_display_notice);

        Intent intent = getIntent();
        ID = intent.getStringExtra(AdminNoticeList.EXTRA_MESSAGE_ID);

        Update = findViewById(R.id.update);
        Delete = findViewById(R.id.delete);

        Title = findViewById(R.id.updateTitle);
        Content = findViewById(R.id.updatecontent);
        postBy = findViewById(R.id.updatepostby);
        date = findViewById(R.id.updatedate);

        getvalue();

        Title.setText(title);
        Content.setText(content);
        postBy.setText("Mr.Avishka");
        date.setText("11/09/2019");

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean res = db.updatenotice(Title.getText().toString(),Content.getText().toString(),ID);

                if (res == true) {
                    Toast.makeText(getApplicationContext(), "updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),AdminNoticeList.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
                }
            }
        });


        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int x = db.deleteNotice(ID);
                if(x > 0){
                    Toast.makeText(getApplicationContext(),"succussflly deleted",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),AdminNoticeList.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(),"delete failed",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void getvalue(){

            title=db.getNoticeValue(ID,"TITLE");
            content=db.getNoticeValue(ID,"DES");
            postby="Mr.Avishka";
            Date="11/09/2019";

    }
}
