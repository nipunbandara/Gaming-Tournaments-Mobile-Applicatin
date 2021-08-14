package com.mad.mad_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class match_org_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_org_list);
    }

    public void match_org_create(View view){

        Intent intent = new Intent(this, match_org_create.class);
        startActivity(intent);

    }
    public void match_org_update(View view){

        Intent intent = new Intent(this, match_org_update.class);
        startActivity(intent);

    }

}