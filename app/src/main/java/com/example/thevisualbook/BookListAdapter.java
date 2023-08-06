package com.example.thevisualbook;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.MyViewHolder> {

   Context context;
   ArrayList<bookListVw> list;
   SelectListener listener;

   public BookListAdapter(Activity context, ArrayList<bookListVw> list, SelectListener listener){
      this.context = context;
      this.list = list;
      this.listener = listener;
   }

   @NonNull
   @Override
   public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View v = LayoutInflater.from(context).inflate(R.layout.book_item, parent, false);
      return new MyViewHolder(v);
   }

   @Override
   public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      bookListVw bookListVW = list.get(position);
      holder.booknameTxtVW.setText(bookListVW.getBook_name());

      holder.cardView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            listener.onItemClicked(list.get(position));
         }
      });
   }

   @Override
   public int getItemCount() {
      return list.size();
   }


   public static class MyViewHolder extends RecyclerView.ViewHolder{

      TextView booknameTxtVW;
      CardView cardView;

      public MyViewHolder(@NonNull View itemView){
         super(itemView);

         booknameTxtVW = itemView.findViewById(R.id.nameReceipient11);
         cardView = itemView.findViewById(R.id.main_container);
      }
   }

}
