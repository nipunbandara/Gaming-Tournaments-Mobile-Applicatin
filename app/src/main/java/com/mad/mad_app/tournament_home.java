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

import java.util.Calendar;
import java.util.Date;

public class tournament_home extends AppCompatActivity {

    private RecyclerView recyclerView;

    private FirebaseAuth mAuth;
    private ProgressDialog loader;
    private DatabaseReference tournamentRef;

    private Button regbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_home);
        Intent intent = getIntent();

        recyclerView = findViewById(R.id.recyclerView);

        mAuth = FirebaseAuth.getInstance();
        tournamentRef = FirebaseDatabase.getInstance().getReference().child("tournaments");

        this.setTitle("Tournaments");
    }

    public void tournament_user_sel(View view){

        Intent intent = new Intent(this, tournament_user_selected_t.class);
        startActivity(intent);

    }

    public void home(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }


    protected void onStart() {
        super.onStart();
        //firebase recycler for listing tournaments
        FirebaseRecyclerOptions<Tournament> options = new FirebaseRecyclerOptions.Builder<Tournament>()
                .setQuery(tournamentRef, Tournament.class)
                .build();
        //initializing adapter
        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Tournament, tournament_home.MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull tournament_home.MyViewHolder holder, final int position, @NonNull final Tournament model) {
                //setting holder for specific data
                holder.setTname(model.getTname());
                holder.setTgame(model.getTselectedgame());

                Date currentTime = Calendar.getInstance().getTime();


                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

                regbutton = holder.getRegbutton();
                regbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(tournament_home.this, tournament_join_user.class);
                        intent.putExtra("MAIN_EXTRA", model.getTid());
                        startActivity(intent);
                    }
                });
                //getting specific delete button id from holder





            }
            //setting layout page for holder
            @NonNull
            @Override
            public tournament_home.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.retrieve_tournament_home_layout, parent, false);
                return new tournament_home.MyViewHolder(view);
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
            TextView item = mView.findViewById(R.id.textView15);
            item.setText(tgame);
        }
        //setting tournament name method
        public void setTname(String tname) {
            TextView item = mView.findViewById(R.id.cr_tournamet_topic5);
            item.setText(tname);
        }

        public Button getRegbutton(){
            return mView.findViewById(R.id.btn_create);
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            tgame = itemView.findViewById(R.id.tselectedgame);
            tname = itemView.findViewById(R.id.tname);
        }
    }

}