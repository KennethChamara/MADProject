package com.example.backrow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import marksDB.marksdbhelper;

public class submitmarks extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
        Button btt;
        Button back;
        EditText txt1;
        EditText txt2;
        String examcategory;
        long result;

        marksdbhelper dbhelper=new marksdbhelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submitmarks);
        txt1=(EditText) findViewById(R.id.studentId);
        txt2=(EditText) findViewById(R.id.marks);
        btt=(Button)findViewById(R.id.submitbtn);
        back=(Button)findViewById(R.id.backbtn);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        btt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               double marks = Double.parseDouble(txt2.getText().toString());
               // Toast.makeText(getApplicationContext(),"Added Succesfully",Toast.LENGTH_SHORT).show();
               result= dbhelper.addmarks(txt1.getText().toString(),examcategory,marks);

                    if(result==-1)
                Toast.makeText(getApplicationContext(),"Checked values before enter",Toast.LENGTH_SHORT).show();
                    else
                 Toast.makeText(getApplicationContext(),"Added Succesfully",Toast.LENGTH_SHORT).show();

            }
        });

            back=(Button)findViewById(R.id.backbtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(submitmarks.this,adminmarkingportal.class );
                startActivity(intent);
            }
        });










        Spinner coloredSpinner =(Spinner)findViewById(R.id.coloredSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
             this,
                R.array.Spinner_Items,
                R.layout.color_spinner_layout
        );
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        coloredSpinner.setAdapter(adapter);
        coloredSpinner.setOnItemSelectedListener(this);



    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
       // Toast.makeText(this,adapterView.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
        examcategory=adapterView.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
