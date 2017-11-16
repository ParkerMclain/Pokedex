package com.example.zero.pokedex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Welcome extends AppCompatActivity {
    private FirebaseAuth mAuth;

    public Button btnUnlock;
    public void initialize(){
        btnUnlock = (Button)findViewById(R.id.btnStart);
        btnUnlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginFirebase = new Intent(Welcome.this, com.example.zero.pokedex.LoginFirebase.class);
                startActivity(LoginFirebase);

            }
        });}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mAuth.signOut();
        initialize();


    }
}
