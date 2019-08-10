package com.example.backrow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }



    public void onClick(View v){

        EditText user = findViewById(R.id.user);
        EditText  password = findViewById(R.id.password);

        if (user.getText().toString().equals("admin") && password.getText().toString().equals("admin") ) {

            startActivity(new Intent(".dash"));



        }else{
            if(user.getText().toString().equals("student") && password.getText().toString().equals("1234"))
            {

                startActivity(new Intent(".navgation"));

            }

            Toast.makeText(this,"Invalid User name or Password",Toast.LENGTH_SHORT).show();

        }


    }


}