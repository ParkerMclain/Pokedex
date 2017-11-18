package com.example.zero.pokedex;



import android.content.DialogInterface;
import android.content.Intent;

import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.text.DateFormat;
import java.util.Date;



public class Memory extends AppCompatActivity {
    public Date date;

    public Button btnLogout;
    public Button test;
    View view;  //Used for colors...and fonts?
    private FirebaseAuth mauth;
    TextView myTextview1;
    TextView myTextview2;
    TextView myTextview3;
    TextView myTextview4;
    TextView myTextview5;
    TextView myTextview6;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        mauth = FirebaseAuth.getInstance();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);

        myTextview1 = (TextView) findViewById(R.id.txtDate);
        myTextview2 = (TextView) findViewById(R.id.txtMemory);
        myTextview3 = (TextView) findViewById(R.id.textAnimal);
        myTextview4 = (TextView) findViewById(R.id.txtMovie);
        myTextview5 = (TextView) findViewById(R.id.textBook);
        myTextview6 = (TextView) findViewById(R.id.textColor);

        btnLogout = (Button) findViewById(R.id.logoutButton);
        Intent texts = getIntent();
        Bundle extraText = texts.getExtras();
        String enteredTexts;
        enteredTexts = extraText.getString("Movie");
        TextView movie = (TextView) findViewById(R.id.txtMovie);
        movie.setText(enteredTexts);
        enteredTexts = extraText.getString("Book");
        TextView book = (TextView) findViewById(R.id.textBook);
        book.setText(enteredTexts);
        enteredTexts = extraText.getString("Color");
        TextView color = (TextView) findViewById(R.id.textColor);
        color.setText(enteredTexts);
        enteredTexts = extraText.getString("Animal");
        TextView animal = (TextView) findViewById(R.id.textAnimal);
        animal.setText(enteredTexts);
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
                    public void onClick(DialogInterface dialogInterface, int integer) {
                        dialogInterface.dismiss();
                        logoutUser();
                        Intent login = new Intent(Memory.this, Welcome.class);
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
        logoutAlert.show();
    }

    //Change background color of activity
    public void backRed(View viewer) {
        view.setBackgroundResource(R.color.red);
    }

    public void backWhite(View viewer) {
        view.setBackgroundResource(R.color.white);
    }

    public void backBlue(View viewer) {
        view.setBackgroundResource(R.color.blue);
    }

    public void fontChange1(View viewer) {
        Typeface myFontTypeFace1 = Typeface.createFromAsset(getAssets(), getFont("calibri.ttf").toString());
        changeAllFont(myFontTypeFace1);

    }

    public void fontChange2(View viewer) {
        Typeface myFontTypeFace1 = Typeface.createFromAsset(getAssets(), getFont("OldEnglish.TTF").toString());
        changeAllFont(myFontTypeFace1);
    }

    public void fontChange3(View viewer) {
        Typeface myFontTypeFace1 = Typeface.createFromAsset(getAssets(), getFont("HARLOWSI.TTF").toString());
        changeAllFont(myFontTypeFace1);
    }

    public void changeAllFont(Typeface font) {
        myTextview1.setTypeface(font);
        myTextview2.setTypeface(font);
        myTextview3.setTypeface(font);
        myTextview4.setTypeface(font);
        myTextview5.setTypeface(font);
        myTextview6.setTypeface(font);

    }

    public static String getFont(String font) {
        return font;
    }

    public void logoutUser() {
        mauth.signOut();
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