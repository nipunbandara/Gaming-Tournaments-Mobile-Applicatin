package com.mad.mad_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class team_managepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_managepage);


        this.setTitle("Manage Team");
    }

    public void user_team_edit(View view){

        Intent intent = new Intent(this, team_update.class);
        startActivity(intent);
    }
}