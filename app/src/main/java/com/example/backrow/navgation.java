package com.example.backrow;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;


public class navgation extends AppCompatActivity {
    private DrawerLayout adrawerLayout;
    private ActionBarDrawerToggle atoggle;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navgation);
        adrawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        navigationView= (NavigationView) findViewById(R.id.nav_view) ;
        atoggle = new ActionBarDrawerToggle(this, adrawerLayout, R.string.open, R.string.close);
        adrawerLayout.addDrawerListener(atoggle);
        atoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

            @Override

            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //Check to see which item was being clicked and perform appropriate action


                switch (menuItem.getItemId()) {

                    case  R.id.db:
                    startActivity(new Intent(navgation.this, studentquiz.class));
                    case R.id.db1  :
                        startActivity(new Intent(navgation.this, noticePanel.class));

                }

                    adrawerLayout.closeDrawers();


                    return true;}

        });



    }


    public boolean onOptionsItemSelected(MenuItem item) {


        if (atoggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }



}
