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

import com.example.thevisualbook.ui.GiftingActivity1;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    private Button createUserButton;
    private EditText user_name;
    private EditText user_password;
    private EditText confirm_userPassword;
    String userAccount_email;
    private TextView emailTextView, signinPage;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseRegistry;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        userAccount_email = getIntent().getExtras().get("user_email").toString();
        emailTextView = (TextView) findViewById(R.id.input1);
        emailTextView.setText(userAccount_email);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseRegistry = FirebaseDatabase.getInstance().getReference().child("UserAccounts");

        progressDialog = new ProgressDialog(this);

        signinPage = (TextView) findViewById(R.id.input01);
        signinPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentNeext = new Intent(RegistrationActivity.this, SigninActivity2.class);
                intentNeext.putExtra("gift_key", userAccount_email);
                startActivity(intentNeext);
            }
        });

        user_name = (EditText) findViewById(R.id.input2);
        user_password = (EditText) findViewById(R.id.input3);
        confirm_userPassword = (EditText) findViewById(R.id.input4);

        createUserButton = (Button) findViewById(R.id.bookAnswer_2);
        createUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent createAccount = new Intent(RegistrationActivity.this, BaseHomeActivity.class);
               // startActivity(createAccount);
                createAccount();
            }
        });
    }


    private void createAccount(){
        final String user_Name = user_name.getText().toString().trim();
        final String user_Email = emailTextView.getText().toString().trim();
        final String user_Password = user_password.getText().toString().trim();
        final String confirm_uPassword = confirm_userPassword.getText().toString().trim();

        if (TextUtils.isEmpty(user_Name)){
            //email is empty
            Toast.makeText(this, "Please enter your Name", Toast.LENGTH_SHORT).show();
            // stop the function from executing further
            return;
        }

        if (TextUtils.isEmpty(user_Password) ) {
            //password is empty
            Toast.makeText(this, "Please enter your Password", Toast.LENGTH_SHORT).show();
            // stop the function from executinf further
            return;
        }

        if (TextUtils.isEmpty(confirm_uPassword) || !user_Password.equals(confirm_uPassword)) {
            Toast.makeText(this, "Please confirm your password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!TextUtils.isEmpty(user_Name) && !TextUtils.isEmpty(user_Password)) {
            // if validation are okay
            // will first show progressbar
            progressDialog.setMessage("Registering, Please wait..");
            progressDialog.show();

            firebaseAuth.createUserWithEmailAndPassword(user_Email, user_Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegistrationActivity.this, "Account Successfully Created", Toast.LENGTH_SHORT).show();

                        final String user_id = firebaseAuth.getCurrentUser().getUid();
                        final DatabaseReference current_user_db = databaseRegistry.child(user_id);

                        //  current_user_db.child("UserID").setValue(user_id);
                        current_user_db.child("User_Name").setValue(user_Name);
                        current_user_db.child("User_Email").setValue(user_Email);
                        current_user_db.child("Password").setValue(user_Password);

                        progressDialog.dismiss();

                        Intent mainIntent = new Intent(RegistrationActivity.this, ButtomNavActivity.class);
                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(mainIntent);

                    } else {
                        Toast.makeText(RegistrationActivity.this, "Failed to register, please try again", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            });


        }


    }

}