package com.example.backrow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import marksDB.marksdbhelper;

public class update_delete_marks extends AppCompatActivity {
        String studentId;
        String Marks;
        String Studentcatgry;
        String marksId;
        marksdbhelper db=new marksdbhelper(this);

        TextView viewstdId;
        EditText viewMarks;
        EditText viewcatagry;

        Button updatebtn;
        Button deletebtn;
        Button backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_marks);

        Intent intent = getIntent();
        studentId =intent.getStringExtra("STUDENTID");
        Marks=intent.getStringExtra("MARKS");
        Studentcatgry=intent.getStringExtra("category");
        marksId=intent.getStringExtra("MARKSID");

        viewstdId=(TextView)findViewById(R.id.updtestdId);
        viewMarks=(EditText)findViewById(R.id.updatemarks);
        viewcatagry=(EditText)findViewById(R.id.updatecategory);


        updatebtn=(Button)findViewById(R.id.updatebtn);
        deletebtn=(Button)findViewById(R.id.deletebtn);
        backbtn=(Button)findViewById(R.id.backbtn);

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double mark = Double.parseDouble(viewMarks.getText().toString());
                int val = db.updateMarks(viewstdId.getText().toString(),mark,marksId);
                if (val > 0){
                    Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(update_delete_marks.this,markscategorylist.class );
                    intent.putExtra("CATEGORY",viewcatagry.getText().toString());
                    startActivity(intent);
                }
            }
        });

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long val = db.deleteNotice(marksId);
                if (val > 0){
                    Toast.makeText(getApplicationContext(),"deleted",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(update_delete_marks.this,markscategorylist.class );
                    intent.putExtra("CATEGORY",viewcatagry.getText().toString());
                    startActivity(intent);
                }
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(update_delete_marks.this,adminmarkingportal.class );
                startActivity(intent);

            }
        });

        viewstdId.setText(studentId);
        viewMarks.setText(Marks);
        viewcatagry.setText(Studentcatgry);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


    }
}
