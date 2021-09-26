package com.mad.mad_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class tournament_org_tournament_info extends AppCompatActivity {

    private String mtid;

//    private TextView ttid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_org_tournament_info);

//        ttid = findViewById(R.id.cr_tournamet_topic3);
//        Intent myIntent = getIntent();
//        tid = myIntent.getStringExtra("TID");
//        ttid.setText(tid);

    }

    public void org_brackets(View view){

        Intent myIntent = getIntent();
        mtid = myIntent.getStringExtra("TID");

        Intent intent = new Intent(this, match_org_list.class);
        intent.putExtra("TIDtomatch", mtid);
        startActivity(intent);

    }
}