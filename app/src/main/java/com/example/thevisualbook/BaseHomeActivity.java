package com.example.thevisualbook;
/*
import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.example.thevisualbook.ui.GiftingActivity1;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.os.StrictMode;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class BaseHomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Uri mImageUri;
    private int PICK_Image_Code = 100;
    private ImageView imageView;
    private Button addMediaButton, uploadMediaButton;
    private Button inviteBUtton;
    private Button membersView;
    private String Recipient_Name;
    private TextView textView;

    private Uri filePath;
    private StorageReference storageReference;
    public static final String FB_STORAGE_PATH = "Videos/";
    public static final String FB_DATABASE_PATH = "Videos";


    //New Buttons
    private Button button10, button20, buttonX, button6, button50;
    private Button bottomButton1, bottomButton2, bottomButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("GiftCollection").child("GiftCode").child("Media_Files");
        storageReference = FirebaseStorage.getInstance().getReference();


        button10 = (Button) findViewById(R.id.buttn01);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent proAct = new Intent(BaseHomeActivity.this, GiftingActivity3.class);
                startActivity(proAct);
            }
        });

        button20 = (Button) findViewById(R.id.buttn02);
        button20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent prodShop = new Intent(BaseHomeActivity.this, LinkShopActivity.class);
                startActivity(prodShop);
            }
        });


        buttonX = (Button) findViewById(R.id.buttnX1);
        buttonX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent booksIntent = new Intent(BaseHomeActivity.this, GiftingActivity2.class);
                startActivity(booksIntent);
            }
        });


        button6 = (Button) findViewById(R.id.buttn06);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent peditorIntent = new Intent(BaseHomeActivity.this, QuickHelpActivity.class);
                startActivity(peditorIntent);
            }
        });


        button50 = (Button) findViewById(R.id.buttn04);
        button50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             //   Intent fbImageIntent = new Intent(BaseHomeActivity.this, ButtomNavActivity.class);
             //   startActivity(fbImageIntent);
            }
        });


        bottomButton1 = (Button) findViewById(R.id.bttn1);
        bottomButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        bottomButton2 = (Button) findViewById(R.id.bttn2);
        bottomButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent booksIntent = new Intent(BaseHomeActivity.this, BookLIstActivity.class);
                startActivity(booksIntent);
            }
        });
        bottomButton3 = (Button) findViewById(R.id.bttn3);
        bottomButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent booksIntent = new Intent(BaseHomeActivity.this, SettingsActivity.class);
                startActivity(booksIntent);
            }
        });




/*

        imageView = (ImageView) findViewById(R.id.image_view);
        //Recipient_Name = getIntent().getExtras().get("gift_key").toString();

        membersView = (Button) findViewById(R.id.peopleMembers);
        membersView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                membersView();
            }
        });

        inviteBUtton = (Button) findViewById(R.id.invite);
        inviteBUtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inviteDialog();
            }
        });

        textView = (TextView) findViewById(R.id.usersGTitle);
       // textView.setText("Gift for" + Recipient_Name);

        addMediaButton = (Button) findViewById(R.id.addMediabutton1);
        addMediaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fileUploadOptions();
            }
        });

        uploadMediaButton = (Button) findViewById(R.id.uploadMediabutton1);
        uploadMediaButton.setVisibility(View.INVISIBLE);
        uploadMediaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadMediaToFB();

            }
        });

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("ACTIVITIES"));
        tabLayout.addTab(tabLayout.newTab().setText("PHOTOS"));
        tabLayout.addTab(tabLayout.newTab().setText("VIDEOS"));


        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        TabFragment1 activiies = new TabFragment1();
                        return activiies;
                    case 1:
                        TabFragment2 photos = new TabFragment2();
                      //  return photos;
                    case 2:
                        TabFragment3 movies = new TabFragment3();
                        return movies;
                    default:
                        return null;
                }

            }

            @Override
            public int getCount() {
                return tabLayout.getTabCount();
            }
        });


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_home:
                        Intent mapAct = new Intent(BaseHomeActivity.this, BaseHomeActivity.class);
                       // mapAct.putExtra("mission_nameH", (missionname_holder));
                        startActivity(mapAct);
                        break;

                    case R.id.nav_gallery:
                        break;

                    case R.id.nav_slideshow:
                        break;

                    case R.id.nav_tools:
                        break;

                    case R.id.nav_share:
                        break;

                    case R.id.nav_send:
                        firebaseAuth.signOut();
                        Intent loginIntent = new Intent(BaseHomeActivity.this, LoginActivity.class);
                        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(loginIntent);
                        break;

                }

                drawer.closeDrawers();

                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.base_home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_Image_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {
            mImageUri = data.getData();
            imageView.setImageURI(mImageUri);

            if(imageView != null) {
                addMediaButton.setVisibility(View.INVISIBLE);
                uploadMediaButton.setVisibility(View.VISIBLE);
            }
        }
    }


    public void inviteDialog(){
        AlertDialog.Builder dialogueBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.email_invite_dialogue, null);
        dialogueBuilder.setView(dialogView);
        dialogueBuilder.setTitle("Invitation");

        final Button buttonEmail = (Button) dialogView.findViewById(R.id.affirmbttn);
        final Button buttonNumbr = (Button) dialogView.findViewById(R.id.negativebttn);
        final EditText destinationEmail = (EditText) dialogView.findViewById(R.id.emailInput);

        final AlertDialog alertDilog = dialogueBuilder.create();
        alertDilog.show();


        buttonEmail.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View view){

                final String recipientEmail = destinationEmail.getText().toString().trim();

                if (TextUtils.isEmpty(recipientEmail)) {
                    Toast.makeText(BaseHomeActivity.this, "Please enter the recipient's email address", Toast.LENGTH_SHORT).show();
                }else {
                    //Send Email

                    final String ownAccountEmail = "system.thevisualbook@gmail.com";
                    final String ownAccntPassword = "TheVisualBook01";
                    final String theMessageContent = "Hey, I'm inviting you over to join our refreshing moment with the Visual Book.";
                    Properties props = new Properties();
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.starttls.enable", "true");
                    props.put("mail.smtp.host", "smtp.gmail.com");
                    props.put("mail.smtp.port", "587");

                    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(ownAccountEmail, ownAccntPassword);
                        }
                    });

                    try {
                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(ownAccountEmail));
                        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
                        message.setSubject("Invitation to Visual Book Moments");
                        message.setText(theMessageContent);
                        Transport.send(message);
                        Toast.makeText(getApplicationContext(), "Email Sent Successfully" + recipientEmail, Toast.LENGTH_LONG).show();

                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                       }

                  //Sending Email Ends--
                  //  Toast.makeText(BaseHomeActivity.this, "Invitation sent to" + recipientEmail, Toast.LENGTH_SHORT).show();
                }

                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }

        });




        buttonNumbr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                       // sendSMS code here;

                        final String recipientAddress = destinationEmail.getText().toString().trim();

                        if (TextUtils.isEmpty(recipientAddress)) {
                            Toast.makeText(BaseHomeActivity.this, "Please enter the recipient's phone number", Toast.LENGTH_SHORT).show();
                        }else {

                            String message = "Hey, I'm inviting you over to join our refreshing moment with the Visual Book.";

                            try{
                                SmsManager smsManager = SmsManager.getDefault();
                                smsManager.sendTextMessage(recipientAddress, null, message, null, null);

                                Toast.makeText(BaseHomeActivity.this, "Message Sent", Toast.LENGTH_SHORT).show();
                                alertDilog.dismiss();

                            }catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(BaseHomeActivity.this, "Message not Sent" + recipientAddress, Toast.LENGTH_SHORT).show();
                                alertDilog.dismiss();
                            }

                        }

                    } else {
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
                    }
                }


            }
        });

    }


    public void membersView(){
        AlertDialog.Builder dialogueBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.members_view_list, null);
        dialogueBuilder.setView(dialogView);
        dialogueBuilder.setTitle("Members");

        final Button buttonEmail = (Button) dialogView.findViewById(R.id.affirmbttn);
        final Button buttonNumbr = (Button) dialogView.findViewById(R.id.negativebttn);
        final EditText destinationEmail = (EditText) dialogView.findViewById(R.id.emailInput);

        final AlertDialog alertDilog = dialogueBuilder.create();
        alertDilog.show();
    }


    public void uploadMediaToFB(){

        if (mImageUri != null) {

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // get the file storage reference
            StorageReference fileRef = storageReference.child(FB_STORAGE_PATH).child(firebaseAuth.getCurrentUser().getUid()).child(mImageUri.getLastPathSegment());

            fileRef.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "File Uploaded", Toast.LENGTH_LONG).show();
                            FileUploader fileUploader = new FileUploader("fileTextName", taskSnapshot.getUploadSessionUri().toString());

                            //save file into firebaseDB
                            String uploadID = databaseReference.push().getKey();
                            databaseReference.child(uploadID).setValue(fileUploader);

                            imageView.setImageURI(null);
                            uploadMediaButton.setVisibility(View.INVISIBLE);
                            addMediaButton.setVisibility(View.VISIBLE);

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

    public void fileUploadOptions(){
        AlertDialog.Builder dialogueBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.file_upload_options, null);
        dialogueBuilder.setView(dialogView);
        dialogueBuilder.setTitle("Choose File Type to Upload");

        final Button buttonImage = (Button) dialogView.findViewById(R.id.bttnChseImage);
        final Button buttonVideo = (Button) dialogView.findViewById(R.id.bttnChseVideo);

        final AlertDialog alertDilog = dialogueBuilder.create();
        alertDilog.show();



        buttonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select an image file"), 100);
                alertDilog.dismiss();

            }
        });


        buttonVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select a video file"), 100);
                alertDilog.dismiss();
            }
        });


    }


}
*/
