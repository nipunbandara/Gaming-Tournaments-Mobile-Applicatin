package com.mad.mad_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class tournament_org_tournament_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_org_tournament_info);
    }

    public void org_brackets(View view){

        Intent intent = new Intent(this, match_org_list.class);
        startActivity(intent);

    }
}