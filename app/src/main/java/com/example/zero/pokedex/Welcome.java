package com.example.zero.pokedex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Welcome extends AppCompatActivity {
    private FirebaseAuth  mauth;

    public Button btnUnlock;

    public void initialize() {
        btnUnlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewer) {
                Intent loginFirebase = new Intent(Welcome.this, loginFirebase.class);
                startActivity(loginFirebase);

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mauth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mauth.signOut();
        btnUnlock = (Button)findViewById(R.id.btnStart);
        initialize();


    }
}
