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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class newMemory extends AppCompatActivity {
    public Date date;

    private static final String TAG = "Memory";
    String fontType;

    //Firebase declarations
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myDatabase;
    private FirebaseAuth mauth;



    public Button btnLogout;
    public Button test;
    public Button erase;
    EditText testText;
    public View view;
    private ListView listView;
    public Spinner fontSpinner;
    public Spinner backgroundColorSpinner;

    //List object to hold memories
    List<MemoryItem> memories;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        fontType = "";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_memory);

        //Firebase
        mauth = FirebaseAuth.getInstance();
        FirebaseUser user = mauth.getCurrentUser();
        final String userId = user.getUid();
        myDatabase = FirebaseDatabase.getInstance().getReference(userId);

        //Listview
        memories = new ArrayList<>();

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

        view = this.getWindow().getDecorView();

        fontSpinner = (Spinner) findViewById(R.id.spinnerFonts);
        backgroundColorSpinner = (Spinner) findViewById(R.id.spinnerBackground);
        testText = (EditText) findViewById(R.id.editText);
        test = (Button) findViewById(R.id.button7);
        btnLogout = (Button) findViewById(R.id.logoutButton);
        listView = (ListView) findViewById(R.id.databaseListView);
        erase = (Button) findViewById((R.id.btnErase));


        fontSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                String getFont = fontSpinner.getSelectedItem().toString();
                fontType = getFont;
                refreshList();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        backgroundColorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                String getColor = backgroundColorSpinner.getSelectedItem().toString();
                switch (getColor) {
                      case "Blue":view.setBackgroundResource(R.color.blue);
                        break;
                      case "Red":view.setBackgroundResource(R.color.red);
                        break;
                      case "White":view.setBackgroundResource(R.color.white);
                        break;
                      default:break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mauth.addAuthStateListener(mAuthListener);
        refreshList();
    }

    public void refreshList() {
        myDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                memories.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    MemoryItem memory = postSnapshot.getValue(MemoryItem.class);
                    memories.add(memory);
                }
                if (fontType.equals("")) {
                    MemoryList memoryListAdapter = new MemoryList(newMemory.this, memories);
                    listView.setAdapter(memoryListAdapter);
                }
                else {
                    MemoryList memoryListAdapter = new MemoryList(newMemory.this, memories, fontType);
                    listView.setAdapter(memoryListAdapter);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
                        Intent login = new Intent(newMemory.this, Welcome.class);
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
    public void showAlert2(View view) {
        final AlertDialog.Builder erasetAlert = new AlertDialog.Builder(this);
        erasetAlert.setMessage("Are you sure you want to erase all?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int integer) {
                        dialogInterface.dismiss();
                        myDatabase.setValue(null);


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int integer) {
                        dialogInterface.dismiss();
                    }
                })
                .setTitle("Warning!");
        erasetAlert.show();
    }

    public void logoutUser() {
        mauth.signOut();
    }

    public void writeToFirebase(View view) {
        String editTextString = testText.getText().toString();
        if (!editTextString.equals("")) {
            String id = myDatabase.push().getKey();
            MemoryItem memory = new MemoryItem(editTextString);
            myDatabase.child(id).setValue(memory);
            testText.setText("");
        }
        else {
            toastMessage("No information provided!");
        }
    }

    private void toastMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}

