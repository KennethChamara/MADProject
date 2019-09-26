package marksDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static java.sql.Types.REAL;


public class marksdbhelper extends SQLiteOpenHelper {
    public static final String DATABASENAME="marks.db";


    public marksdbhelper(@Nullable Context context) {
        super(context, DATABASENAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase mksdb) {
       // mksdb.execSQL("CREATE TABLE  "+ examtype.EXAMTYPE_TABLE +"( " +examtype.EXAMTYPEID + "  " + " INTEGER PRIMARY KEY AUTOINCREMENT," +  " "  + examtype.EXAMVESION + " " + " TEXT)");
        mksdb.execSQL("CREATE TABLE  "+marks.MARKS_TABLE +"( " +marks.MARKSID + " " + "INTEGER PRIMARY KEY AUTOINCREMENT," + " "+marks.STUDENTID +  " "  +  " TEXT,"  +   " "  +marks.MARKS   +  " "  + " REAL, " + marks.MARKSCATEGORY + " " + "TEXT )");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
