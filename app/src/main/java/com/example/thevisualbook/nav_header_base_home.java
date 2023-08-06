package com.example.thevisualbook;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class nav_header_base_home extends AppCompatActivity {

   private FirebaseAuth mAuth;
   private DatabaseReference databaseReference;
   private TextView uname;
   private TextView uemail;
   private String theUserID, theUserEmail;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.nav_header_base_home);

      uname = (TextView) findViewById(R.id.user_name);
      uemail = (TextView) findViewById(R.id.textView);

      mAuth = FirebaseAuth.getInstance();
      theUserID = mAuth.getCurrentUser().getUid();
      theUserEmail = mAuth.getCurrentUser().getEmail();

      uemail.setText(theUserEmail);



      databaseReference = FirebaseDatabase.getInstance().getReference().child("UserAccounts").child(mAuth.getCurrentUser().getUid());
      databaseReference.addValueEventListener(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot snapshot) {
            String name = snapshot.child("User_Name").getValue(String.class);
            uname.setText(name);
         }

         @Override
         public void onCancelled(@NonNull DatabaseError error) {

         }
      });


   }
}
