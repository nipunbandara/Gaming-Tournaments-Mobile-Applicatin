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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class tournament_org_list extends AppCompatActivity {
    private RecyclerView recyclerView;

    private Button editbtn;
    private  Button dltbtn;

    private FirebaseAuth mAuth;
    private ProgressDialog loader;
    private DatabaseReference tournamentRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_org_list);

        recyclerView = findViewById(R.id.recyclerView);

        mAuth = FirebaseAuth.getInstance();
        tournamentRef = FirebaseDatabase.getInstance().getReference().child("tournaments");


        this.setTitle("Tournaments");
    }

    public void tournament_user_sel(View view){

        Intent intent = new Intent(this, tournament_user_selected_t.class);
        startActivity(intent);

    }

    public void tournament_reg(View view){

        Intent intent = new Intent(this, tournament_join_user.class);
        startActivity(intent);

    }

    public void home(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Tournament> options = new FirebaseRecyclerOptions.Builder<Tournament>()
                .setQuery(tournamentRef, Tournament.class)
                .build();

        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Tournament, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, final int position, @NonNull final Tournament model) {

//                holder.setItemAmount("Allocated amount: $"+ model.getAmount());
//                holder.setDate("On: "+model.getDate());
//                holder.setItemName("BudgetItem: "+model.getItem());
//
//                holder.notes.setVisibility(View.GONE);

                holder.setTgame(model.getTname());
                holder.setTname(model.getTselectedgame());


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
                        Intent intent = new Intent(tournament_org_list.this, tournament_update.class);
                        intent.putExtra("MAIN_EXTRA", model.getTid());
                        startActivity(intent);
                    }
                });


            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.retrieve_tournament_layout, parent, false);
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
        public TextView tgame;
        public TextView tname;



        public Button editbtn, dltbtn;

        public void setTgame(String tgame) {
            TextView item = mView.findViewById(R.id.tselectedgame);
            item.setText(tgame);
        }

        public void setTname(String tname) {
            TextView item = mView.findViewById(R.id.tname);
            item.setText(tname);
        }

        public Button getEditbtn(){
            Button item = mView.findViewById(R.id.edit_t_btn);
            return item;
        }
        public Button getDltbtn() {
            return dltbtn;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            tgame = itemView.findViewById(R.id.tselectedgame);
            tname = itemView.findViewById(R.id.tname);



        }
    }

    public void add_tournament(View view){
        Intent intent = new Intent(this, tournament_create.class);
        startActivity(intent);

    }

    public void edit_tournament(View view){

        Intent intent = new Intent(this, tournament_update.class);
        startActivity(intent);

    }

    public void tournament_info(View view){

        Intent intent = new Intent(this, tournament_org_tournament_info.class);
        startActivity(intent);

    }
}