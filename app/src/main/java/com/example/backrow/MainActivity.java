package com.example.backrow;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

            Toast.makeText(this,"Invalid User name or Password",Toast.LENGTH_SHORT).show();

        }


    }


}