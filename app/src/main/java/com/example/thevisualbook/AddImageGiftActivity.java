package com.example.thevisualbook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddImageGiftActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button mButton;
    private Button mUploadBttn;
    private Uri mImageUri;
    private int PICK_Image_Code = 100;
    private DatabaseReference mDBReference;
    private StorageReference mSReference;
    public static final String FB_STORAGE_PATH = "Images/";
    private String gift_extractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_image_gift);

        gift_extractor = getIntent().getExtras().get("gift_key").toString();

        mDBReference = FirebaseDatabase.getInstance().getReference("GiftCollection").child(gift_extractor).child("Images");
        mSReference = FirebaseStorage.getInstance().getReference();

        imageView = (ImageView) findViewById(R.id.imageViewTag);
        mButton = (Button) findViewById(R.id.chooseImageBttn);
        mUploadBttn = (Button) findViewById(R.id.uploadImageBttn);
        mUploadBttn.setVisibility(View.INVISIBLE);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select an image file"), 100);

            }
        });

        mUploadBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uploadMediaToFB();

                Intent nextActivity = new Intent(AddImageGiftActivity.this, GiftingActivity7.class);
                nextActivity.putExtra("gift_key", gift_extractor);
                startActivity(nextActivity);
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_Image_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {
            mImageUri = data.getData();
            imageView.setImageURI(mImageUri);

            if(imageView != null) {
                mButton.setVisibility(View.INVISIBLE);
                mUploadBttn.setVisibility(View.VISIBLE);
            }
        }
    }


    public void uploadMediaToFB(){

        if (mImageUri != null) {

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // get the file storage reference
            StorageReference fileRef = mSReference.child(FB_STORAGE_PATH).child(gift_extractor).child(mImageUri.getLastPathSegment());

            fileRef.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "File Uploaded", Toast.LENGTH_LONG).show();

                                    String fileLink = task.getResult().toString();
                                    FileUploader fileUploader = new FileUploader("fileName", fileLink);

                                    String uploadID = mDBReference.push().getKey();
                                    mDBReference.child(uploadID).setValue(fileUploader);


                                    imageView.setImageURI(null);
                                    mUploadBttn.setVisibility(View.INVISIBLE);
                                    mButton.setVisibility(View.VISIBLE);

                                }
                            });



                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage(((int)progress) + "% Uploaded...");
                        }
                    });
            ;
        } else {
            // display an error toast
        }

    }


}