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
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//*******************************************
//************* TEST LOGIN INFO *************
//********* Username: test@test.com *********
//********* Password: test12345 *************
//*******************************************

public class LoginFirebase extends AppCompatActivity {

    private static final String TAG = "LoginFirebase";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    EditText emailField, passwordField;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_firebase);

        emailField = (EditText) findViewById(R.id.emailText2);
        passwordField = (EditText) findViewById(R.id.passwordText2);

        mAuth = FirebaseAuth.getInstance();

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
                    if(user != null)
                    {
                        toastMessage("Successfully signed out of: " + user.getEmail());
                    }
                }
                // ...
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void loginClick(View view )
    {
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        if(!email.equals("") && !password.equals(""))
        {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginFirebase.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        showAlert("Invalid Username / Password!");
                    }
                    else{

                        Intent Questionaire = new Intent(LoginFirebase.this, Questionaire.class);
                        startActivity(Questionaire);
                    }
                }
            });
        }else{
            showAlert("You did not fill in all the required fields!");
        }

    }

    public void showAlert(String message)
    {
        final AlertDialog.Builder logoutAlert = new AlertDialog.Builder(this);
        logoutAlert.setMessage(message)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setTitle("Warning!");
        logoutAlert.show();

    }

    public void createAccount(View view)
    {

        Intent createAccountPage = new Intent(LoginFirebase.this, createNewAccount.class);
        startActivity(createAccountPage);
    }

    private void toastMessage(String message)
    {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}

