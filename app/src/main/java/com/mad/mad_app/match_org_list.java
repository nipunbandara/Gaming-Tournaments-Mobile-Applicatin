package com.mad.mad_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;


import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class match_org_list extends AppCompatActivity {

    //Declare Variables
    private RecyclerView recyclerView;

    private Button meditbtn;
    private  Button mdltbtn;

    private TextView TxtTname;

    private String tid;

    private FirebaseAuth mAuth;


    private DatabaseReference matchRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_org_list);

        recyclerView = findViewById(R.id.recyclerView);

        //Explicit intent to receive the Tournament ID
        Intent myIntent = getIntent();
        tid = myIntent.getStringExtra("TIDtomatch");

        //Firebase intialization
        mAuth = FirebaseAuth.getInstance();
        matchRef = FirebaseDatabase.getInstance().getReference().child("matches").child(tid);

        //Title of the activity
        this.setTitle("Create Match");
    }

    @Override
    protected void onStart() {
        super.onStart();

        //Firebase Recycler initiation
        FirebaseRecyclerOptions<Match> options = new FirebaseRecyclerOptions.Builder<Match>()
                .setQuery(matchRef, Match.class)
                .build();

        //Firebase Initiation
        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Match, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, final int position, @NonNull final Match model) {

                //Set Variables to the holder
                holder.setmteamA(model.getMteamA());
                holder.setmteamB(model.getMteamB());

                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });

                //Initiate edit button to the relevant holder
                meditbtn = holder.getmEditbtn();

                //Method to be called if the Edit button is pressed.
                meditbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Explicit Intent to direct to the Update match details page and send match id to the relevant match details page
                        Intent intent = new Intent(match_org_list.this, match_org_update.class);
                        intent.putExtra("mid", model.getMid());
                        startActivity(intent);
                    }
                });

                //Initiate Delete button to the relevant holder
                mdltbtn = holder.getmDltbtn();

                //Method to be called if the Delete button is pressed.
                mdltbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        matchRef.child(model.getMid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    //Toast if Update match records is successful
                                    Toast.makeText(match_org_list.this, "Deleted  successfully", Toast.LENGTH_SHORT).show();
                                }else {
                                    //Toast if Update match records is unsuccessful
                                    Toast.makeText(match_org_list.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                //Initialize layout to be used for display the match
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.retrieve_match_layout, parent, false);
                return new MyViewHolder(view);
            }
        };
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(adapter);
        adapter.startListening();
        adapter.notifyDataSetChanged();
    }

    //ViewHolder class to initialize data fetched from the database
    public class MyViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public TextView mteamA;
        public TextView mteamB;


        public void setmteamA(String mteamA) {
            TextView item = mView.findViewById(R.id.teamA);
            item.setText(mteamA);
        }

        public void setmteamB(String mteamB) {
            TextView item = mView.findViewById(R.id.teamB);
            item.setText(mteamB);
        }

        public Button getmEditbtn(){
            Button item = mView.findViewById(R.id.btn_edit_match);
            return item;
        }
        public Button getmDltbtn() {
            Button mdltbtn = mView.findViewById(R.id.btn_dlt_match);
            return mdltbtn;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            mteamA = itemView.findViewById(R.id.teamA);
            mteamB = itemView.findViewById(R.id.teamB);

        }
    }

    public void match_org_create(View view){

        //Intent to receive the tournament ID
        Intent myIntent = getIntent();
        tid = myIntent.getStringExtra("TIDtomatch");

        //Explicit Intent to pass
        // tournament ID to teh Create match Activity
        Intent intent = new Intent(this, match_org_create.class);
        intent.putExtra("tid", tid);
        startActivity(intent);

    }
    public void match_org_update(View view){

        Intent intent = new Intent(this, match_org_update.class);
        startActivity(intent);

    }

}