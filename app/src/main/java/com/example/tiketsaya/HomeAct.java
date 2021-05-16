package com.example.tiketsaya;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.florent37.shapeofview.shapes.CircleView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class HomeAct extends AppCompatActivity {

    CircleView btn_to_profile;
    LinearLayout btn_ticket_pisa, btn_ticket_torri,
            btn_ticket_pagoda, btn_ticket_candi,
            btn_ticket_sphinx, btn_ticket_monas;

    ImageView photo_home_user;
    TextView nama_lengkap,bio, user_balance;

    DatabaseReference reference;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getUsernamelocal();


        photo_home_user = findViewById(R.id.photo_home_user);


        btn_ticket_pisa = findViewById(R.id.btn_ticket_pisa);
        btn_ticket_torri = findViewById(R.id.btn_ticket_torri);
        btn_ticket_pagoda = findViewById(R.id.btn_ticket_pagoda);
        btn_ticket_candi = findViewById(R.id.btn_ticket_candi);
        btn_ticket_sphinx = findViewById(R.id.btn_ticket_sphinx);
        btn_ticket_monas = findViewById(R.id.btn_ticket_monas);


        btn_to_profile = findViewById(R.id.btn_to_profile);
        nama_lengkap = findViewById(R.id.nama_lengkap);
        bio = findViewById(R.id.bio);
        user_balance = findViewById(R.id.user_balance);

        // mengambil data dari firebase
        reference = FirebaseDatabase.getInstance().getReference()
                .child("Users").child(username_key_new);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nama_lengkap.setText(dataSnapshot.child("nama_engkap").getValue().toString());
                bio.setText(dataSnapshot.child("bio").getValue().toString());
                user_balance.setText("US$" + dataSnapshot.child("user_balance").getValue().toString());
                Picasso.with(HomeAct.this)
                        .load(dataSnapshot.child("url_photo_profile")
                                .getValue().toString()).centerCrop().fit()
                        .into(photo_home_user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_ticket_pisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gototicketpisa = new Intent(HomeAct.this, TicketDetailAct.class);
                gototicketpisa.putExtra("jenis_ticket","Pisa");
                startActivity(gototicketpisa);
            }
        });

        btn_ticket_torri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gototicketpisa = new Intent(HomeAct.this, TicketDetailAct.class);
                gototicketpisa.putExtra("jenis_ticket","Torri");
                startActivity(gototicketpisa);
            }
        });

        btn_ticket_pagoda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gototicketpisa = new Intent(HomeAct.this, TicketDetailAct.class);
                gototicketpisa.putExtra("jenis_ticket","Pagoda");
                startActivity(gototicketpisa);
            }
        });

        btn_ticket_candi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gototicketpisa = new Intent(HomeAct.this, TicketDetailAct.class);
                gototicketpisa.putExtra("jenis_ticket","Candi");
                startActivity(gototicketpisa);
            }
        });

        btn_ticket_sphinx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gototicketpisa = new Intent(HomeAct.this, TicketDetailAct.class);
                gototicketpisa.putExtra("jenis_ticket","Sphinx");
                startActivity(gototicketpisa);
            }
        });

        btn_ticket_monas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gototicketpisa = new Intent(HomeAct.this, TicketDetailAct.class);
                gototicketpisa.putExtra("jenis_ticket","Monas");
                startActivity(gototicketpisa);
            }
        });


        btn_to_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoprofileact = new Intent(HomeAct.this,MyProfileAct.class);
                startActivity(gotoprofileact);
            }
        });

    }

    public void getUsernamelocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key,"");

    }

}
