package com.example.mynewsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynewsapp.R;
import com.example.mynewsapp.SelectListener;
import com.example.mynewsapp.model.NewsHeadlines;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter  extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
     private Context context;
     private List<NewsHeadlines> list;
     private SelectListener listener;

    public NewsAdapter(Context context, List<NewsHeadlines> list, SelectListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.headline_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.source.setText(list.get(position).getSource().getName());
        if (list.get(position).getUrlToImage()!=null){
            Picasso.get().load(list.get(position).getUrlToImage()).into(holder.image);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnNewsClicked(list.get(position));

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, source;
        ImageView image;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            source=itemView.findViewById(R.id.source);
            image=itemView.findViewById(R.id.img_headline);
           cardView=itemView.findViewById(R.id.cardview1);

        }
    }
}
