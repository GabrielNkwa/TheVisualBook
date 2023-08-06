package com.example.thevisualbook;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Fragment3_settings extends Fragment {

    private TextView nametextview, emailtextview, unpairDevice;
    private ImageView insta, twitter, tiktok, youTub;
    private TextView signoutButton;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private String userID;
    Activity context;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid().toString();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("UserAccounts").child(userID);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String valueName = snapshot.child("User_Name").getValue(String.class);
                nametextview.setText(valueName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String valueName = snapshot.child("User_Email").getValue(String.class);
                emailtextview.setText(valueName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }


    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = getActivity();
        View view = inflater.inflate(R.layout.fragment_fragment3_settings, container, false);

        nametextview = (TextView) view.findViewById(R.id.namePerson);
        emailtextview = (TextView) view.findViewById(R.id.emailPerson);
       // signoutButton = (Button) view.findViewById(R.id.signOut);

        return view;
    }

    public void onStart() {
        super.onStart();


        insta = (ImageView) context.findViewById(R.id.instaBttn);
        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.instagram.com/thevisualbook/");
            }
        });

        twitter = (ImageView) context.findViewById(R.id.twttrBttn);
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://pin.it/7eBDrlr");
            }
        });

        tiktok = (ImageView) context.findViewById(R.id.tiktikBttn);
        tiktok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.tiktok.com/@thevisualbook/");
            }
        });

        youTub = (ImageView) context.findViewById(R.id.youTBttn);
        youTub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.youtube.com/@the.visualbook");
            }
        });



        unpairDevice = (TextView) context.findViewById(R.id.upairDeviceWipe);
        unpairDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.setValue(null);

                DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("BookCollection").child(firebaseAuth.getCurrentUser().getUid().toString()).child("");
            }
        });



        signoutButton = (TextView) context.findViewById(R.id.signOut);
        signoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                Intent intentClose = new Intent(context, LoginActivity.class);
                startActivity(intentClose);
            }
        });

    }

}