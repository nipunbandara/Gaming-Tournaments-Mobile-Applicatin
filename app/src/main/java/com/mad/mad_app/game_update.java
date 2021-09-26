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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class game_update extends AppCompatActivity {



    private String id;

    private EditText gname, gregion, gdescription, gminteams, gmaxteams;
    private Button Btn_update, Btn_cancel;
    private String gnameString, gregionString, gdescriptionString, gminteamsString,gmaxteamsString;


    private FirebaseAuth mAuth;
    private ProgressDialog loader;
    private DatabaseReference gameRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_update);


        this.setTitle("Update Tournament");

        Btn_update = findViewById(R.id.update_button);
        Btn_cancel = findViewById(R.id.cancle_button);

        Intent myIntent = getIntent();
        id = myIntent.getStringExtra("MAIN_EXTRA");

        mAuth = FirebaseAuth.getInstance();
        gameRef = FirebaseDatabase.getInstance().getReference().child("games").child(id);

        gameRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Game tourn = task.getResult().getValue(Game.class);




                    gname = findViewById(R.id.update_name);
                    gregion = findViewById(R.id.update_region);
                    gdescription = findViewById(R.id.update_des);
                    gminteams = findViewById(R.id.update_min);
                    gmaxteams = findViewById(R.id.update_max);

                    gname.setText(tourn.getGname());
                    gregion.setText(tourn.getGregion());
                    gdescription.setText(tourn.getGdescription());
                    gminteams.setText(tourn.getGminteams());
                    gmaxteams.setText(tourn.getGmaxteams());




                }
            }
        });
        Btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gname = findViewById(R.id.update_name);
                gregion = findViewById(R.id.update_region);
                gdescription = findViewById(R.id.update_des);
                gminteams = findViewById(R.id.update_min);
                gmaxteams = findViewById(R.id.update_max);

                gnameString = gname.getText().toString();
                gregionString =gregion.getText().toString();
                gdescriptionString =gdescription.getText().toString();
                gminteamsString =gminteams.getText().toString();
                gmaxteamsString = gmaxteams.getText().toString();
                //using hashmaps to add details to the database
                HashMap userInfo = new HashMap();
                userInfo.put("gname", gnameString);
                userInfo.put("gregion", gregionString);
                userInfo.put("gdescription", gdescriptionString);
                userInfo.put("gminteams", gminteamsString);
                userInfo.put("gmaxteams", gmaxteamsString);

                //allocating data structure to the database
                gameRef.updateChildren(userInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            //toast when the update is successful
                            Toast.makeText(game_update.this, "Updated successfully", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(game_update.this, game_org_gamelist.class);
                            startActivity(intent);
                            finish();
                            loader.dismiss();

                        }else {
                            Toast.makeText(game_update.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }

                        finish();

                    }
                });


            }
        });

    }

}