package com.example.backrow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.example.backrow.NoticeNotification.CHANNEL_ID;

public class addNotice extends AppCompatActivity {
    private NotificationManagerCompat notificationManager;
    private Button add,reset,btnChoose;
    private EditText title,des;
    private ImageView image;
    private NoticeDBhelper db;
    private static final String  adds = "Successfully Added";
    private static final String fail = "adding Faild";
    private static final String ress = "Reset";
    private static final int REQUEST_CODE_GALLERY = 999;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notice);

        db = new NoticeDBhelper(this);
        notificationManager = NotificationManagerCompat.from(this);
        add = (Button)findViewById(R.id.addnoticebuton);
        reset = (Button)findViewById(R.id.noticereset);
        btnChoose = (Button)findViewById(R.id.notice_choose_image);
        title = findViewById(R.id.add_title);
        des = findViewById(R.id.updatecontent);
        image = findViewById(R.id.notice_image);

        title.addTextChangedListener(textWatcher);
        des.addTextChangedListener(textWatcher);

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        addNotice.this,
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });



/*
        des.setOnTouchListener(new V iew.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                des.setTranslationY(-400f);
                return false;
            }
        });



        des.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {


                if(event != null){
                    InputMethodManager in = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(des.getApplicationWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                }
                des.setTranslationY(0f);

                return false;
            }
        });
*/

        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean res = db.addnotice(title.getText().toString(),des.getText().toString());

                if (res == true) {
                    Intent intent=new Intent(getApplicationContext(),AdminNoticeList.class );
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), fail, Toast.LENGTH_SHORT).show();
                }

                sedNotification(new View(getApplicationContext()));

                emptytext();
            }
        });



        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                emptytext();
                Toast.makeText(getApplicationContext(),ress,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE_GALLERY){
            if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_GALLERY);
            } else {
                Toast.makeText(getApplicationContext(),"You don't have premission",Toast.LENGTH_SHORT);
            }

            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    if(requestCode == REQUEST_CODE_GALLERY && requestCode == RESULT_OK && data != null){
        Uri uri = data.getData();

        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            image.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void sedNotification(View V){
        String n_title = title.getText().toString();
        String n_message = "this is test";

        Notification notification =new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.drawable.notice_notification)
                .setContentTitle(n_title)
                .setContentText(n_message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManager.notify(1,notification);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String title_text = title.getText().toString();
            String des_text = des.getText().toString();

            add.setEnabled(!title_text.isEmpty() && !des_text.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void emptytext(){
        title.setText("");
        des.setText("");
    }
}
