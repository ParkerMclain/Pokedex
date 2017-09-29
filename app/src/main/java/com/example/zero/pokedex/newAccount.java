package com.example.zero.pokedex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class newAccount extends AppCompatActivity {

    public Button btn_setPassword;
    public Button btn_questionaire;
    boolean hasSetPassword = false;
    public void setPassword(){
        btn_setPassword = (Button)findViewById(R.id.btnSetPassword);
        btn_setPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent password = new Intent(newAccount.this, password.class);
                hasSetPassword = true;
                startActivity(password);

            }
        });}

    public void questionaire(){
        btn_questionaire = (Button)findViewById(R.id.btnQuestionaire);
        btn_questionaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent password = new Intent(newAccount.this, Questionaire.class);
                startActivity(password);

            }
        });}
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        setPassword();
        if(hasSetPassword == true)
        {
            questionaire();
        }

    }
}
