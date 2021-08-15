package com.mad.mad_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class tournament_user_selected_t extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_user_selected_t);
        Intent intent = getIntent();


        this.setTitle("Kill Confirmed 10 V 10 #3");
    }
    public void home(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void match_player_list(View view){

        Intent intent = new Intent(this, match_player_list.class);
        startActivity(intent);

    }

}