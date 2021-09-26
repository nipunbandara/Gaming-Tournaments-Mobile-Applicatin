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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class tournament_create extends AppCompatActivity {

    private EditText tname, ttype, tmaxteams, tdate, ttime;
    private Spinner tselectgame;
    private Button registerBtn;
    private TextView registerQn;

    private FirebaseAuth mAuth;
    private ProgressDialog loader;
    private DatabaseReference userDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //remember to change activity name
        setContentView(R.layout.activity_tournament_create);


        this.setTitle("Create Tournament");

        tname = findViewById(R.id.tname);
        ttype = findViewById(R.id.ttype);
        tmaxteams = findViewById(R.id.tmaxteams);
        tdate = findViewById(R.id.tdate);
        ttime = findViewById(R.id.ttime);
        tselectgame = findViewById(R.id.tselectgame);
        registerBtn = findViewById(R.id.Btn_Mupdate);
        registerQn = findViewById(R.id.Btn_MUcancel);
        loader = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tnameString = tname.getText().toString();
                String ttypeString = ttype.getText().toString();
                String tmaxteamsString = tmaxteams.getText().toString();
                String tdateString = tdate.getText().toString();
                String ttimeString = ttime.getText().toString();
                String tselectedgameString = tselectgame.getSelectedItem().toString();

                if (TextUtils.isEmpty(tnameString)) {
                    tname.setError("tname is required");
                }
                if (TextUtils.isEmpty(ttypeString)) {
                    ttype.setError("Password is required");
                }
                if (TextUtils.isEmpty(tmaxteamsString)) {
                    tmaxteams.setError("User Name is required");
                }
                if (TextUtils.isEmpty(tdateString)) {
                    tdate.setError("Full Name is required");
                }
                if (TextUtils.isEmpty(ttimeString)) {
                    ttime.setError("Full Name is required");
                }
                if (TextUtils.isEmpty(tselectedgameString)) {
                    Toast.makeText(tournament_create.this, "Select a Game", Toast.LENGTH_SHORT).show();
                }
                else {
                    loader.setMessage("Registering you...");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();
                    //Database initialization, Getting reference and setting child path
                    String currentUserId = mAuth.getCurrentUser().getUid();
                    userDatabaseRef = FirebaseDatabase.getInstance().getReference().child("tournaments");

                    String id  = userDatabaseRef.push().getKey();
                    //using HashMap Data structure to add data to database
                    HashMap userInfo = new HashMap();
                    userInfo.put("tid", id);//tournament id
                    userInfo.put("oid", currentUserId);//organizer id
                    userInfo.put("tname", tnameString);
                    userInfo.put("ttype", ttypeString);
                    userInfo.put("tmaxteams", tmaxteamsString);
                    userInfo.put("tdate", tdateString);
                    userInfo.put("ttime", ttimeString);
                    userInfo.put("tselectedgame", tselectedgameString);
                    //allocating hashmap to the database child
                    userDatabaseRef.child(id).setValue(userInfo).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(tournament_create.this, "Data set Successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(tournament_create.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }
                            finish();
                            //loader.dismiss();
                        }
                    });

                    Intent intent = new Intent(tournament_create.this, organizer_home.class);
                    startActivity(intent);
                    finish();
                    loader.dismiss();

                }

            }
        });
    }

}