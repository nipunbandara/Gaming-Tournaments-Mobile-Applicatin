package com.mad.mad_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class team_managepage extends AppCompatActivity {
    private Button editbtn;
    private  Button dltbtn;
    private String tid;
    private TextView TeamName,Tdesc;

    private FirebaseAuth mAuth;
    private ProgressDialog loader;
    private DatabaseReference teamRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_managepage);

        Intent myIntent = getIntent();
        tid = myIntent.getStringExtra("MAIN_EXTRA");
        this.setTitle("Manage Team");


        teamRef = FirebaseDatabase.getInstance().getReference().child("teams").child(tid);

        teamRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Team team = task.getResult().getValue(Team.class);

                    TeamName = findViewById(R.id.textView47);
                    Tdesc = findViewById(R.id.textView49);

                    TeamName.setText(team.getTmname());
                    Tdesc.setText(team.getTdesc());

                }
            }
        });










    }

    public void user_team_edit(View view){

        Intent intent = new Intent(this, team_update.class);
        startActivity(intent);
    }


    protected void onStart() {
        super.onStart();


        editbtn = findViewById(R.id.save);

        dltbtn = findViewById(R.id.manage_delete);


        teamRef = FirebaseDatabase.getInstance().getReference().child("teams").child(tid);

        editbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(team_managepage.this, team_update.class);
            intent.putExtra("MAINEXTRA1", tid);
            startActivity(intent);
            }
        });

        //delete method for specific team
        dltbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //deleting data from database
            teamRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(team_managepage.this, "Deleted  successfully", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(team_managepage.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
        });


}
}