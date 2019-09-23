package com.example.backrow;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Quiz_opition extends AppCompatActivity {

    Button btnInsert,btnChange,btnList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_opition);



    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
