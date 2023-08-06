package com.example.thevisualbook;

import static com.example.thevisualbook.R.id.convoRecycler;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class TabFragment1 extends Fragment {

    RecyclerView convoRecycler1, otherRecycler2;
    GalleryAdapter adapter;
    DatabaseReference databaseReference;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);
        convoRecycler1 = view.findViewById(R.id.convoRecycler);
        otherRecycler2 = view.findViewById(R.id.otherRecycler);

        databaseReference = FirebaseDatabase.getInstance().getReference("Gallery");

        getConvoImages();
        getOtherImages();

        return view;
    }

    private void getOtherImages() {
        databaseReference.child("Convocation").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSsnapShot: snapshot.getChildren()) {
                    String data = (String) dataSsnapShot.getValue();
                    imageList.add(data);
                }

                adapter = new GalleryAdapter(getContext(), imageList);
                otherRecycler2.setLayoutManager(new GridLayoutManager(getContext(), 3));
                otherRecycler2.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getConvoImages() {

        databaseReference.child("Other Events").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSsnapShot: snapshot.getChildren()) {
                    String data = (String) dataSsnapShot.getValue();
                    imageList.add(data);
                }

                adapter = new GalleryAdapter(getContext(), imageList);
                convoRecycler1.setLayoutManager(new GridLayoutManager(getContext(), 3));
                convoRecycler1.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}