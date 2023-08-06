package com.example.thevisualbook;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Family_Friends_InviteActivity extends AppCompatActivity {

    private EditText contactInput;
    private Button emailInvite;
    private Button smsInvite;
    private String gift_extractor;
    private DatabaseReference inviteDBReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_friends_invite);

        contactInput = (EditText) findViewById(R.id.recipient_ccontact);
        emailInvite = (Button) findViewById(R.id.inviteEmail);
        smsInvite = (Button) findViewById(R.id.inviteSMS);

        gift_extractor = getIntent().getExtras().get("gift_key").toString();

        inviteDBReference = FirebaseDatabase.getInstance().getReference().child("GiftCollection").child(gift_extractor).child("Invitations");

        emailInvite.setVisibility(View.INVISIBLE);
        smsInvite.setVisibility(View.VISIBLE);

        if (contactInput != null && contactInput.length() >= 10){
           // emailInvite.setVisibility(View.VISIBLE);
            smsInvite.setVisibility(View.VISIBLE);
        }




        smsInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                        // sendSMS code here;

                        final String recipientAddress = contactInput.getText().toString().trim();

                        if (TextUtils.isEmpty(recipientAddress)) {
                            Toast.makeText(Family_Friends_InviteActivity.this, "Please enter the recipient's contact", Toast.LENGTH_SHORT).show();
                        }else {

                            String message = "Hey, I'm inviting you over to join our refreshing moment with the Visual Book. You'll need this code " +gift_extractor+ " to be able to join the fun.";

                            try{
                                SmsManager smsManager = SmsManager.getDefault();
                                smsManager.sendTextMessage(recipientAddress, null, message, null, null);

                                Toast.makeText(Family_Friends_InviteActivity.this, "Message Sent "+recipientAddress+".", Toast.LENGTH_SHORT).show();

                                inviteDBReference.child("invite_contact").setValue(recipientAddress);

                                Intent mIntent = new Intent(Family_Friends_InviteActivity.this, GiftingActivity7.class);
                                mIntent.putExtra("gift_key", gift_extractor);
                                startActivity(mIntent);

                            }catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(Family_Friends_InviteActivity.this, "Message not Sent", Toast.LENGTH_SHORT).show();

                            }

                        }

                    } else {
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
                    }
                }




            }
        });



    }


    public void sendEmailInvite(){

    }
}