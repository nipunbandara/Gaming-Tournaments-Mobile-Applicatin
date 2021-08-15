package com.mad.mad_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class player_stats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_stats);


        this.setTitle("Player Stat");
    }

    public void my_games(View view){

        Intent intent = new Intent(this, game_user_gamelist.class);
        startActivity(intent);

    }
    public void my_tournaments(View view){

        Intent intent = new Intent(this, game_user_gamelist.class);
        startActivity(intent);

    }
}