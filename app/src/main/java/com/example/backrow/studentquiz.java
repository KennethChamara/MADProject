package com.example.backrow;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class studentquiz extends AppCompatActivity {

    private static final int REQUEST_CODE_QUIZ = 1 ;

    public static final String SHARED_PREPS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";

    private TextView textViewHighscore;

    private int highscore;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_studentquiz);




        textViewHighscore = findViewById(R.id.text_view_highscore);
        loadHighscore();

        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener(){

            public  void onClick(View v){

               startQuiz();

            }

        });

    }

    private void startQuiz(){
        Intent intent = new Intent(getApplicationContext(),myquiz.class);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_QUIZ){
            if(resultCode == AppCompatActivity.RESULT_OK){
                int score = data.getIntExtra(myquiz.EXTRA_SCORE,0);
                if(score>highscore){
                    updateHighscore(score);
                }

            }
        }
    }

    private void loadHighscore(){
        SharedPreferences prefs = this.getSharedPreferences(SHARED_PREPS,MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE,0);
        textViewHighscore.setText("Highscore: "+highscore);
    }

    private void updateHighscore(int highscoreNew){
        highscore = highscoreNew;

        textViewHighscore.setText("Highscore: "+highscore);

        SharedPreferences prefs = this.getSharedPreferences(SHARED_PREPS,MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE,highscore);
        editor.apply();
    }

}
