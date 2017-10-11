package com.example.zero.pokedex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.zero.pokedex.R.id.errorMessage;
import static com.example.zero.pokedex.R.id.textView2;

public class simplePassword extends AppCompatActivity implements View.OnClickListener {
    public String password = "";
    public TextView passwordLabel;
    public Globals global;
    public TextView errorDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        global = (Globals)getApplication();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_password);


        errorDisplay = (TextView) findViewById(errorMessage);

        passwordLabel = (TextView) findViewById(textView2);

        Button buttonReset = (Button) findViewById(R.id.reset);
        buttonReset.setOnClickListener(this);

        Button buttonOne = (Button) findViewById(R.id.btn1);
        buttonOne.setOnClickListener(this);

        Button buttonTwo = (Button) findViewById(R.id.btn2);
        buttonTwo.setOnClickListener(this);

        Button buttonThree = (Button) findViewById(R.id.btn3);
        buttonThree.setOnClickListener(this);

        Button buttonFour = (Button) findViewById(R.id.btn4);
        buttonFour.setOnClickListener(this);

        Button buttonFive = (Button) findViewById(R.id.btn5);
        buttonFive.setOnClickListener(this);

        Button buttonSix = (Button) findViewById(R.id.btn6);
        buttonSix.setOnClickListener(this);

        Button buttonSeven = (Button) findViewById(R.id.btn7);
        buttonSeven.setOnClickListener(this);

        Button buttonEight = (Button) findViewById(R.id.btn8);
        buttonEight.setOnClickListener(this);

        Button buttonNine = (Button) findViewById(R.id.btn9);
        buttonNine.setOnClickListener(this);

        Button buttonZero = (Button) findViewById(R.id.btnZero);
        buttonZero.setOnClickListener(this);




    }

    public void onClick(View v) {
        errorDisplay.setVisibility(View.INVISIBLE);
        switch (v.getId()) {
                case R.id.btn1:
                    password = password + "1";
                    passwordLabel.setText(passwordLabel.getText() + "*");
                    break;
                case R.id.btn2:
                    password = password + "2";
                    passwordLabel.setText(passwordLabel.getText() + "*");
                    break;
                case R.id.btn3:
                    password = password + "3";
                    passwordLabel.setText(passwordLabel.getText() + "*");
                    break;
                case R.id.btn4:
                    password = password + "4";
                    passwordLabel.setText(passwordLabel.getText() + "*");
                    break;
                case R.id.btn5:
                    password = password + "5";
                    passwordLabel.setText(passwordLabel.getText() + "*");
                    break;
                case R.id.btn6:
                    password = password + "6";
                    passwordLabel.setText(passwordLabel.getText() + "*");
                    break;
                case R.id.btn7:
                    password = password + "7";
                    passwordLabel.setText(passwordLabel.getText() + "*");
                    break;
                case R.id.btn8:
                    password = password + "8";
                    passwordLabel.setText(passwordLabel.getText() + "*");
                    break;
                case R.id.btn9:
                    password = password + "9";
                    passwordLabel.setText(passwordLabel.getText() + "*");
                    break;
                case R.id.btnZero:
                    password = password + "0";
                    passwordLabel.setText(passwordLabel.getText() + "*");
                    break;
                case R.id.reset:
                    password = "";
                    passwordLabel.setText("");
                    break;
            }

            if(password.length() == 4 && global.getPasswordType() == -1) {
                global.setPassword(password);
                global.setPasswordType(1);
                Intent Questionaire = new Intent(simplePassword.this, Questionaire.class);
                startActivity(Questionaire);
            }
            else
                if(password.length() == 4 && global.getPasswordType() == 1)
                {
                    if(password.equals(global.getPassword()))
                    {
                        Intent Questionaire = new Intent(simplePassword.this, Questionaire.class);
                        startActivity(Questionaire);
                    }
                    else {
                        errorDisplay.setVisibility(View.VISIBLE);
                        password = "";
                        passwordLabel.setText("");
                    }

                }


    }
}
