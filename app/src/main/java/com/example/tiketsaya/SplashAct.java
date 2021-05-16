package com.example.tiketsaya;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashAct extends AppCompatActivity {

    Animation app_splash,btt;
    ImageView splash_logo;
    TextView splash_tagline;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // load animation
        app_splash = AnimationUtils.loadAnimation(this, R.anim.app_splash);
        btt = AnimationUtils.loadAnimation(this, R.anim.btt);

        // load element
        splash_logo = findViewById(R.id.splash_logo);
        splash_tagline = findViewById(R.id.splash_tagline);

        // run animation
        splash_logo.startAnimation(app_splash);
        splash_tagline.startAnimation(btt);

        getUsernamelocal();

    }
    public void getUsernamelocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
        if (username_key_new.isEmpty()){
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent gogetstarted = new Intent(SplashAct.this, GetStartedAct.class);
                    startActivity(gogetstarted);
                    finish();
                }
            }, 2000);
        }
        else {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent gogethome = new Intent(SplashAct.this, HomeAct.class);
                    startActivity(gogethome);
                    finish();
                }
            }, 2000);
        }

    }
}
