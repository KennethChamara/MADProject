package com.example.backrow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import marksDB.marks;
import marksDB.marksdbhelper;

public class maksListUserNav extends AppCompatActivity {

    ArrayList<Integer> marksId;
    ArrayList<String> studentId;
    ArrayList<Double> Studentmarks;

    marksdbhelper db = new marksdbhelper(this);
    String categry;
    Cursor cus;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maks_list_user_nav);

        Intent intent = getIntent();
        categry = intent.getStringExtra("CATEGORY");
        addingvalues(categry);

        listView = findViewById(R.id.usermarkslist);

        marksAdapter adapter = new marksAdapter(this, studentId, Studentmarks);
        listView.setAdapter(adapter);

    }

    public void addingvalues(String examcategory) {
        cus = db.readAllmarkByCategory(examcategory);

        marksId = new ArrayList<Integer>();
        studentId = new ArrayList<String>();
        Studentmarks = new ArrayList<Double>();

        while (cus.moveToNext()) {
            int Id_Of_Marks = Integer.parseInt(cus.getString(cus.getColumnIndexOrThrow(marks.MARKSID)));
            String Id_Of_Students = cus.getString(cus.getColumnIndexOrThrow(marks.STUDENTID));
            double marks_of_Students = Double.parseDouble(cus.getString(cus.getColumnIndexOrThrow(marks.MARKS)));
            String category_Of_Students = cus.getString(cus.getColumnIndexOrThrow(marks.MARKSCATEGORY));

            marksId.add(Id_Of_Marks);
            studentId.add(Id_Of_Students);
            Studentmarks.add(marks_of_Students);


        }
        cus.close();
    }

    class marksAdapter extends ArrayAdapter {

        Context context;
        ArrayList<String> adapterStdID;
        ArrayList<Double> adapterStdmarks;

        marksAdapter(Context c, ArrayList<String> adapterStudentID, ArrayList<Double> adapterStudentdmarks) {

            super(c, R.layout.customizedcategorylist, R.id.stdId, adapterStudentID);
            this.context = c;
            this.adapterStdID = adapterStudentID;
            this.adapterStdmarks = adapterStudentdmarks;


        }

        @NonNull
        @Override
        public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.customizedcategorylist, parent, false);
            TextView sId = row.findViewById(R.id.stdId);
            TextView Smks = row.findViewById(R.id.stdMarks);
            sId.setText(adapterStdID.get(position));
            Smks.setText(adapterStdmarks.get(position).toString());
            return row;
        }
    }
}
