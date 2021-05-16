package com.example.tiketsaya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SuccessRegisterAct extends AppCompatActivity {

    Button btn_explore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_register);

        btn_explore = findViewById(R.id.btn_Explore);
        btn_explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotohomeact = new Intent(SuccessRegisterAct.this,HomeAct.class);
                startActivity(gotohomeact);
            }
        });


    }
}
