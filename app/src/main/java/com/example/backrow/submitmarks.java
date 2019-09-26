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

public class submitmarks extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
        Button btt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submitmarks);
        //btt=(Button)findViewById(R.id.submitbutton);

       // btt.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View view) {

                //Toast.makeText(getApplicationContext(),"Added Succesfully",Toast.LENGTH_SHORT).show();
                //Intent intent=new Intent(submitmarks.this,adminmarkingportal.class );
                //startActivity(intent);

           // }
       // });

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
        Toast.makeText(this,adapterView.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
