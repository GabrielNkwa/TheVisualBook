package com.example.thevisualbook;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class tabpagerAdapter extends FragmentStatePagerAdapter {

   String[] tabarray = new String[]{"ACTIVITIES","PHOTOS","VIDEOS"};

   public tabpagerAdapter(@NonNull FragmentManager fm, int behavior) {
      super(fm, behavior);
   }

   public CharSequence getPageTitle(int position){
      return super.getPageTitle(position);
   }

   @NonNull
   @Override
   public Fragment getItem(int position) {

      switch (position){
         case 0:
            TabFragment1 tabFragment1 = new TabFragment1();
            return tabFragment1;
         case 1:
          //  TabFragment2 tabFragment2 = new TabFragment2();
            //return tabFragment2;
         case 2:
          //  TabFragment3 tabFragment3 = new TabFragment3();
           // return tabFragment3;

      }

      return null;
   }

   @Override
   public int getCount() {
      return 3;
   }
}
