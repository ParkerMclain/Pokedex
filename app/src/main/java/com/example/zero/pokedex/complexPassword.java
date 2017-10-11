package com.example.zero.pokedex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class complexPassword extends AppCompatActivity {

    public String password = "";
    public TextView passwordLabel;
    public Globals global;
    EditText passwordInput, passwordInputConfirm;
    TextView errorDisplay, confirmPassText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        global = (Globals)getApplication();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex_password);
        passwordInput = (EditText) findViewById(R.id.passwordTextBox);
        passwordInputConfirm = (EditText) findViewById(R.id.confirmPasswordTextBox);
        errorDisplay = (TextView) findViewById(R.id.errorMessage);
        confirmPassText = (TextView) findViewById(R.id.textView5);
        //Hides confirm password textbox & label if password has already been created
        if(global.getPasswordType() == 2) {
            passwordInputConfirm.setVisibility(View.INVISIBLE);
            confirmPassText.setVisibility(View.INVISIBLE);
        }

    }

    public void submitPassword(View view)
    {
        if(global.getPasswordType() != 2) { //If there is not a password set already
            if (passwordInput.getText().toString().equals(passwordInputConfirm.getText().toString()) && passwordInput.length() > 0) {
                global.setPassword(passwordInput.getText().toString());
                global.setPasswordType(2);
                Intent Questionaire = new Intent(complexPassword.this, Questionaire.class);
                startActivity(Questionaire);
            } else {
                errorDisplay.setVisibility(View.VISIBLE);
                passwordInput.setText("");
                passwordInputConfirm.setText("");
            }
        }
        else
        {
            if(passwordInput.getText().toString().equals(global.getPassword()))
            {
                Intent Questionaire = new Intent(complexPassword.this, Questionaire.class);
                startActivity(Questionaire);
            }
            else
                errorDisplay.setVisibility(View.VISIBLE);

        }



    }
}
