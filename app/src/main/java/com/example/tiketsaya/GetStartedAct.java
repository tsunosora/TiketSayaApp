package com.example.tiketsaya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class GetStartedAct extends AppCompatActivity {

    Button btn_signin,btn_new_account_creat;
    Animation ttb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        // load animation
        ttb = AnimationUtils.loadAnimation(this, R.anim.ttb);

        // run animation


        btn_signin = findViewById(R.id.btn_signin);
        btn_new_account_creat = findViewById(R.id.btn_new_account_creat);

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotosignin = new Intent(GetStartedAct.this, SignInAct.class);
                startActivity(gotosignin);
            }
        });
        btn_new_account_creat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoregisterone = new Intent(GetStartedAct.this, RegisteOneAct.class);
                startActivity(gotoregisterone);
            }
        });
    }
}
