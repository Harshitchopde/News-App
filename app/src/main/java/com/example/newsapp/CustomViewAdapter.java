package com.example.newsapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.Model.NewsHeadlines;
import com.squareup.picasso.Picasso;

import java.util.List;

public
class CustomViewAdapter extends RecyclerView.Adapter<CustomViewAdapter.ViewHolder> {


    Context context;
    List<NewsHeadlines> headlines;
    SelectListener selectListener;
    public
    CustomViewAdapter(Context context, List<NewsHeadlines> list,SelectListener selectListener) {
        this.context = context;
        this.headlines = list;
        this.selectListener = selectListener;
    }

    @NonNull
    @Override
    public
    ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.headings_item_list,parent,false));
    }

    @Override
    public
    void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.text_source.setText(headlines.get(position).getSource().getName());
        holder.text_title.setText(headlines.get(position).getTitle());
        if (headlines.get(position).getUrlToImage()!=null){
            Picasso.get().load(headlines.get(position).getUrlToImage()).into(holder.imageView);

        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public
            void onClick(View v) {
                // hear what happened that when an item is clicked
                // it will provide the content related to it to onNewsClicked interface
               selectListener.OnNewsClicked(headlines.get(position));
            }
        });


    }

    @Override
    public
    int getItemCount() {
        return headlines.size();
    }

    public
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView text_title,text_source;
        CardView cardView;
        ImageView imageView;
        public
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_source = itemView.findViewById(R.id.text_source);
            text_title  =itemView.findViewById(R.id.text_headline);
            imageView = itemView.findViewById(R.id.img_headline);
            cardView = itemView.findViewById(R.id.main_container);
        }
    }
}
