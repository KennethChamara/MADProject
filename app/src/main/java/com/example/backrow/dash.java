package com.example.backrow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class dash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);
    }

    public void onClickVideo(View view){

        startActivity(new Intent( this,VideoActivity1_Admin.class));


    }

    public void onClickMarks(View view){

        startActivity(new Intent(".adminmarkingportal"));


    }

    public void onClicknotice(View view){

        startActivity(new Intent(dash.this,adminNoticeNavigation.class));


    }
    public void onClickQuiz(View view){

        startActivity(new Intent(dash.this,Quiz_opition.class));


    }

    public void onClickUser(View view){

        startActivity(new Intent(dash.this,User_Option.class));


    }



}
