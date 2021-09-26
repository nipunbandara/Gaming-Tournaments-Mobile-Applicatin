package com.mad.mad_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class tournament_join_user extends AppCompatActivity {

    private String tid;
    private EditText gamename, ttype;
    private Spinner tselectTeam;
    private Button registerBtn,registerQn;

    private FirebaseAuth mAuth;
    private ProgressDialog loader;
    private DatabaseReference userDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_join_user);

        tselectTeam = findViewById(R.id.spinner2);
        registerBtn = findViewById(R.id.btn_create2);
        registerQn = findViewById(R.id.j_btn_cancel);
        loader = new ProgressDialog(this);

        Intent myIntent = getIntent();
        tid = myIntent.getStringExtra("MAIN_EXTRA");

        mAuth = FirebaseAuth.getInstance();


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tselectedteamString = tselectTeam.getSelectedItem().toString();

                if (TextUtils.isEmpty(tselectedteamString)) {
                    Toast.makeText(tournament_join_user.this, "Select a Game", Toast.LENGTH_SHORT).show();
                }
                else {
                    loader.setMessage("Registering you...");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();
                    //Database initialization, Getting reference and setting child path
                    String currentUserId = mAuth.getCurrentUser().getUid();
                    userDatabaseRef = FirebaseDatabase.getInstance().getReference().child("players-joined-tournaments").child(tid);

                    String id  = userDatabaseRef.push().getKey();
                    //using HashMap Data structure to add data to database
                    HashMap userInfo = new HashMap();
                    userInfo.put("id", id);//tournament id
                    userInfo.put("tid", tid);//tournament id
                    userInfo.put("pid", currentUserId);//organizer id
                    userInfo.put("team", tselectedteamString);

                    //allocating hashmap to the database child
                    userDatabaseRef.child(id).setValue(userInfo).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(tournament_join_user.this, "Data set Successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(tournament_join_user.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }
                            finish();
                            //loader.dismiss();
                        }
                    });

                    Intent intent = new Intent(tournament_join_user.this, tournament_home.class);
                    startActivity(intent);
                    finish();
                    loader.dismiss();

                }

            }
        });
    }

}