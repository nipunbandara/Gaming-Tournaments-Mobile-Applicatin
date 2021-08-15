package com.mad.mad_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class tournament_home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_home);
        Intent intent = getIntent();


        this.setTitle("Tournaments");
    }

    public void tournament_user_sel(View view){

        Intent intent = new Intent(this, tournament_user_selected_t.class);
        startActivity(intent);

    }

    public void tournament_reg(View view){

        Intent intent = new Intent(this, tournament_join_user.class);
        startActivity(intent);

    }

    public void home(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}