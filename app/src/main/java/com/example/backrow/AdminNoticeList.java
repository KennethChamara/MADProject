package com.example.backrow;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

//this activity will use only by Admin and display all notices and provide add button
public class AdminNoticeList extends AppCompatActivity {
    public static final String EXTRA_MESSAGE_ID = "com.example.backrow.ID";
    NoticeDBhelper db = new NoticeDBhelper(this);
    ListView listView;
    ArrayList<String> ntitle,ndis,ID;
    ArrayList<Bitmap> bitmaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notice_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ID = new ArrayList<String>();
        ntitle  = new ArrayList<String>();
        ndis = new ArrayList<String>();
        bitmaps = new ArrayList<Bitmap>();
        listView = findViewById(R.id.listview);

        setvaluse();//for set values for arrays

        myAdapter adapter = new myAdapter(this, ntitle, ndis,bitmaps);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(AdminNoticeList.this,AdminDisplayNotice.class);
                intent.putExtra(EXTRA_MESSAGE_ID, ID.get(i));
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

    //get data from database and assign into array lists
    public void setvaluse() {
        //readAllNotice() will return Cursor object
        Cursor cursor = db.readAllNotice();

        //untill there is values in cursor
        while(cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Notice._ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Notice.COLUMN_NAME_TITLE));
            String Des = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Notice.COLUMN_NAME_DESCRIPTION));
            byte[] img = cursor.getBlob(cursor.getColumnIndexOrThrow(UsersMaster.Notice.COLUMN_NAME_IMAGE));

            Bitmap bitmap = BitmapFactory.decodeByteArray(img,0,img.length);

            ID.add(id);
            ntitle.add(title);
            ndis.add(Des);
            bitmaps.add(bitmap);
        }
        cursor.close(); //close cursor
    }

    //customized ArrayAdapter
    class myAdapter extends ArrayAdapter<String> {

        Context context;
        ArrayList<String> title;
        ArrayList<String> description;
        ArrayList<Bitmap> bitmaps;

        myAdapter(Context c,ArrayList<String> t,ArrayList<String> des,ArrayList<Bitmap> bitmaps){
            super(c,R.layout.rownotice,R.id.user_notice_title,t);
            this.context = c;
            this.title = t;
            this.description = des;
            this.bitmaps = bitmaps;
        }

        @NonNull
        @Override
        public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.rownotice,parent,false);
            TextView mytitle = row.findViewById(R.id.user_notice_title);
            TextView des = row.findViewById(R.id.ndis);
            ImageView img = row.findViewById(R.id.notice_disply_img);
            mytitle.setText(title.get(position));
            des.setText(description.get(position));
            img.setImageBitmap(bitmaps.get(position));
            return row;
        }
    }
}
