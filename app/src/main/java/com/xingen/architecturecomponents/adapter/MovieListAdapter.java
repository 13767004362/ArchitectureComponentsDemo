package com.xingen.architecturecomponents.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xingen.architecturecomponents.R;
import com.xingen.architecturecomponents.net.glide.CircularBitmapImageViewTarget;
import com.xingen.architecturecomponents.room.bean.MovieBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${新根} on 2017/12/4.
 * blog:http://blog.csdn.net/hexingen
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    private List<MovieBean> movieBeanList;
    private Context context;
    public MovieListAdapter(){
        this.movieBeanList=new ArrayList<>();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context=parent.getContext();
        View rootView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie,parent,false);
        ViewHolder viewHolder=new ViewHolder(rootView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieBean movieBean=movieBeanList.get(position);
        Glide.with(context).load(movieBean.getImage()).asBitmap().into(new CircularBitmapImageViewTarget(context,holder.imageView));
        holder.title_tv.setText(movieBean.getTitle());
        holder.year_tv.setText(movieBean.getYear());
    }
    @Override
    public int getItemCount() {
        return this.movieBeanList.size();
    }
    public  void changeData(List<MovieBean> list){
        this.movieBeanList.clear();
        this.movieBeanList.addAll(list);
        this.notifyDataSetChanged();
    }
    public  static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView title_tv,year_tv;
        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView=itemView.findViewById(R.id.item_movie_iv);
            this.title_tv=itemView.findViewById(R.id.item_movie_title_tv);
            this.year_tv=itemView.findViewById(R.id.item_movie_year_tv);
        }
    }
}
