package com.example.zero.pokedex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class login extends AppCompatActivity {


    public Button btn_Create;
    public Button btn_Exist;
    public Globals global;
    public void create(){
        btn_Create = (Button)findViewById(R.id.btnCreate);
        btn_Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(global.getPasswordType() == -1) {
                    Intent create = new Intent(login.this, newAccount.class);
                    startActivity(create);
                }

            }
        });}
    public void existing(){
        btn_Exist = (Button)findViewById(R.id.btnExisting);
        btn_Exist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If the password type is Simple Password
                if(global.getPasswordType() == 1) {
                    Intent existing = new Intent(login.this, simplePassword.class);
                    startActivity(existing);
                }
                else
                    if(global.getPasswordType() == 2)
                    {
                        Intent existing = new Intent(login.this, complexPassword.class);
                        startActivity(existing);
                    }else
                        if(global.getPasswordType() == 0)
                    {
                        Intent create = new Intent(login.this, Memory.class);
                        startActivity(create);
                    }

            }
        });}
    protected void onCreate(Bundle savedInstanceState) {
        global = (Globals)getApplication();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        create();
        existing();
    }

}
