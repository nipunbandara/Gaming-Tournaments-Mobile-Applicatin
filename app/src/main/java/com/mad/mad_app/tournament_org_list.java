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

public class tournament_org_list extends AppCompatActivity {
    private RecyclerView recyclerView;

    private Button editbtn;
    private  Button dltbtn;

    private TextView ttid;

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
        //firebase recycler for listing tournaments
        FirebaseRecyclerOptions<Tournament> options = new FirebaseRecyclerOptions.Builder<Tournament>()
                .setQuery(tournamentRef, Tournament.class)
                .build();
        //initializing adapter
        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Tournament, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, final int position, @NonNull final Tournament model) {
                //setting holder for specific data
                holder.setTgame(model.getTname());
                holder.setTname(model.getTselectedgame());

                ttid = holder.getttid();
                ttid.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(tournament_org_list.this, tournament_org_tournament_info.class);
                        intent.putExtra("TID", model.getTid());
                        //intent.putExtra("TID", "Helllow hai");
                        startActivity(intent);
                    }
                });

                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

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
                //getting specific delete button id from holder
                dltbtn = holder.getDltbtn();
                dltbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //removing data from database
                        tournamentRef.child(model.getTid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(tournament_org_list.this, "Deleted  successfully", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(tournament_org_list.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                    }
                });


            }
            //setting layout page for holder
            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.retrieve_tournament_layout, parent, false);
                return new MyViewHolder(view);
            }
        };
        //setting adapter for listing tournaments
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(adapter);
        adapter.startListening();
        adapter.notifyDataSetChanged();


    }
    //setting viewholder class for specific tournament
    public class MyViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public TextView tgame;
        public TextView tname;
        //setting game name method
        public void setTgame(String tgame) {
            TextView item = mView.findViewById(R.id.tselectedgame);
            item.setText(tgame);
        }
        //setting tournament name method
        public void setTname(String tname) {
            TextView item = mView.findViewById(R.id.tname);
            item.setText(tname);
        }

        public TextView getttid(){
            TextView item = mView.findViewById(R.id.tname);
            return item;
        }
        //getting editbutton method
        public Button getEditbtn(){
            Button item = mView.findViewById(R.id.edit_t_btn);
            return item;
        }
        //getting delete button method
        public Button getDltbtn() {
            Button item = mView.findViewById(R.id.delete_t_btn);
            return item;
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
        //intent.putExtra("TID", "Helllow hai");
        startActivity(intent);

    }
}