package com.example.tiketsaya;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SuccessBuyAct extends AppCompatActivity {

    Button btn_my_dashboard, btn_view_ticket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_buy);


        // load element
        btn_my_dashboard = findViewById(R.id.btn_my_dashboard);
        btn_view_ticket = findViewById(R.id.btn_view_ticket);


        // fungcion button
        btn_view_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoprofile = new Intent(SuccessBuyAct.this,MyProfileAct.class);
                startActivity(gotoprofile);
            }
        });

        btn_my_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotohome = new Intent(SuccessBuyAct.this,HomeAct.class);
                startActivity(gotohome);
            }
        });

    }
}
