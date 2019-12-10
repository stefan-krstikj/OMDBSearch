package com.example.android.moviesactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.Menu;
import android.view.View;
import android.widget.TextView;


import com.example.android.moviesactivity.adapters.CustomMovieAdapter;
import com.example.android.moviesactivity.api.APIKeys;
import com.example.android.moviesactivity.api.omdbApiClient;
import com.example.android.moviesactivity.database.Movie;
import com.example.android.moviesactivity.holders.CustomListViewHolder;
import com.example.android.moviesactivity.viewmodels.MovieListViewModel;

import java.io.IOException;
import java.util.logging.Logger;

import retrofit2.Call;


public class MoviesSearchActivity extends AppCompatActivity {

    CustomMovieAdapter adapter;
    MovieListViewModel movieListViewModel;

    boolean dataInitaliazed = false;
    SearchView searchView;

    Logger logger = Logger.getLogger("MoviesSearchActivity");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_search);

        initToolbar();
        initListView();
    }


    private void initData(String query){
        movieListViewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);
        movieListViewModel.getAll(query).observe(this, data -> {
            adapter.updateDataset(data);
        });
    }

    private void initToolbar(){
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    private void initListView(){
        // todo: add material design cards & dividers between list items
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CustomMovieAdapter(getViewOnClickListener());
        recyclerView.setAdapter(adapter);
    }

    private View.OnClickListener getViewOnClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomListViewHolder listViewHolder = (CustomListViewHolder) view.getTag();
                Movie selectedMovie = adapter.getClickedMovie(listViewHolder);

                DetailedAsyncTask asyncTask = new DetailedAsyncTask();
                asyncTask.execute(selectedMovie.imdbID);


            }
        };
    }

    private class DetailedAsyncTask extends AsyncTask<String, Void, Movie>{

        @Override
        protected Movie doInBackground(String... strings) {
            final Call<Movie> movieCall = omdbApiClient.getService().getMovieByID(APIKeys.APIKEY, strings[0]);
            try {
                Movie movie = movieCall.execute().body();
                return movie;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Movie movie) {
            super.onPostExecute(movie);
            showDetailedView(movie);
        }
    }

    private void showDetailedView(Movie selectedMovie){
        Intent intent = new Intent(this, MovieDetailsActivity.class);



        // todo: send 'Movie' object as extra
        intent.putExtra("movieID", selectedMovie.imdbID);
        intent.putExtra("Plot", selectedMovie.Plot);
        intent.putExtra("Poster", selectedMovie.Poster);
        intent.putExtra("Title", selectedMovie.Title);
        intent.putExtra("Year", selectedMovie.Year);
        intent.putExtra("Director", selectedMovie.Director);

         intent.putExtra("Actors", selectedMovie.Actors);
         intent.putExtra("imdbRating", selectedMovie.imdbRating);
         intent.putExtra("Metascore", selectedMovie.Metascore);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);

        searchView = (SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setOnQueryTextListener(getOnQueryTextListener());
        return true;
    }

    private SearchView.OnQueryTextListener getOnQueryTextListener(){
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!dataInitaliazed){
                    initData(query);
                    dataInitaliazed = true;
                }
                else {
                    movieListViewModel.getAll(query);
                }
                searchView.setQuery("", false);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                logger.info("Query text change: " + newText);
                return false;
            }
        };
    }
}
