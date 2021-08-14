package com.mad.mad_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void user_home(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void organizer_home(View view){

        Intent intent = new Intent(this, organizer_home.class);
        startActivity(intent);

    }
}