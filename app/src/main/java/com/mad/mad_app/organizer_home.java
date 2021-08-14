package com.mad.mad_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class organizer_home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizer_home);
    }

    public void game_create_org(View view){

        Intent intent = new Intent(this, team_create.class);
        startActivity(intent);
    }

    public void tournament_create_org(View view){

        Intent intent = new Intent(this, team_create.class);
        startActivity(intent);
    }

    public void organizer_create_org(View view){

        Intent intent = new Intent(this, team_create.class);
        startActivity(intent);
    }
}