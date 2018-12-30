package com.kukroid.newscorp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kukroid.newscorp.Interface.NewsClickListener;
import com.kukroid.newscorp.model.News;
import com.kukroid.newscorp.R;
import com.kukroid.newscorp.Util.Util;

import java.util.ArrayList;

/**
 * Created by kukresa on 12/26/2018.
 */

public class NewsGridAdapter extends RecyclerView.Adapter<NewsGridAdapter.GridViewHolder>{

    Context context ;
    ArrayList<News> newsList;
    NewsClickListener newsClickListener;
    RequestOptions options;
    public NewsGridAdapter(Context context, ArrayList<News> newsList, NewsClickListener newsClickListener){
        this.context = context;
        this.newsList = newsList;
        this.newsClickListener = newsClickListener;
        options = new RequestOptions()
                .centerCrop()
                .error(R.mipmap.ic_launcher_round);

    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Util.log("Creating View: "+i);
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.news_layout,viewGroup,false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder gridViewHolder, int i) {
        Util.log("Binding View: "+i);
        Glide.with(context)
                .load(newsList.get(i).getThumbnailUrl())
                .apply(options)
                .into(gridViewHolder.newsImage);

        gridViewHolder.newsHeading.setText(newsList.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class GridViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView newsImage;
        TextView newsHeading;
        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            newsImage = itemView.findViewById(R.id.newsImage);
            newsHeading = itemView.findViewById(R.id.newsHeading);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            newsClickListener.onItemClick(view,getAdapterPosition());
        }
    }

}
