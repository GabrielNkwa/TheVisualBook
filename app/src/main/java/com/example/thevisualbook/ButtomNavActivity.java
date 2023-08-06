package com.example.thevisualbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import androidx.fragment.app.Fragment;
import android.os.Bundle;

import com.example.thevisualbook.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ButtomNavActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    BottomNavigationView buttonNav_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttom_nav);
      //  binding = ActivityMainBinding.inflate(getLayoutInflater());
      //  setContentView(binding.getRoot());
        replaceFragment(new Fragment1_Home());

        buttonNav_view = (BottomNavigationView) findViewById(R.id.bottonNavView);
        buttonNav_view.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.home_menu:
                    replaceFragment(new Fragment1_Home());
                    break;

                case R.id.book_menu:
                    replaceFragment(new Fragment2_Books());
                    break;

                case R.id.settings_menu:
                    replaceFragment(new Fragment3_settings());
                    break;
            }

            return true;
        });

    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}