package com.example.thevisualbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private Button signinButton1;
    private Button createAccountButton2;
    private EditText emailEditText;
    private DatabaseReference mdataReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signinButton1 = (Button) findViewById(R.id.bookAnswer_2);
        createAccountButton2 = (Button) findViewById(R.id.bookAnswer_3);
        emailEditText = (EditText) findViewById(R.id.input1);

        firebaseAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    startActivity(new Intent(LoginActivity.this, ButtomNavActivity.class));
                }
            }
        };

        //signinButton1.setVisibility(View.INVISIBLE);
        //createAccountButton2.setVisibility(View.INVISIBLE);
/*
        mdataReference = FirebaseDatabase.getInstance().getReference().child("UserAccounts");
        mdataReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds1 : snapshot.getChildren()) {

                    String user_confirmation = ds1.child("user_email").getValue(String.class);


                    signinButton1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View view, boolean b) {

                            if (user_confirmation != emailEditText.getText().toString()){
                                createAccountButton2.setVisibility(View.VISIBLE);
                            }else {
                                signinButton1.setVisibility(View.VISIBLE);
                            }

                        }
                    });








                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
*/





        signinButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(emailEditText.getText().toString())){
                    //email is empty
                    Toast.makeText(LoginActivity.this, "Please enter your email address", Toast.LENGTH_SHORT).show();
                    // stop the function from executing further
                  //  return;
                } else {
                    Intent loginUser = new Intent(LoginActivity.this, SigninActivity2.class);
                    loginUser.putExtra("user_email", (emailEditText.getText().toString()));
                    startActivity(loginUser);
                }




            }
        });


        createAccountButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(emailEditText.getText().toString())){
                    //email is empty
                    Toast.makeText(LoginActivity.this, "Please enter your Email Address", Toast.LENGTH_SHORT).show();
                    // stop the function from executing further
                    //  return;
                } else {
                    Intent createUser = new Intent(LoginActivity.this, RegistrationActivity.class);
                    createUser.putExtra("user_email", (emailEditText.getText().toString()));
                    startActivity(createUser);
                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        firebaseAuth.addAuthStateListener(mAuthListener);
    }

}