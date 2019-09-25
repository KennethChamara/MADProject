package com.example.backrow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdminNoticeList extends AppCompatActivity {
    public static final String EXTRA_MESSAGE_ID = "com.example.backrow.ID";
    NoticeDBhelper db = new NoticeDBhelper(this);
    ListView listView;
    String ntitle[];
    String ndis[];
    String ID[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notice_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.listview);

        setvaluse();

        myAdapter adapter = new myAdapter(this, ntitle, ndis);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(AdminNoticeList.this,AdminDisplayNotice.class);
                intent.putExtra(EXTRA_MESSAGE_ID, ID[i]);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminNoticeList.this,addNotice.class );
                startActivity(intent);
            }
        });


    }

    public void setvaluse() {
        ArrayList<String> list = db.readAllNotice("TITLE");
        ArrayList<String> list2 = db.readAllNotice("DES");
        ArrayList<String> IDlist = db.readAllNotice("ID");

        ntitle = new String[list.size()];

        for (int j = 0; j < list.size(); j++) {
            ntitle[j] = list.get(j);
        }

        ndis = new String[list2.size()];

        for (int j = 0; j < list2.size(); j++) {
            ndis[j] = list2.get(j);
        }

        ID = new String[IDlist.size()];
        for (int j = 0; j < IDlist.size(); j++) {
            ID[j] = IDlist.get(j);
        }

    }

    class myAdapter extends ArrayAdapter<String> {

        Context context;
        String title[];
        String description[];

        myAdapter(Context c,String t[],String des[]){
            super(c,R.layout.rownotice,R.id.user_notice_title,t);
            this.context = c;
            this.title = t;
            this.description = des;
        }

        @NonNull
        @Override
        public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.rownotice,parent,false);
            TextView mytitle = row.findViewById(R.id.user_notice_title);
            TextView des = row.findViewById(R.id.ndis);
            mytitle.setText(title[position]);
            des.setText(description[position]);
            return row;
        }
    }
}
