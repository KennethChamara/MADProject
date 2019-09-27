package marksDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import static java.sql.Types.REAL;


public class marksdbhelper extends SQLiteOpenHelper {
    public static final String DATABASENAME="marks.db";


    public marksdbhelper(@Nullable Context context) {
        super(context, DATABASENAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase mksdb) {
       // mksdb.execSQL("CREATE TABLE  "+ examtype.EXAMTYPE_TABLE +"( " +examtype.EXAMTYPEID + " INTEGER PRIMARY KEY AUTOINCREMENT," examtype.EXAMVESION + " " + " TEXT)");

        mksdb.execSQL("CREATE TABLE  " + marks.MARKS_TABLE +"( " +
                marks.MARKSID  + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                marks.STUDENTID +  " TEXT,"  +
                marks.MARKS   + " REAL," +
                marks.MARKSCATEGORY + " TEXT )"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public long addmarks(String StudentId,String examcategory,double exammarks){
        SQLiteDatabase mks=getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(marks.STUDENTID,StudentId);
        values.put(marks.MARKSCATEGORY,examcategory);
        values.put(marks.MARKS,exammarks);

        long newRowId=mks.insert(marks.MARKS_TABLE,null,values);

        return newRowId;
    }
    public Cursor readAllmarkByCategory(String category)
    {
        SQLiteDatabase mks=getReadableDatabase();
        String[] projection={
                marks.MARKSID,
                marks.STUDENTID,
                marks.MARKS,
                marks.MARKSCATEGORY
        };
        String selection = marks.MARKSCATEGORY + " = ?";
        String[] selectionArgs = { category };

        Cursor cus=mks.query(
                marks.MARKS_TABLE,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

            return  cus;

    }

    public int updateMarks(String sID,double mark,String markID){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(marks.STUDENTID,sID);
        values.put(marks.MARKS,mark);

        String selection = marks.MARKSID + " LIKE ?";
        String[] selectionArgs = { markID };

        int count = db.update(
                marks.MARKS_TABLE,
                values,
                selection,
                selectionArgs);

        return count;

    }

    public int deleteNotice(String ID){
        SQLiteDatabase db = getWritableDatabase();
        String selection =  marks.MARKSID + " LIKE ?";

        String[] SelectionARGS =  {ID};
        return db.delete(marks.MARKS_TABLE,selection,SelectionARGS);
    }



}
