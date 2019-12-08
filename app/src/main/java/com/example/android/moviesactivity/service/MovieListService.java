package com.example.android.moviesactivity.service;

import com.example.android.moviesactivity.models.MovieList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieListService {

    @GET("?type=movie")
    Call<MovieList> getMovies(
            @Query("apikey") String apiKey,
            @Query("s") String title);
}
