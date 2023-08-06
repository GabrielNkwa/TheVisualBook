package com.example.thevisualbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class Fragment1_Home extends Fragment {

    Activity context;
    private ImageView button10, button20, buttonX, button6, button50;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = getActivity();
        View view = inflater.inflate(R.layout.fragment_fragment1__home, container, false);

        return view;
    }

    public void onStart(){
        super.onStart();

        button10 = (ImageView) context.findViewById(R.id.buttn01);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent proAct = new Intent(context, GiftingActivity3.class);
                startActivity(proAct);
            }
        });

        button20 = (ImageView) context.findViewById(R.id.buttn02);
        button20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent prodShop = new Intent(context, LinkShopActivity.class);
                startActivity(prodShop);
            }
        });

        buttonX = (ImageView) context.findViewById(R.id.buttnX1);
        buttonX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent booksIntent = new Intent(context, GiftingActivity2.class);
                startActivity(booksIntent);
            }
        });


        button6 = (ImageView) context.findViewById(R.id.buttn06);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent peditorIntent = new Intent(context, QuickHelpActivity.class);
                startActivity(peditorIntent);
            }
        });

        button50 = (ImageView) context.findViewById(R.id.buttn04);
        button50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent peditorIntent = new Intent(context, PhotoEditorActivity.class);
                startActivity(peditorIntent);
            }
        });
    }
}