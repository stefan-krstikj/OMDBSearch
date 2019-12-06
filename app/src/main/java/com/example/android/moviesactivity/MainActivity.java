package com.example.android.moviesactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.SearchView;

import java.util.logging.Logger;


public class MainActivity extends AppCompatActivity {

    CustomMovieAdapter adapter;

    Logger logger = Logger.getLogger("MainActivity");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initListView();
        initData();

    }

    private void initData(){
        MovieListViewModel movieListViewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);
        movieListViewModel.getAll().observe(this, data -> {
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
                CustomListViewHolder listViewHolder =(CustomListViewHolder) view.getTag();
                String selectedMovieName =adapter.getClickedMovieName(listViewHolder);
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setOnQueryTextListener(getOnQueryTextListener());
        return true;
    }

    private SearchView.OnQueryTextListener getOnQueryTextListener(){
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                logger.info("Query text change: " + newText);
                return false;
            }
        };
    }
}
