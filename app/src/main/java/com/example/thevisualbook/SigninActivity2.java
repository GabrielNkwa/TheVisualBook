package com.example.thevisualbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class SigninActivity2 extends AppCompatActivity {

    private Button loginSuccess;
    private EditText password;
    private TextView emailTextView, forgetPasssword;
    String userAccount_email;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference validAccount;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin2);

        userAccount_email = getIntent().getExtras().get("user_email").toString();
        emailTextView = (TextView) findViewById(R.id.action_introduction23);
        emailTextView.setText(userAccount_email);

        password = (EditText) findViewById(R.id.input1);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        forgetPasssword = (TextView) findViewById(R.id.forgetPsWrd);
        forgetPasssword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SigninActivity2.this, ForgetPasswordActivity.class));
            }
        });


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    startActivity(new Intent(SigninActivity2.this, ButtomNavActivity.class));
                }
            }
        };



        loginSuccess = (Button) findViewById(R.id.bookAnswer_3);
        loginSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Intent homeIntent = new Intent(SigninActivity2.this, BaseHomeActivity.class);
              //  startActivity(homeIntent);
                userLogin();
             }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();

        firebaseAuth.addAuthStateListener(mAuthListener);
    }


    private void userLogin() {
        String Usrname = emailTextView.getText().toString();
        String Usrpsswrd = password.getText().toString();

        //check if Username and Password is empty
        if(TextUtils.isEmpty(Usrname) || TextUtils.isEmpty(Usrpsswrd)){
            Toast.makeText(this, "Please enter your correct password", Toast.LENGTH_LONG).show();
        }  else {
            //if username and password are not empty
            //display a progress dialog
            progressDialog.setMessage("Please wait");
            progressDialog.show();

            firebaseAuth.signInWithEmailAndPassword(Usrname, Usrpsswrd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();

                    if (!task.isSuccessful()) {
                        Toast.makeText(SigninActivity2.this, "Authentication Error", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

}