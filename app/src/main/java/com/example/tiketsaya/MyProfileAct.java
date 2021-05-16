package com.example.tiketsaya;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyProfileAct extends AppCompatActivity {

    LinearLayout item_my_ticket;
    Button btn_edit_profile, btn_back_home, btn_sign_out;
    ImageView photo_home_user;
    TextView nama_profile, bio;


    DatabaseReference reference, reference2;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    RecyclerView myticket_place;
    ArrayList<MyTicket> list;
    TicketAdapter ticketAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        getUsernamelocal();

        // load elemen
        btn_back_home = findViewById(R.id.btn_back_home);
        btn_sign_out = findViewById(R.id.btn_sign_out);

        item_my_ticket = findViewById(R.id.item_my_ticket);
        photo_home_user = findViewById(R.id.photo_home_user);
        btn_edit_profile = findViewById(R.id.btn_edit_profile);
        nama_profile = findViewById(R.id.nama_profile);
        bio = findViewById(R.id.bio);

        myticket_place = findViewById(R.id.myticket_place);
        myticket_place.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<MyTicket>();


        // mengambil data dari firebase
        reference = FirebaseDatabase.getInstance().getReference()
                .child("Users").child(username_key_new);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nama_profile.setText(dataSnapshot.child("nama_engkap").getValue().toString());
                bio.setText(dataSnapshot.child("bio").getValue().toString());
                Picasso.with(MyProfileAct.this)
                        .load(dataSnapshot.child("url_photo_profile")
                                .getValue().toString()).centerCrop().fit()
                        .into(photo_home_user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        btn_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoeditprofil = new Intent(MyProfileAct.this,EditProfilAct.class);
                startActivity(gotoeditprofil);
            }
        });


        reference2 = FirebaseDatabase.getInstance().getReference().child("MyTickets").child(username_key_new);
        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    MyTicket p = dataSnapshot1.getValue(MyTicket.class);
                    list.add(p);
                }
                ticketAdapter = new TicketAdapter(MyProfileAct.this, list);
                myticket_place.setAdapter(ticketAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_back_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotohome = new Intent(MyProfileAct.this,HomeAct.class);
                startActivity(gotohome);
            }
        });

        btn_sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //menyimpan data kepada local storage (handphone)
                SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(username_key, null);
                editor.apply();

                // berpindah activity
                Intent gotosignin = new Intent(MyProfileAct.this,SignInAct.class);
                startActivity(gotosignin);
                finish();
            }
        });


    }
    public void getUsernamelocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");

    }
}
