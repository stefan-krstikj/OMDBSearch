package com.example.android.moviesactivity.service;

import com.example.android.moviesactivity.database.Movie;
import com.example.android.moviesactivity.database.MovieList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieListService {

    @GET("t={title}")
    Call<Movie> getMovieListById(
            @Path("title") String title
    );

}
