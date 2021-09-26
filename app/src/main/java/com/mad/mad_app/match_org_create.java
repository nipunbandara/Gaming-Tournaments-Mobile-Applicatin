package com.mad.mad_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class match_org_create extends AppCompatActivity {

    //Declare Variables

    private EditText id, mteamA, mteamB, mtime, mround;

    private Button registerBtn;
    private TextView registerQn, test;

    private TextView t_round;

    private FirebaseAuth mAuth;

   // private ProgressDialog loader;
    private DatabaseReference matchDatabaseRef;

    //Declare Variable to get the tournament ID
    private String mtid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_org_create);

        //Get User input data to the variables
        mteamA = findViewById(R.id.mUteamA);
        mteamB = findViewById(R.id.mUteamB);
        mround = findViewById(R.id.mUround);
        mtime = findViewById(R.id.mUtime);

        //Get user response to the buttons
        registerBtn = findViewById(R.id.Btn_Mupdate);
        registerQn = findViewById(R.id.Btn_MUcancel);

        //declare Firebase variables
        mAuth = FirebaseAuth.getInstance();

//        //Get Explicit Intent from match_org_list Activity
//        Intent myIntent = getIntent();
//        mtid = myIntent.getStringExtra("tid");

//        test = findViewById(R.id.t_round);
//        test.setText(mtid);

        //Method to be called on RegisterBtn click
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Store user input in String Variables
                String mteamAString = mteamA.getText().toString();
                String mteamBString = mteamB.getText().toString();
                String mroundString = mround.getText().toString();
                String mtimeString = mtime.getText().toString();

                //Validate User Input
                if (TextUtils.isEmpty(mteamAString)) {
                    mteamA.setError("Team A is required");
                }
                if (TextUtils.isEmpty(mteamBString)) {
                    mteamB.setError("Team B is required");
                }
                if (TextUtils.isEmpty(mroundString)) {
                    mround.setError("Round is required");
                }

                if (TextUtils.isEmpty(mtimeString)) {
                    mtime.setError("Time is requried");
                }
                //If Validation Successful
                else {

                    //Get Explicit Intent from match_org_list Activity
                    Intent myIntent = getIntent();
                    mtid = myIntent.getStringExtra("tid");

                    //Reference match Database
                    String currentUserId = mAuth.getCurrentUser().getUid();
                    matchDatabaseRef = FirebaseDatabase.getInstance().getReference().child("matches").child(mtid);

                    //Match unique key generate
                    String id  = matchDatabaseRef.push().getKey();

                    //Store User input data in a hashmap
                    HashMap userInfo = new HashMap();
                    userInfo.put("oid", currentUserId);
                    userInfo.put("mid", id);
                    userInfo.put("mtid", mtid);
                    userInfo.put("mteamA", mteamAString);
                    userInfo.put("mteamB", mteamBString);
                    userInfo.put("mround", mroundString);
                    userInfo.put("ttime", mtimeString);

                    //Send to Database to create the entry.
                    matchDatabaseRef.child(id).setValue(userInfo).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (task.isSuccessful()) {
                                //Display match entry Successful Toast
                                Toast.makeText(match_org_create.this, "Match created Successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                //Display match entry Successful Toast
                                Toast.makeText(match_org_create.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }

                            finish();
                        }
                    });

                    //Intent to redirect the user to match_org_create irrespective of the result of the match creation
                    Intent intent = new Intent(match_org_create.this, match_org_list.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
