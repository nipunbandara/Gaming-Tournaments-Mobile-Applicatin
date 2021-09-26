package com.mad.mad_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class team_home extends AppCompatActivity {
    private RecyclerView recyclerView;

    private Button editbtn;
    private  Button dltbtn;

    private FirebaseAuth mAuth;
    private ProgressDialog loader;
    private DatabaseReference teamRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_home);

        recyclerView = findViewById(R.id.recyclerView);

        mAuth = FirebaseAuth.getInstance();
        teamRef = FirebaseDatabase.getInstance().getReference().child("teams");



        this.setTitle("Teams");

    }
    public void user_team_create(View view){

        Intent intent = new Intent(this, team_create.class);
        startActivity(intent);

    }
    public void user_team_managepage(View view){

        Intent intent = new Intent(this, team_managepage.class);
        startActivity(intent);

    }



    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Team> options = new FirebaseRecyclerOptions.Builder<Team>()
                .setQuery(teamRef, Team.class)
                .build();

        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Team, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, final int position, @NonNull final Team model) {

//                holder.setItemAmount("Allocated amount: $"+ model.getAmount());
//                holder.setDate("On: "+model.getDate());
//                holder.setItemName("BudgetItem: "+model.getItem());
//
//                holder.notes.setVisibility(View.GONE);

                holder.setTmname(model.getTmname());


                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        post_key = getRef(position).getKey();
//                        item = model.getItem();
//                        amount = model.getAmount();
//                        updateData();
                    }
                });

                editbtn = holder.getEditbtn();
                editbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(team_home.this, team_managepage.class);
                        intent.putExtra("MAIN_EXTRA", model.getTid());
                        startActivity(intent);
                    }
                });

                dltbtn = holder.getDltbtn();
                dltbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        teamRef.child(model.getTid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(team_home.this, "Deleted  successfully", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(team_home.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                    }
                });


            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.retrieve_team_layout, parent, false);
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
        public TextView tmname;

        public void setTmname(String tmname) {
            TextView item = mView.findViewById(R.id.teamname);
            item.setText(tmname);
        }

        public Button getEditbtn(){
            Button item = mView.findViewById(R.id.edit_t_btn);
            return item;
        }
        public Button getDltbtn() {
            Button item = mView.findViewById(R.id.delete_t_btn);
            return item;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            tmname = itemView.findViewById(R.id.teamname);



        }
    }

    public void add_team(View view){
        Intent intent = new Intent(this, team_create.class);
        startActivity(intent);

    }

    public void edit_team(View view){

        Intent intent = new Intent(this, team_update.class);
        startActivity(intent);

    }
}