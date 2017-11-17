package com.example.zero.pokedex;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.R.id.edit;
import static com.example.zero.pokedex.R.id.textView2;

public class Questionaire extends AppCompatActivity {

    EditText editColor;
    EditText editMovie;
    EditText editAnimal;
    EditText editBook;


    public Globals global;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        global = (Globals)getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionaire);



        editColor = (EditText) findViewById(R.id.editColor);
        editMovie = (EditText) findViewById(R.id.editMovie);
        editAnimal = (EditText) findViewById(R.id.editAnimal);
        editBook = (EditText) findViewById(R.id.editBook);

    }

    public void showAlert(View view) {
        final AlertDialog.Builder submitAlert = new AlertDialog.Builder(this);
        submitAlert.setMessage("Are you sure you want to submit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int integer) {
                        dialogInterface.dismiss();
                        Bundle editTexts = new Bundle();
                        editTexts.putString("Color", editColor.getText().toString());
                        editTexts.putString("Movie", editMovie.getText().toString());
                        editTexts.putString("Animal", editAnimal.getText().toString());
                        editTexts.putString("Book", editBook.getText().toString());
                        Intent login = new Intent(Questionaire.this, Memory.class);
                        login.putExtras(editTexts);
                        startActivity(login);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int integer) {
                        dialogInterface.dismiss();
                    }
                })
                .setTitle("Warning!");
        submitAlert.show();

    }

}
