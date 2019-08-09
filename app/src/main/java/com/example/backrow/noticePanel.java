package com.example.backrow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class noticePanel extends AppCompatActivity {

    ListView listView;
    String ntitle[] = {"Notice 01","Notice 02","Notice 03","Notice 04"};
    String ndis[] = {"notice discription 01 this is samplae data","notice discription 02 this is samplae data"
            ,"notice discription 03 this is samplae data","notice discription 04 this is samplae data"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_panel);
        listView = findViewById(R.id.listview);

        myAdapter adapter = new myAdapter(this, ntitle, ndis);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(noticePanel.this,ntitle[i],Toast.LENGTH_SHORT).show();
            }
        });

    }

    class myAdapter extends ArrayAdapter<String>{

        Context context;
        String title[];
        String description[];

        myAdapter(Context c,String t[],String des[]){
            super(c,R.layout.rownotice,R.id.ntitle,t);
            this.context = c;
            this.title = t;
            this.description = des;
        }

        @NonNull
        @Override
        public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.rownotice,parent,false);
            TextView mytitle = row.findViewById(R.id.ntitle);
            TextView des = row.findViewById(R.id.ndis);
            mytitle.setText(title[position]);
            des.setText(description[position]);

            return row;
        }
    }
}