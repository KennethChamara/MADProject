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
                QuizContract.QuestionsTable._ID+ " varchar(10) PRIMARY KEY, "+
                QuizContract.QuestionsTable.COLUM_QUESTION+ " TEXT, "+
                QuizContract.QuestionsTable.COLUM_OPTION1+ " TEXT, "+
                QuizContract.QuestionsTable.COLUM_OPTION2+ " TEXT, "+
                QuizContract.QuestionsTable.COLUM_OPTION3+ " TEXT, "+
                QuizContract.QuestionsTable.COLUM_ANSWER_NR+ " INTEGER "+" ); ";


        System.out.println(SQL_CREATE_QUESION_TABLE);

        db.execSQL(SQL_CREATE_QUESION_TABLE);


        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS "+ QuizContract.QuestionsTable.TABLE_NAME);
            onCreate(db);

    }

    private void fillQuestionsTable(){
        Log.i("db helper start","its ok 222222222222222222222");
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
    public Question getQuestion(String id){


        Cursor c = db.rawQuery("SELECT * FROM "+ QuizContract.QuestionsTable.TABLE_NAME+" WHERE _id ='"+id+"'",null);
        System.out.println("SELECT * FROM "+ QuizContract.QuestionsTable.TABLE_NAME+" where _id="+id);
        Question question = new Question();
        if(c.moveToFirst()){



                question.setId(c.getString(c.getColumnIndex(QuizContract.QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUM_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUM_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUM_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUM_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUM_ANSWER_NR)));

        }
        c.close();
        return  question;
    }
    public List<Question> getAllQuestions(){

        List <Question> questionList = new ArrayList<>();

      // db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM "+ QuizContract.QuestionsTable.TABLE_NAME,null);

        if(c.moveToFirst()){
        do{

            Question question = new Question();
            question.setId(c.getString(c.getColumnIndex(QuizContract.QuestionsTable._ID)));
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




    private String keyGen(List list){
            if(!list.isEmpty()) {
                Question question = (Question) list.get(list.size() - 1);
                String key = question.getId();

                if (key != null) {

                    String[] parts = key.split("(Q)");
                    key = parts[1];

                    int a = Integer.parseInt(key);
                    System.out.println("Q" + (a + 1));
                    return "Q" + (a + 1);


                } else {

                    return "Q20" + 1;
                }
            }else {
                return "Q20" + 1;
            }
    }

    public void addQuestion(Question question){


            question.setId(keyGen(getAllQuestions()));

            ContentValues cv = new ContentValues();
            cv.put(QuizContract.QuestionsTable._ID, question.getId());
            cv.put(QuizContract.QuestionsTable.COLUM_QUESTION, question.getQuestion());
            cv.put(QuizContract.QuestionsTable.COLUM_OPTION1, question.getOption1());
            cv.put(QuizContract.QuestionsTable.COLUM_OPTION2, question.getOption2());
            cv.put(QuizContract.QuestionsTable.COLUM_OPTION3, question.getOption3());
            cv.put(QuizContract.QuestionsTable.COLUM_ANSWER_NR, question.getAnswerNr());
            db.insert(QuizContract.QuestionsTable.TABLE_NAME, null, cv);


    }

    public void update(Question question){

        ContentValues cv = new ContentValues();

        cv.put(QuizContract.QuestionsTable.COLUM_QUESTION, question.getQuestion());
        cv.put(QuizContract.QuestionsTable.COLUM_OPTION1,question.getOption1());
        cv.put(QuizContract.QuestionsTable.COLUM_OPTION2,question.getOption2());
        cv.put(QuizContract.QuestionsTable.COLUM_OPTION3,question.getOption3());
        cv.put(QuizContract.QuestionsTable.COLUM_ANSWER_NR,question.getAnswerNr());
        db.update(QuizContract.QuestionsTable.TABLE_NAME,cv,"_id='"+question.getId()+"'",null);

    }
    public boolean deleteQuesion(String id){
        return db.delete(QuizContract.QuestionsTable.TABLE_NAME,"_id='"+id+"'",null) >0;
    }

}