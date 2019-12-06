package com.example.android.moviesactivity.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie")
public class Movie {

    @PrimaryKey
    public long imdbID;

    @ColumnInfo(name = "Year")
    public int movieYear;

    @ColumnInfo(name = "Title")
    public String movieName;

    @ColumnInfo(name = "Plot")
    public String shortInfo;

    @ColumnInfo(name = "Poster")
    public String movieThumbnailUrl;
}
