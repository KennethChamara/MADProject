package com.example.backrow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AdminDisplayNotice extends AppCompatActivity {
    Button Update,Delete,btnChoose;
    EditText Title,Content,postBy,date;
    NoticeDBhelper db = new NoticeDBhelper(this);
    String title,content,postby,Date,ID;
    ImageView image;
    private static final int REQUEST_CODE_GALLERY = 999;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_display_notice);

        Intent intent = getIntent();
        ID = intent.getStringExtra(AdminNoticeList.EXTRA_MESSAGE_ID);

        Update = findViewById(R.id.update);
        Delete = findViewById(R.id.delete);
        btnChoose = (Button)findViewById(R.id.notice_update_choose_image);

        Title = findViewById(R.id.updateTitle);
        Content = findViewById(R.id.updatecontent);
        postBy = findViewById(R.id.updatepostby);
        date = findViewById(R.id.updatedate);
        image = findViewById(R.id.admin_dis_notice_image);

        getvalue();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byte[] imageByte = imageViewToByte(image);
                boolean res = db.updatenotice(Title.getText().toString(),Content.getText().toString(),ID,imageByte);

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

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        AdminDisplayNotice.this,
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });
    }

    private byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
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

        if(requestCode == REQUEST_CODE_GALLERY){
            Uri uri = data.getData();

            try{
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                image.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void getvalue(){

        byte[] img = null;

        Cursor cursor = db.getNoticeValue(ID);

        if(cursor.moveToFirst()){
            //int ID = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Notice._ID)));
            title = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Notice.COLUMN_NAME_TITLE));
            content = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Notice.COLUMN_NAME_DESCRIPTION));
            Date = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Notice.COLUMN_NAME_DATE));
            img = cursor.getBlob(cursor.getColumnIndexOrThrow(UsersMaster.Notice.COLUMN_NAME_IMAGE));
        }
        cursor.close();

        Bitmap bitmap = BitmapFactory.decodeByteArray(img,0,img.length);

        Title.setText(title);
        Content.setText(content);
        postBy.setText("Mr.Avishka");
        date.setText(Date);
        image.setImageBitmap(bitmap);
    }
}
