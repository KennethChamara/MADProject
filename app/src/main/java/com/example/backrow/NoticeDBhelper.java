package com.example.backrow;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoticeDBhelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ClassInf.DB";

    public NoticeDBhelper(@Nullable Context context) {
        super(context,DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_ENTERIS = "CREATE TABLE " +UsersMaster.Notice.TABLE_NAME+" ("+
                UsersMaster.Notice._ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                UsersMaster.Notice.COLUMN_NAME_TITLE + " TEXT," +
                UsersMaster.Notice.COLUMN_NAME_DESCRIPTION + " TEXT," +
                UsersMaster.Notice.COLUMN_NAME_DATE + " TEXT)";
        sqLiteDatabase.execSQL(SQL_CREATE_ENTERIS);
    }

    public boolean addnotice(String title,String des){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/M/yyyy hh:mm");
        String toDay = formatter.format(date);

        values.put(UsersMaster.Notice.COLUMN_NAME_TITLE,title);
        values.put(UsersMaster.Notice.COLUMN_NAME_DESCRIPTION,des);
        values.put(UsersMaster.Notice.COLUMN_NAME_DATE,toDay);

        long newRowId = db.insert(UsersMaster.Notice.TABLE_NAME,null,values);

        if(newRowId == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean updatenotice(String title,String des,String ID){
        SQLiteDatabase db = getWritableDatabase();

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String toDay = formatter.format(date);

        ContentValues values = new ContentValues();

        values.put(UsersMaster.Notice.COLUMN_NAME_TITLE,title);
        values.put(UsersMaster.Notice.COLUMN_NAME_DESCRIPTION,des);
        values.put(UsersMaster.Notice.COLUMN_NAME_DATE,toDay);

        String selection = UsersMaster.Notice._ID + " LIKE ?";
        String[] selectionArgs = { ID };

        int count = db.update(
                UsersMaster.Notice.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        if(count == -1){
            return false;
        } else {
            return true;
        }
    }

    public int deleteNotice(String ID){
        SQLiteDatabase db = getWritableDatabase();
        String selection = UsersMaster.Notice._ID + " LIKE ?";

        String[] SelectionARGS =  {ID};
        return db.delete(UsersMaster.Notice.TABLE_NAME,selection,SelectionARGS);
    }


    public ArrayList readAllNotice(String key){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + UsersMaster.Notice.TABLE_NAME + " order by " + UsersMaster.Notice.COLUMN_NAME_DATE + " desc" ,null);

        ArrayList Title = new ArrayList<String>();
        ArrayList des  = new ArrayList<String>();
        ArrayList date = new ArrayList<String>();
        ArrayList id = new ArrayList<String>();

        while(cursor.moveToNext()){
            String ID = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Notice._ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Notice.COLUMN_NAME_TITLE));
            String Des = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Notice.COLUMN_NAME_DESCRIPTION));

            id.add(ID);
            Title.add(title);
            des.add(Des);
            //date.add("Date");
        }
        cursor.close();

        switch (key){
            case "ID" :
                return id;
            case "TITLE":
                return Title;
            case "DES":
                return des;
            case "DATE":
                return date;
            default:
                return null;
        }
    }

    public String getNoticeValue(String ID,String key){
        SQLiteDatabase db = getReadableDatabase();
        String title = "empty";
        String Des = "empty";

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                UsersMaster.Notice._ID,
                UsersMaster.Notice.COLUMN_NAME_TITLE,
                UsersMaster.Notice.COLUMN_NAME_DESCRIPTION
        };

// Filter results WHERE "title" = 'My Title'
        String selection = UsersMaster.Notice._ID + " = ?";
        String[] selectionArgs = { ID };

// How you want the results sorted in the resulting Cursor

        Cursor cursor = db.query(
                UsersMaster.Notice.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null              // The sort order
        );
        if(cursor.moveToFirst()){
            //int ID = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Notice._ID)));
            title = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Notice.COLUMN_NAME_TITLE));
            Des = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Notice.COLUMN_NAME_DESCRIPTION));
        }
        cursor.close();

        switch (key){
            case "TITLE":
                return title;
            case "DES":
                return Des;
            default:
                return null;
        }
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + UsersMaster.Notice.TABLE_NAME;

        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);

    }
}
