package com.example.backrow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class adminmarkingportal extends AppCompatActivity {
    Button bt1;
    Button bt2;
    Button bt3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminmarkingportal);


        bt2=(Button)findViewById(R.id.button1);
       //  bt3=(Button)findViewById(R.id.buton3);



        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
                Intent intent=new Intent(adminmarkingportal.this,submitmarks.class );
                startActivity(intent);
            }
        });

     //  bt3.setOnClickListener(new View.OnClickListener() {
           // @Override
          //  public void onClick(View view) {
              //  Intent intent=new Intent(adminmarkingportal.this,listMarks.class );
             //   startActivity(intent);
      //      }
     //   });

    }
}
