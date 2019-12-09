package com.example.android.moviesactivity.asynctask;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.android.moviesactivity.api.APIKeys;
import com.example.android.moviesactivity.api.omdbApiClient;
import com.example.android.moviesactivity.database.Movie;
import com.example.android.moviesactivity.models.MovieList;
import com.example.android.moviesactivity.database.MovieRepository;

import java.io.IOException;

import retrofit2.Call;

public class MovieListAsyncTask extends AsyncTask<String, Integer, MovieList> {
    static MovieRepository repository;


    public static MovieRepository getRepository() {
        return repository;
    }



    public MovieListAsyncTask(MovieRepository repository){
        this.repository = repository;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        repository.deleteAll();
    }

    @Override
    protected MovieList doInBackground(String... strings) {
        final Call<MovieList> movieList = omdbApiClient.getService().getMovies(APIKeys.APIKEY, strings[0]);
        try {
            MovieList list = movieList.execute().body();
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onPostExecute(MovieList movieList) {
        if(movieList!=null){
            for(Movie t: movieList.Search) {
                repository.insert(t);
            }
        }
    }


}
