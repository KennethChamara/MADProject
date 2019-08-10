package com.example.backrow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class addNotice extends AppCompatActivity {
    Button add;
    Button reset;

    String adds = "Successfully Added";
    String ress = "Reset";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notice);

        add = (Button)findViewById(R.id.addnoticebuton);
        reset = (Button)findViewById(R.id.noticereset);

        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),adds,Toast.LENGTH_SHORT).show();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),ress,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
