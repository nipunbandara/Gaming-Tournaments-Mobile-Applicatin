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
import android.widget.ImageView;
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

    private ImageView editbtn;
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


    @Override
    protected void onStart() {
        super.onStart();
        //firebase recycler for for listing teams
        FirebaseRecyclerOptions<Team> options = new FirebaseRecyclerOptions.Builder<Team>()
                .setQuery(teamRef, Team.class)
                .build();
        //initializing adapter
        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Team, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, final int position, @NonNull final Team model) {

                holder.setTmname(model.getTmname());

                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

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


            }
            //setting retrieve layout
            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.retrieve_team_layout, parent, false);
                return new MyViewHolder(view);
            }
        };
        //setting adapter
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(adapter);
        adapter.startListening();
        adapter.notifyDataSetChanged();


    }
    //viewholder class for specific team
    public class MyViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public TextView tmname;
        //setter for teamname
        public void setTmname(String tmname) {
            TextView item = mView.findViewById(R.id.teamname);
            item.setText(tmname);
        }
        //getter for editbutton
        public ImageView getEditbtn(){
            ImageView item = mView.findViewById(R.id.Teamimage);
            return item;
        }
        //getter for delete button
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