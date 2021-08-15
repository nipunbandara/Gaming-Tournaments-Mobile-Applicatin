package com.mad.mad_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class team_home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_home);


        this.setTitle("Team");
    }

    public void user_team_create(View view){

        Intent intent = new Intent(this, team_create.class);
        startActivity(intent);

    }
    public void user_team_managepage(View view){

        Intent intent = new Intent(this, team_managepage.class);
        startActivity(intent);

    }
}