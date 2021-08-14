package com.mad.mad_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class activity_game_home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_home);
    }
    public void view_ml_game(View view){

        Intent intent = new Intent(this, game_display_user.class);
        startActivity(intent);

    }
}