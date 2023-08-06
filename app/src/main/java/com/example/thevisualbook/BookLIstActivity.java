package com.example.thevisualbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class BookLIstActivity extends AppCompatActivity implements SelectListener{

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private Button buttonAddBook;
    RecyclerView recyclerView1;
    BookListAdapter adapter;
    ArrayList<bookListVw> list;

    private String bookName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        firebaseAuth = FirebaseAuth.getInstance();
        String userId = firebaseAuth.getCurrentUser().getUid().toString();
        databaseReference = FirebaseDatabase.getInstance().getReference("UserAccounts").child(userId).child("Gifts");
        storageReference = FirebaseStorage.getInstance().getReference();

        //bookName = getIntent().getExtras().get("gift_key").toString();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("GiftCollection");
        buttonAddBook = (Button) findViewById(R.id.addBookAction);

        recyclerView1 = findViewById(R.id.bookListVw);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new BookListAdapter(this, list, this);
        recyclerView1.setAdapter(adapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    bookListVw books = dataSnapshot.getValue(bookListVw.class);
                    list.add(books);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        buttonAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookSetupOptions();
            }
        });

    }

    private void BookSetupOptions() {
        AlertDialog.Builder dialogueBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.book_setup_options, null);
        dialogueBuilder.setView(dialogView);
        dialogueBuilder.setTitle("Choose Setup Type");

        final Button bookSetup = (Button) dialogView.findViewById(R.id.bookSetupOption1);
        final Button giftSetup = (Button) dialogView.findViewById(R.id.bookSetupOption2);


        final AlertDialog alertDilog = dialogueBuilder.create();
        alertDilog.show();

        bookSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBookSetup = new Intent(BookLIstActivity.this, GiftingActivity2.class);
                startActivity(intentBookSetup);
            }
        });

        giftSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentGiftSetup = new Intent(BookLIstActivity.this, GiftingActivity3.class);
                startActivity(intentGiftSetup);
            }
        });

    }

    @Override
    public void onItemClicked(bookListVw bkListVw) {
      //  Toast.makeText(this, bkListVw.getBook_name(), Toast.LENGTH_SHORT).show();

        Intent bookIntent = new Intent(BookLIstActivity.this, ClaimedImagesActivity.class);
        bookIntent.putExtra("gift_key", bkListVw.getBook_name());
        startActivity(bookIntent);
    }
}