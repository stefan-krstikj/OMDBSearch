package com.example.android.moviesactivity.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import java.util.List;

import okhttp3.logging.HttpLoggingInterceptor;

public class MovieRepository {
    private MovieDatabase database = null;

    public MovieRepository(Context context){
        if(database == null){
            database = Room
                    .databaseBuilder(context, MovieDatabase.class, "db-app")
                    .fallbackToDestructiveMigration()
                    .build();
        }
    }

    public LiveData<List<Movie>> getAllMovies(){
            return database.movieDao().getAllAsync();
    }

    public LiveData<Movie> getMovieById(String id){
        return database.movieDao().findById(id);
    }



    @SuppressLint("StaticFieldLeak")
    public void insert(Movie movie){
        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                database.movieDao().insert(movie);
                return null;
            }
        }.execute();
    }

    public void deleteAll(  ){
        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                database.movieDao().deleteAll();
                return null;
            }
        }.execute();
    }

    public void deleteById(String id){
        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                database.movieDao().delete(id);
                return null;
            }
        }.execute();
    }
}
