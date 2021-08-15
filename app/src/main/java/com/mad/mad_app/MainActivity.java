package com.mad.mad_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.setTitle("Home");
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

        Intent intent = new Intent(this, game_home.class);
        startActivity(intent);

    }

    public void user_home_user(View view){

        Intent intent = new Intent(this, player_stats.class);
        startActivity(intent);

    }

    public void home_user(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}