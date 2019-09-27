package com.example.backrow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class update_delete_marks extends AppCompatActivity {
        String studentId;
        String Marks;
        String Studentcatgry;
        String marksId;

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

        viewstdId.setText(studentId);
        viewMarks.setText(Marks);
        viewcatagry.setText(Studentcatgry);

    }
}
