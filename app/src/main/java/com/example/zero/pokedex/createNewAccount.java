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
    private FirebaseAuth mauth;
    private FirebaseAuth.AuthStateListener mauthListener;
    EditText emailField;
    EditText passwordField;
    EditText confirmPasswordField;
    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);
        emailField = (EditText) findViewById(R.id.emailText2);
        passwordField = (EditText) findViewById(R.id.passwordText2);
        confirmPasswordField = (EditText) findViewById(R.id.confirmPasswordText);
        mauth = FirebaseAuth.getInstance();
        mauthListener = new FirebaseAuth.AuthStateListener() {
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
    }

    @Override
    public void onStart() {
        super.onStart();
        mauth.addAuthStateListener(mauthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mauthListener != null) {
            mauth.removeAuthStateListener(mauthListener);
        }
    }

    public void createAccount(View view) {
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        String confirmPassword = confirmPasswordField.getText().toString();

        if (checkTextboxForEmpty(email, password, confirmPassword)) {
            if (checkPasswordLength(password)) {
                if (password.equals(confirmPassword)) {
                    mauth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(createNewAccount.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
                                        showAlert("Invalid Username / Password!");
                                    } else {
                                        Intent questionaire = new Intent(createNewAccount.this, Questionaire.class);
                                        startActivity(questionaire);
                                    }
                                }
                            });
                } else {
                    showAlert("Passwords do not match, try again!");
                    clearPasswordTextBoxes();

                }
            } else {
                showAlert("Invalid password! Needs to be 6 or more characters.");
                clearPasswordTextBoxes();
            }

        } else {
            showAlert("You did not fill in all the required fields!");
            clearPasswordTextBoxes();
        }
    }

    public void showAlert(String message) {
        final AlertDialog.Builder logoutAlert = new AlertDialog.Builder(this);
        logoutAlert.setMessage(message)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int integer) {
                        dialogInterface.dismiss();
                    }
                })
                .setTitle("Warning!");
        logoutAlert.show();
    }

    private void toastMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    public void returnToLogin(View view) {
        Intent backToLogin = new Intent(createNewAccount.this, loginFirebase.class);
        startActivity(backToLogin);

    }

    public boolean checkPasswordLength(String password) {
        if (password.length() > 5) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkTextboxForEmpty(String email, String password, String confirmPassword) {
        if (!email.equals("") && !password.equals("") && !confirmPassword.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public void clearPasswordTextBoxes() {
        passwordField.setText("");
        confirmPasswordField.setText("");
        //emailField.setText("");

    }
}
