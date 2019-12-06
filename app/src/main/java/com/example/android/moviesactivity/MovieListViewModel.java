package com.example.android.moviesactivity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android.moviesactivity.database.Movie;
import com.example.android.moviesactivity.database.MovieListAsyncTask;
import com.example.android.moviesactivity.database.MovieRepository;

import java.util.List;

public class MovieListViewModel extends AndroidViewModel {
    MovieRepository repository;

    public MovieListViewModel(Application application) {
        super(application);
        repository = new MovieRepository(application);
        fetchData();
    }

    public LiveData<List<Movie>> getAll(){
        return repository.getAllMovies();
    }

    private void fetchData(){
        MovieListAsyncTask asyncTask =new MovieListAsyncTask(repository);
        asyncTask.execute("Batman");
    }
}
