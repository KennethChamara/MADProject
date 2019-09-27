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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class noticePanel extends AppCompatActivity {

    public static final String EXTRA_MESSAGE_ID = "com.example.backrow.ID";
    NoticeDBhelper db = new NoticeDBhelper(this);
    ListView listView;
    ArrayList<String> ntitle,ndis,ID;
    ArrayList<Bitmap> bitmaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_panel);
        ID = new ArrayList<String>();
        ntitle  = new ArrayList<String>();
        ndis = new ArrayList<String>();
        bitmaps = new ArrayList<Bitmap>();
        listView = findViewById(R.id.listview);

        setvaluse();

        noticePanel.myAdapter adapter = new noticePanel.myAdapter(this, ntitle, ndis,bitmaps);
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
            byte[] img = cursor.getBlob(cursor.getColumnIndexOrThrow(UsersMaster.Notice.COLUMN_NAME_IMAGE));

            Bitmap bitmap = BitmapFactory.decodeByteArray(img,0,img.length);

            ID.add(id);
            ntitle.add(title);
            ndis.add(Des);
            bitmaps.add(bitmap);
        }
        cursor.close();
    }

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