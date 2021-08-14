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

    public void game_home_org(View view){

        Intent intent = new Intent(this, game_org_gamelist.class);
        startActivity(intent);
    }

    public void tournament_home_org(View view){

        Intent intent = new Intent(this, tournament_org_list.class);
        startActivity(intent);
    }

    public void organizer_home_org(View view){

        Intent intent = new Intent(this, organizer_organizer_info.class);
        startActivity(intent);
    }

    public void home_org(View view){

        Intent intent = new Intent(this, organizer_home.class);
        startActivity(intent);

    }
}