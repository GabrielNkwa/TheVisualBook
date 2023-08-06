package com.example.thevisualbook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class ClaimedVideosActivity extends AppCompatActivity {

    float x1, x2, y1, y2;
    private String bookCode;
    private TextView txtVwTAG;
    private RecyclerView recyclerViewHere;
    private ArrayList<FileUploader> list;
    private MyVideoAdapter adapter;
    private DatabaseReference root;
    public static final String FB_STORAGE_PATH = "Videos/";
    private DatabaseReference mDBReference;
    private StorageReference mSReference;
    private Uri mImageUri;
    private int PICK_Image_Code = 100;
    private Button add_photo, viewPictures;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claimed_videos);

        bookCode = getIntent().getExtras().get("gift_key").toString();

        txtVwTAG = (TextView) findViewById(R.id.tag);
        txtVwTAG.setText(bookCode + " Visual Book's Videos");

        root = FirebaseDatabase.getInstance().getReference("GiftCollection").child(bookCode).child("Videos");

        mDBReference = FirebaseDatabase.getInstance().getReference("GiftCollection").child(bookCode).child("Videos");
        mSReference = FirebaseStorage.getInstance().getReference();

        add_photo = (Button) findViewById(R.id.addMediabutton1);
        add_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addImageToAlbum();
            }
        });

        recyclerViewHere = findViewById(R.id.recyclerViewFront);
        recyclerViewHere.setHasFixedSize(true);
        recyclerViewHere.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new MyVideoAdapter(this, list);
        recyclerViewHere.setAdapter(adapter);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    FileUploader model = dataSnapshot.getValue(FileUploader.class);
                    list.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        viewPictures = (Button) findViewById(R.id.pic_button);
        viewPictures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClaimedVideosActivity.this, ClaimedImagesActivity.class);
                intent.putExtra("gift_key", bookCode);
                startActivity(intent);

            }
        });



    }

    private void addImageToAlbum() {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select an image file"), 100);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_Image_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {
            mImageUri = data.getData();

            uploadImage();
        }
    }


    private void uploadImage(){
        if (mImageUri != null) {

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // get the file storage reference
            StorageReference fileRef = mSReference.child(FB_STORAGE_PATH).child(bookCode).child(mImageUri.getLastPathSegment());

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


    public boolean onTouchEvent(MotionEvent touchEvent){
        switch (touchEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;

            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();

                if (x1 < x2) {

                    //Intent intent = new Intent(ClaimedVideosActivity.this, ClaimedImagesActivity.class);
                    //intent.putExtra("gift_key", bookCode);
                    //startActivity(intent);

                } else if (x1 > x2) {

                  //  Intent intent = new Intent(ClaimedVideosActivity.this, ClaimedVideosActivity.class);
                  //  startActivity(intent);

                }
        }

        return false;
    }



}