package com.example.backrow;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME ="MyQuiz.db";
   private static final int DATABASE_VERSION =1;

   private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("db helper start","its ok oncreate");
        this.db =db;

        final String SQL_CREATE_QUESION_TABLE =" CREATE TABLE "+
                QuizContract.QuestionsTable.TABLE_NAME+" ( "+
                QuizContract.QuestionsTable._ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                QuizContract.QuestionsTable.COLUM_QUESTION+ " TEXT, "+
                QuizContract.QuestionsTable.COLUM_OPTION1+ " TEXT, "+
                QuizContract.QuestionsTable.COLUM_OPTION2+ " TEXT, "+
                QuizContract.QuestionsTable.COLUM_OPTION3+ " TEXT, "+
                QuizContract.QuestionsTable.COLUM_ANSWER_NR+ " INTEGER "+" ) ";





        db.execSQL(SQL_CREATE_QUESION_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS "+ QuizContract.QuestionsTable.TABLE_NAME);
            onCreate(db);

    }

    private void fillQuestionsTable(){

            Question q1 = new Question("A is correct ","A","B","C",1);
        Question q2 = new Question("B is correct ","A","B","C",2);
        Question q3 = new Question("C is correct ","A","B","C",3);
        Question q4 = new Question("A is correct ","A","B","C",1);
        Question q5 = new Question("B is correct ","A","B","C",2);
        addQuestion(q1);
        addQuestion(q2);
        addQuestion(q3);
        addQuestion(q4);
        addQuestion(q5);
    }

    public List<Question> getAllQuestions(){

        List <Question> questionList = new ArrayList<>();

        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM "+ QuizContract.QuestionsTable.TABLE_NAME,null);

        if(c.moveToFirst()){
        do{

            Question question = new Question();
            question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUM_QUESTION)));
            question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUM_OPTION1)));
            question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUM_OPTION2)));
            question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUM_OPTION3)));
            question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUM_ANSWER_NR)));
            questionList.add(question);


        }while(c.moveToNext());
        }
        c.close();
        return  questionList;
    }

    private void addQuestion(Question question){

        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionsTable.COLUM_QUESTION, question.getQuestion());
        cv.put(QuizContract.QuestionsTable.COLUM_OPTION1,question.getOption1());
        cv.put(QuizContract.QuestionsTable.COLUM_OPTION2,question.getOption2());
        cv.put(QuizContract.QuestionsTable.COLUM_OPTION3,question.getOption3());
        cv.put(QuizContract.QuestionsTable.COLUM_ANSWER_NR,question.getAnswerNr());
        db.insert(QuizContract.QuestionsTable.TABLE_NAME,null,cv);


    }


}