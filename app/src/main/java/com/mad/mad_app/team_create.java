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

public class team_create extends AppCompatActivity {

    private EditText tmname, tdesc;
    private Button registerBtn;
    private TextView registerQn;

    private FirebaseAuth mAuth;
    private ProgressDialog loader;
    private DatabaseReference userDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //remember to change activity name
        setContentView(R.layout.activity_team_create);


        this.setTitle("Create Team");


        tmname = findViewById(R.id.editTextTextPersonName);
        tdesc = findViewById(R.id.editTextTextPersonName6);
        registerBtn = findViewById(R.id.save23);
        registerQn = findViewById(R.id.button44);

        loader = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tmnameString = tmname.getText().toString();
                String tdescString = tdesc.getText().toString();

                if (TextUtils.isEmpty(tmnameString)) {
                    tmname.setError("Team name is required");
                }
                if (TextUtils.isEmpty(tdescString)) {
                    tdesc.setError("Description is required");
                }

                else {

                    loader.setMessage("Registering you...");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();
                    //database initialization
                    String currentUserId = mAuth.getCurrentUser().getUid();
                    userDatabaseRef = FirebaseDatabase.getInstance().getReference().child("teams");

                    String id  = userDatabaseRef.push().getKey();
                    //creating hashmap to set data
                    HashMap userInfo = new HashMap();
                    userInfo.put("tid", id);
                    userInfo.put("oid", currentUserId);
                    userInfo.put("tmname", tmnameString);
                    userInfo.put("tdesc", tdescString);
                    //adding hashmap to the database
                    userDatabaseRef.child(id).setValue(userInfo).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(team_create.this, "Data set Successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(team_create.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }

                            finish();
                            //loader.dismiss();
                        }
                    });

                    Intent intent = new Intent(team_create.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    loader.dismiss();

                }

            }
        });
    }

}