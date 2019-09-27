package com.example.backrow;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

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
                    break;

                    case R.id.db2  :

                        startActivity(new Intent(getApplicationContext(), noticePanel.class));
                        break;

                    case R.id.db1  :
                        Toast.makeText(getApplicationContext(),"id is"+menuItem.getItemId(),Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(navgation.this, VideoActivity2.class));
                        break;

                    case R.id.db3  :
                       Toast.makeText(getApplicationContext(),"id is"+menuItem.getItemId(),Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(navgation.this, makslistNavigation.class));
                       break;

                    case R.id.db4  :
                        Toast.makeText(getApplicationContext(),"id is"+menuItem.getItemId(),Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(navgation.this, MainActivity.class));
                        break;

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
