package com.mad.mad_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class match_org_update extends AppCompatActivity {

    //Declare Variables
    private String mid;

    private EditText mUround, mUteamA, mUteamB;
    private Button Btn_Mupdate, Btn_MUcancel;
    private String mUroundString, mUteamAString, mUteamBString;


    private FirebaseAuth mAuth;

    private DatabaseReference matchRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_org_update);

        //Initialize Update and Cancel Buttons
        Btn_Mupdate = findViewById(R.id.Btn_Mupdate);
        Btn_MUcancel = findViewById(R.id.Btn_MUcancel);

        //Explicit Intent to get the match ID
        Intent myIntent = getIntent();
        mid = myIntent.getStringExtra("mid");

        //Initialize Firebase
        mAuth = FirebaseAuth.getInstance();
        matchRef = FirebaseDatabase.getInstance().getReference().child("matches").child(mid);

        matchRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    //Create Instance of the Match class and fetch data
                    Match match = task.getResult().getValue(Match.class);

                    //Initialize edittext fields
                    mUround = findViewById(R.id.mUround);
                    mUteamA = findViewById(R.id.mUteamA);
                    mUteamB = findViewById(R.id.mUteamB);

                    //Initialize data in database to the edit text fields
                    mUround.setText(match.getMround());
                    mUteamA.setText(match.getMteamA());
                    mUteamB.setText(match.getMteamB());

                }
            }
        });

        Btn_Mupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Match m = new Match();

                //Get User entered data
                mUround = findViewById(R.id.mUround);
                mUteamA = findViewById(R.id.mUteamA);
                mUteamB = findViewById(R.id.mUteamB);

                //get user input data to String Variables
                mUroundString = mUround.getText().toString();
                mUteamAString = mUteamA.getText().toString();
                mUteamBString = mUteamB.getText().toString();

                //HashMap to initialize data
                HashMap userInfo = new HashMap();
                userInfo.put("mround", mUroundString);
                userInfo.put("mteamA", mUteamAString);
                userInfo.put("mteamB", mUteamBString);

                matchRef.updateChildren(userInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            //Toast if match details update successful
                            Toast.makeText(match_org_update.this, "Updated successfully", Toast.LENGTH_SHORT).show();

                            //Intent to redirect to the match_org_list page
                            Intent intent = new Intent(match_org_update.this, match_org_list.class);
                            startActivity(intent);
                            finish();
//                            loader.dismiss();

                        }else {
                            //Toast if match details update unsuccessful
                            Toast.makeText(match_org_update.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }

                        finish();
                    }
                });
            }
        });
    }
}