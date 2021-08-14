package com.mad.mad_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class game_org_gamelist extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_org_gamelist);
    }
    public void create_new_game(View view){

        Intent intent = new Intent(this, game_create.class);
        startActivity(intent);


    }
    public void update_new_game(View view){

        Intent intent = new Intent(this, game_update.class);
        startActivity(intent);

    }
}