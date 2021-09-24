package com.mad.mad_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class team_managepage extends AppCompatActivity {
    private Button editbtn;
    private  Button dltbtn;
    private String tid;

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
    }

    public void user_team_edit(View view){

        Intent intent = new Intent(this, team_update.class);
        startActivity(intent);
    }


    protected void onStart() {
        super.onStart();


            Button editbtn = findViewById(R.id.save);



            Button dltbtn = findViewById(R.id.save23);




                editbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(team_managepage.this, team_managepage.class);
            intent.putExtra("MAIN_EXTRA", tid);
            startActivity(intent);
        }
    });


                dltbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            teamRef.child(tid).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
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