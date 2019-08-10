package com.example.backrow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class updatedeletemarks extends AppCompatActivity {
            Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedeletemarks);

        b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button2);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Updated marks Successfully",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(updatedeletemarks.this,adminmarkingportal.class );
                startActivity(intent);
            }


        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Deleted marks Successfully",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(updatedeletemarks.this,adminmarkingportal.class );
                startActivity(intent);
            }


        });




    }
}
