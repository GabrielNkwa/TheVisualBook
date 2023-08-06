package com.example.thevisualbook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class GiftingActivity4 extends AppCompatActivity {

    private TextView textView;
    private Button shareCodeButton;
    DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gifting4);

        textView = (TextView) findViewById(R.id.scan_result);
        shareCodeButton = (Button) findViewById(R.id.send_code);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("GiftCollection");

        IntentIntegrator intentIntegrator = new IntentIntegrator(GiftingActivity4.this);
        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.setPrompt("Scan the QR Code on the Visual Book");
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        intentIntegrator.initiateScan();


        shareCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!textView.getText().toString().isEmpty()){

                    databaseReference.child(textView.getText().toString()).child("Gift_Code").setValue(textView.getText().toString());
                    databaseReference.child(textView.getText().toString()).child("AuthorID").setValue(firebaseAuth.getCurrentUser().getUid().toString());

                    DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference("BookCollection").child(firebaseAuth.getCurrentUser().getUid().toString()).child("book_Code");
                    dataRef.setValue(textView.getText().toString());

                    Intent nextActivity = new Intent(GiftingActivity4.this, QRBookNamingActivity.class);
                    nextActivity.putExtra("bookAuthor_QRcode", (textView.getText().toString()));
                    startActivity(nextActivity);

                }else{
                    Toast.makeText(GiftingActivity4.this, "Kindly scan your Visual Book QR Code", Toast.LENGTH_SHORT).show();
                }



                /*
                Intent intent = new Intent();
                intent.putExtra(Intent.EXTRA_STREAM, textView.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT, textView.getText().toString() );
                intent.putExtra(Intent.EXTRA_SUBJECT, "Image Subject");
                intent.setType("image/*");
                startActivity(Intent.createChooser(intent, "Share Via"));
                 */
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode, data);

        if (intentResult != null){
            String contents = intentResult.getContents();
            if (contents != null) {
                textView.setText(intentResult.getContents());
            }
        } else{
            super.onActivityResult(requestCode, resultCode, data);
        }

    }


}