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
                UsersMaster.Notice.COLUMN_NAME_DATE + " TEXT," +
                UsersMaster.Notice.COLUMN_NAME_IMAGE + " blob)";
        sqLiteDatabase.execSQL(SQL_CREATE_ENTERIS);
    }

    public boolean addnotice(String title,String des,byte[] img){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/M/yyyy hh:mm");
        String toDay = formatter.format(date);

        values.put(UsersMaster.Notice.COLUMN_NAME_TITLE,title);
        values.put(UsersMaster.Notice.COLUMN_NAME_DESCRIPTION,des);
        values.put(UsersMaster.Notice.COLUMN_NAME_DATE,toDay);
        values.put(UsersMaster.Notice.COLUMN_NAME_IMAGE,img);

        long newRowId = db.insert(UsersMaster.Notice.TABLE_NAME,null,values);

        if(newRowId == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean updatenotice(String title,String des,String ID,byte[] imageByte){
        SQLiteDatabase db = getWritableDatabase();

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String toDay = formatter.format(date);

        ContentValues values = new ContentValues();

        values.put(UsersMaster.Notice.COLUMN_NAME_TITLE,title);
        values.put(UsersMaster.Notice.COLUMN_NAME_DESCRIPTION,des);
        values.put(UsersMaster.Notice.COLUMN_NAME_DATE,toDay);
        values.put(UsersMaster.Notice.COLUMN_NAME_IMAGE,imageByte);

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


    public Cursor readAllNotice(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor1 = db.rawQuery("select * from " + UsersMaster.Notice.TABLE_NAME + " order by " + UsersMaster.Notice.COLUMN_NAME_DATE + " desc" ,null);
        return cursor1;
    }

    public Cursor getNoticeValue(String ID){
        SQLiteDatabase db = getReadableDatabase();
        String title = "empty";
        String Des = "empty";

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                UsersMaster.Notice._ID,
                UsersMaster.Notice.COLUMN_NAME_TITLE,
                UsersMaster.Notice.COLUMN_NAME_DESCRIPTION,
                UsersMaster.Notice.COLUMN_NAME_DATE,
                UsersMaster.Notice.COLUMN_NAME_IMAGE
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

        return cursor;
    }




    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + UsersMaster.Notice.TABLE_NAME;

        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);

    }
}
