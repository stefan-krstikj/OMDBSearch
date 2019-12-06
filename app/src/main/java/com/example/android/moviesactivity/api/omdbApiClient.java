package com.example.android.moviesactivity.api;

import com.example.android.moviesactivity.service.MovieListService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class omdbApiClient {

    private static Retrofit retroFit = null;
    // api = 434c6b1f
    private static Retrofit getRetroFit(){
        if(retroFit == null){
            retroFit = new Retrofit.Builder()
                    .baseUrl("http://www.omdbapi.com/?apikey=434c6b1f&")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retroFit;
    }

    public static MovieListService getService(){
        return getRetroFit().create(MovieListService.class);
    }
}
