package com.example.zero.pokedex;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.os.Build.VERSION_CODES.M;
import static com.example.zero.pokedex.R.id.txtMovie;

public class Memory extends AppCompatActivity {


    public Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        btnLogout = (Button)findViewById(R.id.logoutButton);
        Intent texts = getIntent();
        Bundle extraText = texts.getExtras();
        String enteredTexts;
        enteredTexts = extraText.getString("Movie");
        TextView Movie = (TextView)findViewById(R.id.txtMovie);
        Movie.setText(enteredTexts);
        enteredTexts = extraText.getString("Book");
        TextView Book = (TextView)findViewById(R.id.textBook);
        Book.setText(enteredTexts);
        enteredTexts = extraText.getString("Color");
        TextView Color = (TextView)findViewById(R.id.textColor);
        Color.setText(enteredTexts);
        enteredTexts = extraText.getString("Animal");
        TextView Animal = (TextView)findViewById(R.id.textAnimal);
        Animal.setText(enteredTexts);


    }

    public void showAlert(View view)
    {
        final AlertDialog.Builder logoutAlert = new AlertDialog.Builder(this);
        logoutAlert.setMessage("Are you sure you want to logout?")
            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    Intent login = new Intent(Memory.this, Welcome.class);
                    startActivity(login);
                }
            })
            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            })
            .setTitle("Warning!");
        logoutAlert.show();

    }
}
