package com.example.android.moviesactivity.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie")
public class Movie {

    @PrimaryKey
    @NonNull
    public String imdbID;

    public String Year;

    @NonNull
    public String Title;

    public String Plot;

    public String Poster;

    public String Actors;
    public String Director;
    public String Rated;
    public String Runtime;
    public String Genre;
    public String Production;
    public String imdbRating;
    public String Metascore;
}
