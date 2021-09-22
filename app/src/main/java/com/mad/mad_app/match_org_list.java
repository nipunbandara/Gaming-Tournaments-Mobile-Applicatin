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
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class match_org_list extends AppCompatActivity {

    private RecyclerView recyclerView;

    private Button meditbtn;
    private  Button mdltbtn;

    private FirebaseAuth mAuth;
    private ProgressDialog loader;
    private DatabaseReference matchRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_org_list);

        recyclerView = findViewById(R.id.recyclerView);

        mAuth = FirebaseAuth.getInstance();
        matchRef = FirebaseDatabase.getInstance().getReference().child("matches");


        this.setTitle("Create Match");
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Match> options = new FirebaseRecyclerOptions.Builder<Match>()
                .setQuery(matchRef, Match.class)
                .build();

        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Match, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, final int position, @NonNull final Match model) {

//                holder.setItemAmount("Allocated amount: $"+ model.getAmount());
//                holder.setDate("On: "+model.getDate());
//                holder.setItemName("BudgetItem: "+model.getItem());
//
//                holder.notes.setVisibility(View.GONE);

                holder.setmteamA(model.getmteamA());
                holder.setmteamB(model.getmteamB());



                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        post_key = getRef(position).getKey();
//                        item = model.getItem();
//                        amount = model.getAmount();
//                        updateData();
                    }
                });

                meditbtn = holder.getmEditbtn();
                meditbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(match_org_list.this, match_org_update.class);
                        intent.putExtra("MAIN_EXTRA", model.getmid());
                        startActivity(intent);
                    }
                });


            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
            Button item = mView.findViewById(R.id.Btn_edit_match);
            return item;
        }
        public Button getmDltbtn() {
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

        Intent intent = new Intent(this, match_org_create.class);
        startActivity(intent);

    }
    public void match_org_update(View view){

        Intent intent = new Intent(this, match_org_update.class);
        startActivity(intent);

    }

}