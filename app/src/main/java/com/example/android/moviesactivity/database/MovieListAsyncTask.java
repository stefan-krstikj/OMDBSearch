package com.example.android.moviesactivity.database;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.android.moviesactivity.api.omdbApiClient;

import java.io.IOException;

import retrofit2.Call;

public class MovieListAsyncTask extends AsyncTask<String, Integer, Movie> {

    MovieRepository repository;

    public MovieListAsyncTask(MovieRepository repository){
        this.repository = repository;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        repository.deleteAll();
    }

    @Override
    protected Movie doInBackground(String... strings) {
        final Call<Movie> movieList = omdbApiClient.getService().getMovieListById(strings[0]);
        try {
            return movieList.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onPostExecute(Movie movie) {
        repository.insert(movie);
    }


}
