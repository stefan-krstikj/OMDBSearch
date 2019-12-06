package com.example.android.moviesactivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.moviesactivity.database.Movie;

import org.w3c.dom.Text;

public class CustomListViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView movieName;
    private TextView movieYear;
    private TextView moviePlot;

    public CustomListViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.imageView);
        movieName = itemView.findViewById(R.id.textView_movieName);
        movieYear = itemView.findViewById(R.id.textView_movieYear);
        moviePlot = itemView.findViewById(R.id.textView_moviePlot);
    }

    public void setText(Movie movie, View.OnClickListener listener){
        imageView.setImageResource(R.drawable.ic_launcher_background);
        movieName.setText(movie.movieName);
        movieYear.setText(movie.movieYear);
        moviePlot.setText(movie.shortInfo);
        itemView.setOnClickListener(listener);
    }
}
