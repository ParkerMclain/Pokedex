package com.example.zero.pokedex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class password extends AppCompatActivity {
    public Button btn_simplePassword;
    public Button btn_complexPassword;

    public void simplePassword(){
        btn_simplePassword = (Button)findViewById(R.id.btnSimple);
        btn_simplePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent simplePassword = new Intent(password.this, simplePassword.class);
                startActivity(simplePassword);

            }
        });}

    public void complexPassword(){
        btn_complexPassword = (Button)findViewById(R.id.btnComplex);
        btn_complexPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent complexPassword = new Intent(password.this, complexPassword.class);
                startActivity(complexPassword);

            }
        });}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        simplePassword();
        complexPassword();
    }
}
