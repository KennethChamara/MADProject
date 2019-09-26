package com.example.backrow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class listMarks extends AppCompatActivity {

    ListView listView;
    String ntitle[] = {"subject 01 : 80%","subject 01 : 80%","subject 01 : 80%","subject 01 : 80%",
            "subject 01 : 80%","subject 01 : 80%","subject 01 : 80%","subject 01 : 80%"
            ,"subject 01 : 80%","subject 01 : 80%","subject 01 : 80%","subject 01 : 80%",
            "subject 01 : 80%","subject 01 : 80%","subject 01 : 80%","subject 01 : 80%"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_marks);

        ListView list = (ListView)findViewById(R.id.markslist);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,ntitle);

        list.setAdapter(arrayAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),ntitle[i],Toast.LENGTH_LONG).show();
            }
        });
    }


}
