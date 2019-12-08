package com.example.android.moviesactivity.api;

import com.example.android.moviesactivity.service.MovieListService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class omdbApiClient {

    private static Retrofit retroFit = null;
    // api = 434c6b1f
    private static Retrofit getRetroFit(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();

        if(retroFit == null){

            retroFit = new Retrofit.Builder()
                    .baseUrl("http://www.omdbapi.com/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retroFit;
    }

    public static MovieListService getService(){
        return getRetroFit().create(MovieListService.class);
    }
}
