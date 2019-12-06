package com.example.android.moviesactivity.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDao{

    @Query("SELECT * FROM  movie")
    public LiveData<List<Movie>> getAllAsync();

    @Query("SELECT * from movie WHERE imdbID = :id")
    public List<Movie>findById(long id);

    @Query("SELECT * from movie WHERE Title = :title")
    public List<Movie> findByTitle(String title);

    @Insert
    public void insert(Movie... movies);

    @Query("DELETE from movie WHERE imdbID = :id")
    public void delete(long id);

    @Query("DELETE from movie")
    public void deleteAll();
}
