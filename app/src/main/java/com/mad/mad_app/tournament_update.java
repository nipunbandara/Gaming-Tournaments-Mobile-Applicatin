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

public class tournament_update extends AppCompatActivity {

    private String tid;
    private TextView TxtTname, TxtGname;
    private EditText TournamentName, tType, tDate, tTime;
    private Button Btn_update, Btn_cancel;
    private String TnameString, TtypeString, TdateString, TtimeString;


    private FirebaseAuth mAuth;
    private ProgressDialog loader;
    private DatabaseReference tournamentRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_update);


        this.setTitle("Update Tournament");

        Btn_update = findViewById(R.id.Btn_update);
        Btn_cancel = findViewById(R.id.Btn_UCancel);

        Intent myIntent = getIntent();
        tid = myIntent.getStringExtra("MAIN_EXTRA");

        mAuth = FirebaseAuth.getInstance();
        tournamentRef = FirebaseDatabase.getInstance().getReference().child("tournaments").child(tid);

        tournamentRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Tournament tourn = task.getResult().getValue(Tournament.class);

                    TxtTname = findViewById(R.id.texttname);
                    TxtGname = findViewById(R.id.textgamename);
                    TournamentName = findViewById(R.id.TournamentName);
                    tType = findViewById(R.id.tType);
                    tDate = findViewById(R.id.tDate);
                    tTime = findViewById(R.id.tTime);

                    TxtTname.setText(tourn.getTname());
                    TxtGname.setText(tourn.getTselectedgame());
                    TournamentName.setText(tourn.getTname());
                    tType.setText(tourn.getTtype());
                    tDate.setText(tourn.getTdate());
                    tTime.setText(tourn.getTtime());


                }
            }
        });

        Btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tournament t = new Tournament();

                TournamentName = findViewById(R.id.TournamentName);
                tType = findViewById(R.id.tType);
                tDate = findViewById(R.id.tDate);
                tTime = findViewById(R.id.tTime);

                TnameString = TournamentName.getText().toString();
                TtypeString = tType.getText().toString();
                TdateString = tDate.getText().toString();
                TtimeString = tTime.getText().toString();


                HashMap userInfo = new HashMap();
                userInfo.put("tname", TnameString);
                userInfo.put("ttype", TtypeString);
                userInfo.put("tdate", TdateString);
                userInfo.put("ttime", TtimeString);


                tournamentRef.updateChildren(userInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(tournament_update.this, "Updated successfully", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(tournament_update.this, tournament_org_list.class);
                            startActivity(intent);
                            finish();
                            loader.dismiss();

                        }else {
                            Toast.makeText(tournament_update.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }

                        finish();

                    }
                });


            }
        });

    }

}