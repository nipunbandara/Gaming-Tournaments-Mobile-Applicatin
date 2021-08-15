package com.mad.mad_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class team_create extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_create);
        Intent intent = getIntent();


        this.setTitle("Create Team");
    }
    public void tournament_home(View view){

        Intent intent = new Intent(this, tournament_home.class);
        startActivity(intent);

    }

    public void home(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}