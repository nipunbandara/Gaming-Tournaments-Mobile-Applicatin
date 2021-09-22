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

    private String mid;
//    private TextView TxtTname, TxtGname;
    private EditText mUround, mUteamA, mUteamB;
    private Button Btn_Mupdate, Btn_MUcancel;
    private String mUroundString, mUteamAString, mUteamBString;


    private FirebaseAuth mAuth;
    private ProgressDialog loader;
    private DatabaseReference matchRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_org_update);


       // this.setTitle("Update Match");

        Btn_Mupdate = findViewById(R.id.Btn_Mupdate);
        Btn_MUcancel = findViewById(R.id.Btn_MUcancel);

        Intent myIntent = getIntent();
        mid = myIntent.getStringExtra("MAIN_EXTRA");

        mAuth = FirebaseAuth.getInstance();
        matchRef = FirebaseDatabase.getInstance().getReference().child("matches").child(mid);

        matchRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Match match = task.getResult().getValue(Match.class);

//                    TxtTname = findViewById(R.id.texttname);
//                    TxtGname = findViewById(R.id.textgamename);
//                    TournamentName = findViewById(R.id.TournamentName);
//                    tType = findViewById(R.id.tType);
//                    tDate = findViewById(R.id.tDate);
//                    tTime = findViewById(R.id.tTime);

                    mUround = findViewById(R.id.mUround);
                    mUteamA = findViewById(R.id.mUteamA);
                    mUteamB = findViewById(R.id.mUteamB);

//                    TxtTname.setText(tourn.getTname());
//                    TxtGname.setText(tourn.getTselectedgame());
//                    TournamentName.setText(tourn.getTname());
//                    tType.setText(tourn.getTtype());
//                    tDate.setText(tourn.getTdate());
//                    tTime.setText(tourn.getTtime());

                    mUround.setText(match.getmround());
                    mUteamA.setText(match.getmteamA());
                    mUteamB.setText(match.getmteamB());

                }
            }
        });

        Btn_Mupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tournament t = new Tournament();

                mUround = findViewById(R.id.mUround);
                mUteamA = findViewById(R.id.mUteamA);
                mUteamB = findViewById(R.id.mUteamB);

                mUroundString = mUround.getText().toString();
                mUteamAString = mUteamA.getText().toString();
                mUteamBString = mUteamB.getText().toString();



                HashMap userInfo = new HashMap();
                userInfo.put("mround", mUroundString);
                userInfo.put("mteamA", mUteamAString);
                userInfo.put("mteamB", mUteamBString);
                //userInfo.put("ttime", TtimeString);


                matchRef.updateChildren(userInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(match_org_update.this, "Updated successfully", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(match_org_update.this, match_org_list.class);
                            startActivity(intent);
                            finish();
                            loader.dismiss();

                        }else {
                            Toast.makeText(match_org_update.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }

                        finish();

                    }
                });


            }
        });

    }
}