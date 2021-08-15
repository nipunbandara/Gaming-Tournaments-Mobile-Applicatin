package com.mad.mad_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class game_display_user extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_display_user);

        this.setTitle("Games");
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

        Intent intent = new Intent(this, game_user_gamelist.class);
        startActivity(intent);

    }

    public void user_create_user(View view){

        Intent intent = new Intent(this, player_playerinfo.class);
        startActivity(intent);

    }


}