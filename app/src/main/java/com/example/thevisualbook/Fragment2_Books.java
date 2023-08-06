package com.example.thevisualbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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


public class Fragment2_Books extends Fragment implements SelectListener {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private Button buttonAddBook;
    RecyclerView recyclerView1;
    BookListAdapter adapter;
    ArrayList<bookListVw> list;
    Activity context;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        firebaseAuth = FirebaseAuth.getInstance();
        String userId = firebaseAuth.getCurrentUser().getUid().toString();

        databaseReference = FirebaseDatabase.getInstance().getReference("UserAccounts").child(userId).child("Gifts");
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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = getActivity();
        View view = inflater.inflate(R.layout.fragment_fragment2__books, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        recyclerView1 = (RecyclerView) view.findViewById(R.id.bookListVw);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(context));

        list = new ArrayList<>();
        adapter = new BookListAdapter(context, list, this);
        recyclerView1.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClicked(bookListVw bkListVw) {

        Toast.makeText(context, bkListVw.getBook_name(), Toast.LENGTH_SHORT).show();

        Intent bookIntent = new Intent(context, ClaimedImagesActivity.class);
        bookIntent.putExtra("gift_key", bkListVw.getBook_name());
        startActivity(bookIntent);

    }


    public void onStart() {
        super.onStart();

        buttonAddBook = (Button) context.findViewById(R.id.addBookAction);
        buttonAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentGiftSetup = new Intent(context, GiftingActivity3.class);
                startActivity(intentGiftSetup);
            }
        });

    }
}