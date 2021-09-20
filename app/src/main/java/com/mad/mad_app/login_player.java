package com.mad.mad_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class login_player extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_player);


        this.setTitle("Login Player");
    }

    public void user_home(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
    public void plyr_signup(View view){
        Intent intent = new Intent(this, signup_player.class);
        startActivity(intent);
    }
}