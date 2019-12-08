package com.example.android.moviesactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import android.view.Menu;
import android.view.View;


import com.example.android.moviesactivity.asynctask.MovieListAsyncTask;
import com.example.android.moviesactivity.database.Movie;
import com.example.android.moviesactivity.database.MovieRepository;
import com.example.android.moviesactivity.holders.CustomListViewHolder;

import java.util.List;
import java.util.logging.Logger;


public class MainActivity extends AppCompatActivity {

    CustomMovieAdapter adapter;
    MovieListViewModel movieListViewModel;
    boolean dataInitaliazed = false;
    SearchView searchView;

    Logger logger = Logger.getLogger("MainActivity");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initListView();
      //  initData();
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

            }
        };
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
