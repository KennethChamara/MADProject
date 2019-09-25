package com.example.backrow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class User_Option extends AppCompatActivity {

    Button btnManege,btnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__option);
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btnManege = findViewById(R.id.btnManege);
        btnList = findViewById(R.id.btnUserlist);


//        btnManege.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(),User_Manger.class));
//            }
//        });
//        btnList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(),List_Users.class));
//            }
//        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
