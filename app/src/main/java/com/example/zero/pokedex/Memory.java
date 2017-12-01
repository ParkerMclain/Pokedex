package com.example.zero.pokedex;



import android.content.DialogInterface;
import android.content.Intent;

import android.graphics.Typeface;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Date;



public class Memory extends AppCompatActivity {
    public Date date;

    private static final String TAG = "Memory";
    private MemoryItem iMemory;

    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private FirebaseAuth mauth;



    public Button btnLogout;
    public Button test;
    View view;  //Used for colors...and fonts?
    TextView myTextview1;
    TextView myTextview2;
    TextView myTextview3;
    TextView myTextview4;
    TextView myTextview5;
    TextView myTextview6;
    EditText testText;



    @Override

    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mauth = FirebaseAuth.getInstance();
        myRef = mFirebaseDatabase.getReference();



        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                    toastMessage("Successfully signed in with: " + user.getEmail());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    if (user != null) {
                        toastMessage("Successfully signed out of: " + user.getEmail());
                    }
                }
                // ...
            }
        };

   /*     // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                //Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });*/


        myTextview1 = (TextView) findViewById(R.id.txtDate);
        myTextview2 = (TextView) findViewById(R.id.txtMemory);
        myTextview3 = (TextView) findViewById(R.id.textAnimal);
        myTextview4 = (TextView) findViewById(R.id.txtMovie);
        myTextview5 = (TextView) findViewById(R.id.textBook);
        myTextview6 = (TextView) findViewById(R.id.textColor);
        testText = (EditText) findViewById(R.id.editText);
        test = (Button) findViewById(R.id.button7);

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

    @Override
    public void onStart() {
        super.onStart();
        mauth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mauth.removeAuthStateListener(mAuthListener);
        }
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
        //mauth.signOut();
    }

    public void writeToFirebase(View view)
    {


        String editTextString = testText.getText().toString();

        if(!editTextString.equals(""))
        {
            FirebaseUser user = mauth.getCurrentUser();
            String userId = user.getUid();

            myRef = FirebaseDatabase
                    .getInstance()
                    .getReference("MemoryList")
                    .child(userId);

            DatabaseReference pushRef = myRef.push();
            String pushId = pushRef.getKey();
            //iMemory.setPushId(pushId);
            pushRef.child("Memory").setValue(editTextString);
            testText.setText("");
            /*
            FirebaseUser user = mauth.getCurrentUser();
            String userId = user.getUid();
            myRef.child("MemoryList").child(userId).child("Memories").child(editTextString).setValue("true");
            toastMessage("Saving: " + editTextString);
            testText.setText("");*/


        }
        else
            toastMessage("No information provided!");


        /*FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        String editTextString = testText.getText().toString();

        myRef.setValue(editTextString);*/


        /*FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();







        DatabaseReference memoryRef = FirebaseDatabase
                .getInstance()
                .getReference("MEMORIES")
                .child(uid);

        DatabaseReference pushRef = memoryRef.push();
        String pushId = pushRef.getKey();
        //toastMessage(pushId);
        iMemory.setPushId(pushId);*/
        //pushRef.setValue(iMemory);

        //Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();


    }

    private void toastMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
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