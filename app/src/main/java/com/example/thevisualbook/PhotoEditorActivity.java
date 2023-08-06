package com.example.thevisualbook;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dsphotoeditor.sdk.activity.DsPhotoEditorActivity;
import com.dsphotoeditor.sdk.utils.DsPhotoEditorConstants;
import com.google.firebase.database.collection.LLRBNode;

import java.io.ByteArrayOutputStream;

public class PhotoEditorActivity extends AppCompatActivity {

    //ActivityMainBinding binding;
    int IMAGE_REQUEST_CODE = 45;
    int CAMERA_REQUEST_CODE = 14;
    int RESULT_CODE = 200;
    ImageView editBtn,imageView;

    private Button selectImage;
    private ImageView displayPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_editor);

        selectImage = (Button) findViewById(R.id.chooseImg);
        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 100);
            }
        });

        displayPic = (ImageView) findViewById(R.id.imageVshow);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

/*
        if(requestCode == IMAGE_REQUEST_CODE){
            if(data.getData() != null){
                Uri filePath = data.getData();
                Intent dsPhotoEditorIntent = new Intent(PhotoEditorActivity.this, DsPhotoEditorActivity.class);
                dsPhotoEditorIntent.setData(filePath);
                dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY, "Edited Images");
                dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_TOOL_BAR_BACKGROUND_COLOR, Color.parseColor("#FFCBA4"));
                dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_MAIN_BACKGROUND_COLOR, Color.parseColor("#ffffff"));
                startActivityForResult(dsPhotoEditorIntent, RESULT_CODE);
            }
        }

        if(requestCode == RESULT_CODE){
            if(data.getData() != null) {
                Uri filePath1 = data.getData();
                Intent intent = new Intent(PhotoEditorActivity.this, PhotoEditorActivity.class);
                intent.setData(filePath1);
               // intent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY, "Edited Images");
                startActivityForResult(intent, 100);
                displayPic.setImageURI(filePath1);
                Toast.makeText(this, "Image saved in gallery", Toast.LENGTH_LONG).show();
            }
        }

        if(requestCode == CAMERA_REQUEST_CODE) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            Uri uri = getImageUri(photo);
            Intent dsPhotoEditorIntent = new Intent(PhotoEditorActivity.this, DsPhotoEditorActivity.class);
            dsPhotoEditorIntent.setData(uri);
            dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY, "Edited Images");
            dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_TOOL_BAR_BACKGROUND_COLOR, Color.parseColor("#FFCBA4"));
            dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_MAIN_BACKGROUND_COLOR, Color.parseColor("#ffffff"));
            startActivityForResult(dsPhotoEditorIntent, RESULT_CODE);
        }
*/


        if (resultCode == RESULT_OK){
            Uri uri = data.getData();
            switch (requestCode){
                case 100:
                        Intent dsPhotoEditorIntent = new Intent(PhotoEditorActivity.this, DsPhotoEditorActivity.class);
                        dsPhotoEditorIntent.setData(uri);
                        dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY, "Edited Images");
                        dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_TOOL_BAR_BACKGROUND_COLOR, Color.parseColor("#FFCBA4"));
                        dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_MAIN_BACKGROUND_COLOR, Color.parseColor("#ffffff"));
                        startActivity(dsPhotoEditorIntent);
                        break;

                case 101:
                    displayPic.setImageURI(uri);
                    Toast.makeText(this, "Image saved in gallery", Toast.LENGTH_LONG).show();
                    break;
            }
        }


    }

    public Uri getImageUri(Bitmap bitmap) {
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100, arrayOutputStream);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap,"Title", null);
        return Uri.parse(path);
    }




}