package com.example.backrow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class noticePanel extends AppCompatActivity {

    public static final String EXTRA_MESSAGE_ID = "com.example.backrow.ID";
    NoticeDBhelper db = new NoticeDBhelper(this);
    ListView listView;
    ArrayList<String> ntitle,ndis,ID,postby,date;
    ArrayList<Bitmap> bitmaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_panel);
        ID = new ArrayList<String>();
        ntitle  = new ArrayList<String>();
        ndis = new ArrayList<String>();
        postby = new ArrayList<String>();
        date = new ArrayList<String>();
        bitmaps = new ArrayList<Bitmap>();
        listView = findViewById(R.id.listview);

        setvaluse();

        myAdapter adapter = new myAdapter(this, ntitle, ndis,bitmaps,date,postby);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(noticePanel.this,displayNotice.class);
                intent.putExtra(EXTRA_MESSAGE_ID, ID.get(i));
                startActivity(intent);
            }
        });

    }

    public void setvaluse() {
        Cursor cursor = db.readAllNotice();

        while(cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Notice._ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Notice.COLUMN_NAME_TITLE));
            String Des = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Notice.COLUMN_NAME_DESCRIPTION));
            String Date = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Notice.COLUMN_NAME_DATE));
            byte[] img = cursor.getBlob(cursor.getColumnIndexOrThrow(UsersMaster.Notice.COLUMN_NAME_IMAGE));

            Bitmap bitmap = BitmapFactory.decodeByteArray(img,0,img.length);

            ID.add(id);
            ntitle.add(title);
            ndis.add(Des);
            postby.add("Mr.Avishka");
            date.add(Date);
            bitmaps.add(bitmap);
        }
        cursor.close();
    }

    class myAdapter extends ArrayAdapter<String> {
        Context context;
        ArrayList<String> title;
        ArrayList<String> description;
        ArrayList<String> date;
        ArrayList<String> pstby;
        ArrayList<Bitmap> bitmaps;

        myAdapter(Context c,ArrayList<String> t,ArrayList<String> des,ArrayList<Bitmap> bitmaps,ArrayList<String> date,ArrayList<String> pstby){
            super(c,R.layout.rownotice,R.id.user_notice_title,t);
            this.context = c;
            this.title = t;
            this.description = des;
            this.date = date;
            this.pstby = pstby;
            this.bitmaps = bitmaps;
        }

        @NonNull
        @Override
        public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.rownotice,parent,false);
            TextView mytitle = row.findViewById(R.id.user_notice_title);
            TextView des = row.findViewById(R.id.ndis);
            TextView Date = row.findViewById(R.id.rownotice_date);
            TextView Pstby = row.findViewById(R.id.rownotice_postby);
            ImageView img = row.findViewById(R.id.notice_disply_img);
            mytitle.setText(title.get(position));
            des.setText(description.get(position));
            Date.setText(date.get(position));
            Pstby.setText(pstby.get(position));
            img.setImageBitmap(bitmaps.get(position));
            return row;
        }
    }
}