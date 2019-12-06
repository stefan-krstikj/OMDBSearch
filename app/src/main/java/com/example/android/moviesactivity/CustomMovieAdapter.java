package com.example.android.moviesactivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.moviesactivity.database.Movie;

import java.util.ArrayList;
import java.util.List;

public class CustomMovieAdapter extends RecyclerView.Adapter {

    private List<Movie> dataset;
    private View.OnClickListener listener;

    public CustomMovieAdapter(View.OnClickListener listener) {
        this.dataset =new ArrayList<>();
        this.listener = listener;
    }

    public String getClickedMovieName(CustomListViewHolder holder){
        int position = holder.getAdapterPosition();
        return dataset.get(position).movieName;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_layout, parent, false);
        return new CustomListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((CustomListViewHolder) holder).setText(dataset.get(position), listener);
    }

    public void updateDataset(List<Movie> newDataset) {
        this.dataset = newDataset;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
