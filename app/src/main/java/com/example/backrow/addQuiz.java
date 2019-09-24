package com.example.backrow;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class addQuiz extends AppCompatActivity {

    EditText txt_question,txt_answerA,txt_answerB,txt_answerC,txt_answer,txt_id;
    Button btn_add,btn_show,btn_update,btn_delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quiz);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txt_question=findViewById(R.id.txtQuestion);
        txt_answerA=findViewById(R.id.txtAnsweA);
        txt_answerB=findViewById(R.id.txtAnsweB);
        txt_answerC=findViewById(R.id.txtAnsweC);
        txt_answer=findViewById(R.id.txtCorrectAnswer);
        txt_id = findViewById(R.id.txtId);
        btn_add=findViewById(R.id.btnAdd);
        btn_show=findViewById(R.id.btnshow);
        btn_delete=findViewById(R.id.btnDelete);
        btn_update=findViewById(R.id.btnUpdate);

        Intent intent = getIntent();
        if(intent.getStringExtra("qid")!=null){

            Question question = new Question();

            QuizDbHelper quizDbHelper = new QuizDbHelper(getApplicationContext());
            question=quizDbHelper.getQuestion(intent.getStringExtra("qid"));
            txt_id.setText(question.getId());
            txt_question.setText(question.getQuestion());
            txt_answerA.setText(question.getOption1());
            txt_answerB.setText(question.getOption2());
            txt_answerC.setText(question.getOption3());
            txt_answer.setText(Integer.toString(question.getAnswerNr()));
            Toast.makeText(getApplicationContext(),"showing Success",Toast.LENGTH_SHORT).show();
        }


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question question = new Question();
                question.setQuestion(txt_question.getText().toString());
                question.setOption1(txt_answerA.getText().toString());
                question.setOption2(txt_answerB.getText().toString());
                question.setOption3(txt_answerC.getText().toString());
                question.setOption3(txt_answerC.getText().toString());
                question.setAnswerNr(Integer.parseInt(txt_answer.getText().toString()));


                QuizDbHelper quizDbHelper = new QuizDbHelper(getApplicationContext());
                quizDbHelper.addQuestion(question);
                Toast.makeText(getApplicationContext(),"Question saved",Toast.LENGTH_SHORT).show();

            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question question = new Question();
                question.setId(txt_id.getText().toString());
                question.setQuestion(txt_question.getText().toString());
                question.setOption1(txt_answerA.getText().toString());
                question.setOption2(txt_answerB.getText().toString());
                question.setOption3(txt_answerC.getText().toString());
                question.setOption3(txt_answerC.getText().toString());
                question.setAnswerNr(Integer.parseInt(txt_answer.getText().toString()));


                QuizDbHelper quizDbHelper = new QuizDbHelper(getApplicationContext());
                quizDbHelper.update(question);
                Toast.makeText(getApplicationContext(),"apdate Success",Toast.LENGTH_SHORT).show();

            }
        });

        btn_show.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongViewCast")
            @Override
            public void onClick(View v) {

                Question question = new Question();

                QuizDbHelper quizDbHelper = new QuizDbHelper(getApplicationContext());
                question=quizDbHelper.getQuestion(txt_id.getText().toString());

                txt_id.setText(question.getId());
                txt_question.setText(question.getQuestion());
                txt_answerA.setText(question.getOption1());
                txt_answerB.setText(question.getOption2());
                txt_answerC.setText(question.getOption3());
                txt_answer.setText(Integer.toString(question.getAnswerNr()));
                Toast.makeText(getApplicationContext(),"showing Success",Toast.LENGTH_SHORT);

            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongViewCast")
            @Override
            public void onClick(View v) {


                QuizDbHelper quizDbHelper = new QuizDbHelper(getApplicationContext());
                quizDbHelper.deleteQuesion(txt_id.getText().toString());
                Toast.makeText(getApplicationContext(),"Delete Success",Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
