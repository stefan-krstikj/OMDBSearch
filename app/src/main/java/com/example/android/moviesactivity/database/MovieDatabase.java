package com.example.android.moviesactivity.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase{

    public abstract MovieDao movieDao();
}