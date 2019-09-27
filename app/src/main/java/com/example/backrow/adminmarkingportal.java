package com.example.backrow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class adminmarkingportal extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Button bt1;
    Button bt2;
    Button bt3;
    String examcategory;
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
        Spinner coloredSpinner =(Spinner)findViewById(R.id.coloredSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.SpinnerList_Items,
                R.layout.color_spinner_layout
        );
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        coloredSpinner.setAdapter(adapter);
        coloredSpinner.setOnItemSelectedListener(this);



    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(i != 0) {
            Intent intent=new Intent(adminmarkingportal.this,markscategorylist.class );
            intent.putExtra("CATEGORY",adapterView.getSelectedItem().toString());
            startActivity(intent);

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}

