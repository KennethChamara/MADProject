package com.example.backrow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class submitmarks extends AppCompatActivity {
        Button btt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submitmarks);
        btt=(Button)findViewById(R.id.submitbutton);

        btt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),"Added Succesfully",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(submitmarks.this,adminmarkingportal.class );
                startActivity(intent);

            }
        });


    }
}
