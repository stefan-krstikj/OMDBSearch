package com.example.android.moviesactivity.holders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.moviesactivity.R;
import com.example.android.moviesactivity.database.Movie;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class CustomListViewHolder extends RecyclerView.ViewHolder {

    private ImageView Poster;
    private TextView Title;
    private TextView Year;
    private Context context;
    public CustomListViewHolder(@NonNull View itemView, Context context) {
        super(itemView);

        Poster = itemView.findViewById(R.id.Poster);
        Title = itemView.findViewById(R.id.textView_movieName);
        Year = itemView.findViewById(R.id.textView_movieYear);
        itemView.setTag(this);
        this.context = context;
    }

    public void setText(Movie movie, View.OnClickListener listener){
        Picasso.get()
                .load(movie.Poster)
                .into(Poster);
        Title.setText(movie.Title);
        Year.setText(movie.Year);
        itemView.setOnClickListener(listener);
    }
}
