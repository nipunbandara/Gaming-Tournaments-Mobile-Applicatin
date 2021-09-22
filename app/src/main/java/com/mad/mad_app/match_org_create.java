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

    private EditText id, mteamA, mteamB, mtime, mround;
//    private Spinner tselectgame;
    private Button registerBtn;
    private TextView registerQn;

    private FirebaseAuth mAuth;
    private ProgressDialog loader;
    private DatabaseReference userDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_org_create);

        mteamA = findViewById(R.id.mteamA);
        mteamB = findViewById(R.id.mteamB);
        mround = findViewById(R.id.mround);

        mtime = findViewById(R.id.mtime);

        registerBtn = findViewById(R.id.Btn_create);
        registerQn = findViewById(R.id.Btn_cancel);
        loader = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mteamAString = mteamA.getText().toString();
                String mteamBString = mteamB.getText().toString();
                String mroundString = mround.getText().toString();

                String mtimeString = mtime.getText().toString();


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

                else {

                    loader.setMessage("Creating Match...");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();

//                    FirebaseDatabase database = FirebaseDatabase.getInstance();
//                    DatabaseReference myRef = database.getReference("message");
//
//                    myRef.setValue("Hello, World!");

                    String currentUserId = mAuth.getCurrentUser().getUid();
                    userDatabaseRef = FirebaseDatabase.getInstance().getReference().child("matches");
                    //change child name to specific table name
                    String id  = userDatabaseRef.push().getKey();

                    HashMap userInfo = new HashMap();
                    userInfo.put("id", currentUserId);
                    userInfo.put("mteamA", mteamAString);
                    userInfo.put("mteamB", mteamBString);
                    userInfo.put("mround", mroundString);

                    userInfo.put("ttime", mtimeString);


                    userDatabaseRef.child(id).setValue(userInfo).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(match_org_create.this, "Match set Successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(match_org_create.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }

                            finish();
                            //loader.dismiss();
                        }
                    });

                    Intent intent = new Intent(match_org_create.this, organizer_home.class);
                    startActivity(intent);
                    finish();
                    loader.dismiss();

                }

            }
        });
    }
    }
