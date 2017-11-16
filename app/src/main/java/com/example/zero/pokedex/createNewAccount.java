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

public class createNewAccount extends AppCompatActivity {

    private static final String TAG = "createNewAccount";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    EditText emailField, passwordField, confirmPasswordField;
    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);
        emailField = (EditText) findViewById(R.id.emailText2);
        passwordField = (EditText) findViewById(R.id.passwordText2);
        confirmPasswordField = (EditText) findViewById(R.id.confirmPasswordText);
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

    public void createAccount(View view)
    {
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        String confirmPassword = confirmPasswordField.getText().toString();

        if(!email.equals("") && !password.equals(""))
        {
            if(password.length() > 5)
            {
                if(password.equals(confirmPassword)) {
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(createNewAccount.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                showAlert("Invalid Username / Password!");
                            } else {
                                Intent Questionaire = new Intent(createNewAccount.this, Questionaire.class);
                                startActivity(Questionaire);
                            }
                        }
                    });
                }
                else
                {
                    showAlert("Passwords do not match, try again!");
                    passwordField.setText("");
                    confirmPasswordField.setText("");

                }
            }
            else {
                showAlert("Invalid password! Needs to be 6 or more characters.");
                passwordField.setText("");
                confirmPasswordField.setText("");
            }

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

    private void toastMessage(String message)
    {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    public void returnToLogin(View view)
    {
        Intent backToLogin = new Intent(createNewAccount.this, LoginFirebase.class);
        startActivity(backToLogin);

    }
}
