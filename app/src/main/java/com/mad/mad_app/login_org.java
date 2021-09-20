package com.mad.mad_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class login_org extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_org);


        this.setTitle("Login - Organization");
    }

    public void organizer_home(View view){

        Intent intent = new Intent(this, organizer_home.class);
        startActivity(intent);

    }

    public void org_signup(View view){
        Intent intent = new Intent(this, signup_organizer.class);
        startActivity(intent);
    }
}