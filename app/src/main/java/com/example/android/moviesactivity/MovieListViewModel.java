package com.example.android.moviesactivity;

import android.app.Application;

import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android.moviesactivity.database.Movie;
import com.example.android.moviesactivity.asynctask.MovieListAsyncTask;
import com.example.android.moviesactivity.database.MovieRepository;

import java.util.List;

public class MovieListViewModel extends AndroidViewModel {
    MovieRepository repository;

    public MovieListViewModel(Application application) {
        super(application);
        repository = new MovieRepository(application);
    }

    public LiveData<List<Movie>> getAll(String query){
        fetchData(query);
        return repository.getAllMovies();
    }


    private void fetchData(String query){
        MovieListAsyncTask asyncTask =new MovieListAsyncTask(repository);
        asyncTask.execute(query);
    }
}
