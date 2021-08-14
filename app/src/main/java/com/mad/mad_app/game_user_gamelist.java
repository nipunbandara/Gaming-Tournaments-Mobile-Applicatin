package com.mad.mad_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class game_user_gamelist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_user_gamelist );
    }

    public void tournament_home_user(View view){

        Intent intent = new Intent(this, tournament_home.class);
        startActivity(intent);

    }

    public void team_home_user(View view){

        Intent intent = new Intent(this, team_home.class);
        startActivity(intent);

    }

    public void game_home_user(View view){

        Intent intent = new Intent(this, activity_game_home.class);
        startActivity(intent);

    }

    public void user_create_user(View view){

        Intent intent = new Intent(this, player_playerinfo.class);
        startActivity(intent);

    }


}