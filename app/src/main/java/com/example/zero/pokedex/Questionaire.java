package com.example.zero.pokedex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.zero.pokedex.R.id.textView2;

public class Questionaire extends AppCompatActivity {

    String Color, Movie, Animal, Book;

    EditText editColor;
    EditText editMovie;
    EditText editAnimal;
    EditText editBook;

    Button btnSubmitInfo;


    public Button btn_submitInfo;
    public Globals global;

    public void submitInfo()
    {
        btn_submitInfo = (Button)findViewById(R.id.btnSubmitInfo   );

        btn_submitInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                global = (Globals)getApplication();
                if(global.getPasswordType() == -1) {
                    global.setPasswordType(0);
                }
                Intent password = new Intent(Questionaire.this, Memory.class);
                startActivity(password);

            }
        });}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        global = (Globals)getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionaire);

        editColor = (EditText) findViewById(R.id.editColor);
        editMovie = (EditText) findViewById(R.id.editMovie);
        editAnimal = (EditText) findViewById(R.id.editAnimal);
        editBook = (EditText) findViewById(R.id.editBook);

        //btnSubmitInfo = (Button) findViewById((R.id.btnSubmitInfo));
        submitInfo();

    }
}
