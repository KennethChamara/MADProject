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

       bt1=(Button)findViewById(R.id.addExamTypebtn);
        // bt2=(Button)findViewById(R.id.button2);
       //  bt3=(Button)findViewById(R.id.buton3);

        bt1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(adminmarkingportal.this,Add_Exam_Type.class );
                startActivity(intent);
            }
      });

       // bt2.setOnClickListener(new View.OnClickListener() {
            //@Override
         //   public void onClick(View view) {
            //    Intent intent=new Intent(adminmarkingportal.this,updatedeletemarks.class );
             //   startActivity(intent);
       //     }
     //   });

     //  bt3.setOnClickListener(new View.OnClickListener() {
           // @Override
          //  public void onClick(View view) {
              //  Intent intent=new Intent(adminmarkingportal.this,listMarks.class );
             //   startActivity(intent);
      //      }
     //   });

    }
}
