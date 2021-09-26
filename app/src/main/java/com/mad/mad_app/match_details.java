package com.mad.mad_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class match_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_details);

        //Set the Title of the Activity
        this.setTitle("Match Details");
    }
}