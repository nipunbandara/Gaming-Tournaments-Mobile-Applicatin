package com.mad.mad_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class player_playerinfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_playerinfo);
    }
    public void subs_game_list(View view){

        Intent intent = new Intent(this, game_user_gamelist.class);
        startActivity(intent);

    }
}