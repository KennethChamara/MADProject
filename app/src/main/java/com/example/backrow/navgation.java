package com.example.backrow;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;


public class navgation extends AppCompatActivity {
    private DrawerLayout adrawerLayout;
    private ActionBarDrawerToggle atoggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navgation);
        adrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        atoggle = new ActionBarDrawerToggle(this,adrawerLayout,R.string.open,R.string.close);
        adrawerLayout.addDrawerListener(atoggle);
        atoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(atoggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
