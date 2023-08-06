package com.example.thevisualbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GiftingActivity7 extends AppCompatActivity {

    public Button Button1;
    public Button Button2;
    public Button Button3;
    public Button Button4;
    public Button Button5;
    private DatabaseReference confirmDB_content;
    private FirebaseAuth firebaseAuth;

    private String gift_extractor, gift_recipient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gifting7);

        Button1 = (Button) findViewById(R.id.bookAnswer_1);
        Button2 = (Button) findViewById(R.id.bookAnswer_2);
        Button3 = (Button) findViewById(R.id.bookAnswer_3);
        Button4 = (Button) findViewById(R.id.bookAnswer_4);
        Button5 = (Button) findViewById(R.id.bookAnswer_5);

        gift_extractor = getIntent().getExtras().get("gift_key").toString();
      //  gift_recipient = getIntent().getExtras().get("gift_recipient").toString();

        firebaseAuth = FirebaseAuth.getInstance();

        confirmDB_content = FirebaseDatabase.getInstance().getReference().child("GiftCollection").child(gift_extractor).child("Message").child("");
        DatabaseReference mDataImage = FirebaseDatabase.getInstance().getReference("GiftCollection").child(gift_extractor).child("Images");
        DatabaseReference mSentInvitations = FirebaseDatabase.getInstance().getReference().child("GiftCollection").child(gift_extractor).child("Invitations").child("invite_contact").child("");

        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(GiftingActivity7.this, GiftMessageActivity.class);
                mIntent.putExtra("gift_key", gift_extractor);
                startActivity(mIntent);
            }
        });

        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(GiftingActivity7.this, Family_Friends_InviteActivity.class);
                mIntent.putExtra("gift_key", gift_extractor);
                startActivity(mIntent);
            }
        });



        Button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             /*
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 100);

              */
                Intent mIntent = new Intent(GiftingActivity7.this, AddImageGiftActivity.class);
                mIntent.putExtra("gift_key", gift_extractor);
                startActivity(mIntent);
            }
        });

        Button4.setVisibility(View.INVISIBLE);

        Button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                DatabaseReference createGift = FirebaseDatabase.getInstance().getReference().child("UserAccounts").child(firebaseAuth.getCurrentUser().getUid());
                //createGift.child("Gifts").push().child("book_name").setValue(gift_recipient);
                //createGift.child("Gifts").push().child("book_code").setValue(gift_extractor);

                createGift.child("Gifts").push().child("book_name").setValue(gift_extractor);
                
                Intent mIntentFinish = new Intent(GiftingActivity7.this, ButtomNavActivity.class);
                mIntentFinish.putExtra("gift_key", gift_extractor);
                startActivity(mIntentFinish);
            }
        });

        confirmDB_content.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String rCode3 = snapshot.getValue(String.class);

                if (rCode3 != null){
                   // Button1.setVisibility(View.INVISIBLE);
                    Button1.setBackgroundColor(R.drawable.baseline_check_circle_24);
                }else {

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        mDataImage.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String returnCode = snapshot.child("uri").getValue(String.class);
                if (returnCode != null){
                    Button3.setBackgroundColor(R.drawable.baseline_check_circle_24);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        mSentInvitations.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String returnCode = snapshot.getValue(String.class);
                if (returnCode != null){
                    Button2.setBackgroundColor(R.drawable.baseline_check_circle_24);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}