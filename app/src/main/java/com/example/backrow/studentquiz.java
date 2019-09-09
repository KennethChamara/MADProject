package com.example.backrow;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class studentquiz extends Fragment {

    private static final int REQUEST_CODE_QUIZ = 1 ;

    public static final String SHARED_PREPS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";

    private TextView textViewHighscore;

    private int highscore;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_studentquiz, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
      super.onActivityCreated(savedInstanceState);


        textViewHighscore = getView().findViewById(R.id.text_view_highscore);
        loadHighscore();

        Button buttonStartQuiz = getView().findViewById(R.id.button_start_quiz);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener(){

            public  void onClick(View v){

               startQuiz();

            }

        });

    }

    private void startQuiz(){
        Intent intent = new Intent(getActivity(),myquiz.class);
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
        SharedPreferences prefs = this.getActivity().getSharedPreferences(SHARED_PREPS,MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE,0);
        textViewHighscore.setText("Highscore: "+highscore);
    }

    private void updateHighscore(int highscoreNew){
        highscore = highscoreNew;

        textViewHighscore.setText("Highscore: "+highscore);

        SharedPreferences prefs = this.getActivity().getSharedPreferences(SHARED_PREPS,MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE,highscore);
        editor.apply();
    }

}
