package com.mad.mad_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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


public class game_org_gamelist extends AppCompatActivity {


    private RecyclerView recyclerView;
    private Button editbtn,deletebtn;
    private FirebaseAuth mAuth;
    private ProgressDialog loader;
    private DatabaseReference gameRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_org_gamelist);

        recyclerView = findViewById(R.id.recyclerView);

        mAuth = FirebaseAuth.getInstance();
        gameRef = FirebaseDatabase.getInstance().getReference().child("games");


        this.setTitle("Games");
    }
    //firebase recycler for listing games
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Game> options = new FirebaseRecyclerOptions.Builder<Game>()
                .setQuery(gameRef, Game.class)
                .build();

        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Game, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, final int position, @NonNull final Game model) {


                holder.setgname(model.getGname());

                editbtn = holder.getEditbtn();
                editbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(game_org_gamelist.this, game_update.class);
                        intent.putExtra("MAIN_EXTRA", model.getId());
                        startActivity(intent);
                    }
                });




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
                        Intent intent = new Intent(game_org_gamelist.this, game_update.class);
                        intent.putExtra("MAIN_EXTRA", model.getId());
                        startActivity(intent);
                    }
                });




                //method to delete game records
                deletebtn = holder.getDltbtn();
                deletebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gameRef.child(model.getId()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    //toast if the delete is successful
                                    Toast.makeText(game_org_gamelist.this, "Deleted  successfully", Toast.LENGTH_SHORT).show();
                                }else {
                                    //toast if the delete is unsuccessful
                                    Toast.makeText(game_org_gamelist.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                    }
                });
            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.retrieve_game_layout, parent, false);
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
    //viewholder class for a specific game
    public class MyViewHolder extends RecyclerView.ViewHolder{
        View mView;

        public TextView gname;
        public Button getEditbtn(){
            Button item = mView.findViewById(R.id.edit_btn_t11);
            return item;
        }
        public Button getDltbtn() {
            Button item = mView.findViewById(R.id.delete_btn_t12);
            return item;
        }

        public void setgname(String gname) {
            TextView item = mView.findViewById(R.id.cod_mw_name1);
            item.setText(gname);
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            gname = itemView.findViewById(R.id.cod_mw_name1);



        }
    }



    public void create_new_game(View view){

        Intent intent = new Intent(this, game_create.class);
        startActivity(intent);


    }
    public void update_new_game(View view){

        Intent intent = new Intent(this, game_update.class);
        startActivity(intent);

    }
    public void game_create_org(View view){

        Intent intent = new Intent(this, game_org_gamelist.class);
        startActivity(intent);
    }

    public void tournament_create_org(View view){

        Intent intent = new Intent(this, tournament_org_list.class);
        startActivity(intent);
    }

    public void organizer_create_org(View view){

        Intent intent = new Intent(this, organizer_organizer_info.class);
        startActivity(intent);
    }

    public void home_org(View view){

        Intent intent = new Intent(this, organizer_home.class);
        startActivity(intent);

    }

}