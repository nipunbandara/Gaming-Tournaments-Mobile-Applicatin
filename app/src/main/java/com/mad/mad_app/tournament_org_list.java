package com.mad.mad_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class tournament_org_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_org_list);


        this.setTitle("Tournaments");
    }

    public void add_tournament(View view){

        Intent intent = new Intent(this, tournament_create.class);
        startActivity(intent);

    }

    public void edit_tournament(View view){

        Intent intent = new Intent(this, tournament_update.class);
        startActivity(intent);

    }
}