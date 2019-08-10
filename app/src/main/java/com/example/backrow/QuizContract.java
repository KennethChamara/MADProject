package com.example.backrow;

import android.provider.BaseColumns

public class QuizContract {

    private QuizContract(){}

    public  static class  QuestionsTable implements  BaseColumns {
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUM_QUESTION = "questions";
        public static final String COLUM_OPTION1 = "questions1";
        public static final String COLUM_OPTION2 = "questions2";
        public static final String COLUM_OPTION3 = "questions3";
        public static final String COLUM_ANSWER_NR = "answer_nr";
    }


}
