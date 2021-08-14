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

    public void login_player(View view){

        Intent intent = new Intent(this, login_player.class);
        startActivity(intent);

    }

    public void login_org(View view){

        Intent intent = new Intent(this, login_org.class);
        startActivity(intent);

    }
}