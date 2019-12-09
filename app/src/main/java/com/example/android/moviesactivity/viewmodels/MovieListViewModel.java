package com.example.android.moviesactivity.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android.moviesactivity.database.Movie;
import com.example.android.moviesactivity.asynctask.MovieListAsyncTask;
import com.example.android.moviesactivity.database.MovieRepository;

import java.util.List;

public class MovieListViewModel extends AndroidViewModel {
    public  MovieRepository repository;

    public MovieListViewModel(Application application) {
        super(application);
        repository = new MovieRepository(application);
    }

    public LiveData<List<Movie>> getAll(String query) {
        fetchDataMovieList(query);
        return repository.getAllMovies();
    }

    private void fetchDataMovieList(String query){
        MovieListAsyncTask asyncTask = new MovieListAsyncTask(repository);
        asyncTask.execute(query);
    }

}
