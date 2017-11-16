package com.example.zero.pokedex;



import android.content.DialogInterface;
import android.content.Intent;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;



public class Memory extends AppCompatActivity {
    public Date date;

    public Button btnLogout;
    public Button test;
    View view;  //Used for colors...and fonts?
    private FirebaseAuth mAuth;


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        btnLogout = (Button) findViewById(R.id.logoutButton);
        Intent texts = getIntent();
        Bundle extraText = texts.getExtras();
        String enteredTexts;
        enteredTexts = extraText.getString("Movie");
        TextView Movie = (TextView) findViewById(R.id.txtMovie);
        Movie.setText(enteredTexts);
        enteredTexts = extraText.getString("Book");
        TextView Book = (TextView) findViewById(R.id.textBook);
        Book.setText(enteredTexts);
        enteredTexts = extraText.getString("Color");
        TextView Color = (TextView) findViewById(R.id.textColor);
        Color.setText(enteredTexts);
        enteredTexts = extraText.getString("Animal");
        TextView Animal = (TextView) findViewById(R.id.textAnimal);
        Animal.setText(enteredTexts);
        TextView textView = (TextView) findViewById(R.id.txtDate);
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        textView.setText(currentDateTimeString);


        view = this.getWindow().getDecorView(); //For colors

    }


    public void showAlert(View view) {
        final AlertDialog.Builder logoutAlert = new AlertDialog.Builder(this);
        logoutAlert.setMessage("Are you sure you want to logout?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        mAuth.signOut();
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

    //Change background color of activity
    public void backRed(View v) {
        view.setBackgroundResource(R.color.red);
    }

    public void backWhite(View v) {
        view.setBackgroundResource(R.color.white);
    }

    public void backBlue(View v) {
        view.setBackgroundResource(R.color.blue);
    }
}


   /* public class DatabaseOpenHelper extends SQLiteOpenHelper {
        public static final String DATABASE = "Memory.db";
        public static final String TABLE = "Memory";
        public static final int VERSION = 1;

        public DatabaseOpenHelper(Context context) {
            super(context, DATABASE, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE memo(date INTEGER PRIMARY KEY, memo TEXT);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }*/