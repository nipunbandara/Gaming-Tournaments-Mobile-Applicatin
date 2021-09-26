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


public class game_create extends AppCompatActivity {
    private EditText gname, gregion, gdescription, gminteams, gmaxteams;

    private Button registerBtn;
    private TextView registerQn;

    private FirebaseAuth mAuth;
    private ProgressDialog loader;
    private DatabaseReference userDatabaseRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_create);



        this.setTitle("Create Game");

        gname = findViewById(R.id.gnamekey);
        gregion = findViewById(R.id.update_region);
        gdescription = findViewById(R.id.update_des);
        gminteams = findViewById(R.id.update_min);
        gmaxteams = findViewById(R.id.update_max);

        registerBtn = findViewById(R.id.button2);

        loader = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gnameString = gname.getText().toString();
                String gregionString =gregion.getText().toString();
                String gdescriptionString =gdescription.getText().toString();
                String gminteamsString =gminteams.getText().toString();
                String  gmaxteamsString = gmaxteams.getText().toString();


                if (TextUtils.isEmpty(gnameString)) {
                    gname.setError("gname is required");
                }
                if (TextUtils.isEmpty(gregionString)) {
                    gregion.setError("Password is required");
                }
                if (TextUtils.isEmpty(gdescriptionString)) {
                    gdescription.setError("User Name is required");
                }
                if (TextUtils.isEmpty( gminteamsString)) {
                    gminteams.setError("Full Name is required");
                }
                if (TextUtils.isEmpty(gmaxteamsString)) {
                    gmaxteams.setError("Full Name is required");
                }

                else {

                    loader.setMessage("Registering you...");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();

                    String currentUserId = mAuth.getCurrentUser().getUid();
                    //database initialization
                    userDatabaseRef = FirebaseDatabase.getInstance().getReference()
                            .child("games");

                    String id  = userDatabaseRef.push().getKey();

                    //using hashmaps to add details to the database
                    HashMap userInfo = new HashMap();
                    userInfo.put("id", id);
                    userInfo.put("oid", currentUserId);
                    userInfo.put("gname", gregionString);
                    userInfo.put("gregion", gdescriptionString);
                    userInfo.put("gdescription",gdescriptionString);
                    userInfo.put("gminteams", gminteamsString);
                    userInfo.put("gmaxteams", gmaxteamsString);

                    //allocating data structure to the database
                    userDatabaseRef.child(id).setValue(userInfo).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(game_create.this, "Data set Successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(game_create.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }

                            finish();
                            //loader.dismiss();
                        }
                    });

                    Intent intent = new Intent(game_create.this, organizer_home.class);
                    startActivity(intent);
                    finish();
                    loader.dismiss();

                }

            }
        });
    }

}