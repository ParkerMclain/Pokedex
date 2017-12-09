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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.R.id.edit;

public class Questionaire extends AppCompatActivity {

    EditText editColor;
    EditText editMovie;
    EditText editAnimal;
    EditText editBook;
    private DatabaseReference myDatabase;
    private FirebaseAuth mauth;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionaire);

        mauth = FirebaseAuth.getInstance();
        FirebaseUser user = mauth.getCurrentUser();
        final String userId = user.getUid();
        myDatabase = FirebaseDatabase.getInstance().getReference(userId);



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



                        String color = editColor.getText().toString();
                        String movie = editMovie.getText().toString();
                        String animal = editAnimal.getText().toString();
                        String book = editBook.getText().toString();

                            if (!color.equals("")) {
                            String id = myDatabase.push().getKey();
                            MemoryItem memory = new MemoryItem(color);
                            myDatabase.child(id).setValue(memory);
                        }

                            if (!movie.equals("")) {
                            String id = myDatabase.push().getKey();
                            MemoryItem memory = new MemoryItem(movie);
                            myDatabase.child(id).setValue(memory);
                        }

                            if (!animal.equals("")) {
                            String id = myDatabase.push().getKey();
                            MemoryItem memory = new MemoryItem(animal);
                            myDatabase.child(id).setValue(memory);
                        }

                            if (!book.equals("")) {
                            String id = myDatabase.push().getKey();
                            MemoryItem memory = new MemoryItem(book);
                            myDatabase.child(id).setValue(memory);
                        }

                        Intent login = new Intent(Questionaire.this, newMemory.class);
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
