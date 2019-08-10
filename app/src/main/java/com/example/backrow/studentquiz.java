package com.example.backrow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class studentquiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentquiz);

        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener(){

            public  void onClick(View v){

               startQuiz();

            }

        });

    }

    private void startQuiz(){
        Intent intent = new Intent(studentquiz.this,myquiz.class);
        startActivity(intent);

    }
}
