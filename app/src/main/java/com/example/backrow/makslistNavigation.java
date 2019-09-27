package com.example.backrow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class makslistNavigation extends AppCompatActivity {

    Button Final,Mid,Spot,Asgment1,Asgment2,Asgment3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makslist_navigation);

        Final = findViewById(R.id.Final);
        Mid = findViewById(R.id.Mid);
        Spot = findViewById(R.id.Spot);
        Asgment1 = findViewById(R.id.Assignment1);
        Asgment2 = findViewById(R.id.Assignment2);
        Asgment3 = findViewById(R.id.Assignment3);

        Final.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(makslistNavigation.this,maksListUserNav.class );
                intent.putExtra("CATEGORY",Final.getText().toString());
                startActivity(intent);
            }
        });
        Mid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(makslistNavigation.this,maksListUserNav.class );
                intent.putExtra("CATEGORY",Mid.getText().toString());
                startActivity(intent);
            }
        });
        Spot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(makslistNavigation.this,maksListUserNav.class );
                intent.putExtra("CATEGORY",Spot.getText().toString());
                startActivity(intent);
            }
        });
        Asgment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(makslistNavigation.this,maksListUserNav.class );
                intent.putExtra("CATEGORY",Asgment1.getText().toString());
                startActivity(intent);
            }
        });
        Asgment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(makslistNavigation.this,maksListUserNav.class );
                intent.putExtra("CATEGORY",Asgment2.getText().toString());
                startActivity(intent);
            }
        });
        Asgment3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(makslistNavigation.this,maksListUserNav.class );
                intent.putExtra("CATEGORY",Asgment3.getText().toString());
                startActivity(intent);
            }
        });
    }
}
