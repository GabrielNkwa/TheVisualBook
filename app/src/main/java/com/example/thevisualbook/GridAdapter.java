package com.example.thevisualbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {

   Context context;
   String[] flowerName;
   int[] image;

   LayoutInflater inflater;

   public GridAdapter(Context context, String[] flowerName, int[] image) {
      this.context = context;
      this.flowerName = flowerName;
      this.image = image;
   }

   @Override
   public int getCount() {
      return flowerName.length;
   }

   @Override
   public Object getItem(int i) {
      return null;
   }

   @Override
   public long getItemId(int i) {
      return 0;
   }

   @Override
   public View getView(int position, View converterView, ViewGroup viewGroup) {

      if (inflater == null)
         inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

      if (converterView == null){
         converterView = inflater.inflate(R.layout.images_grid_item, null);
      }

      ImageView imageView = converterView.findViewById(R.id.image_grid);
      TextView textView = converterView.findViewById(R.id.image_name);

      imageView.setImageResource(image[position]);
      textView.setText(flowerName[position]);

      return converterView;
   }
}
