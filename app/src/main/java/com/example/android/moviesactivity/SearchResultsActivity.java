package com.example.android.moviesactivity;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class SearchResultsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("SearchResultsActivity", "onCreate() CALLED!!!!");
        Log.v("SearchResultsActivity", savedInstanceState.toString());
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.v("SearchResultsActivity", "onNewIntent() called");

        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        Log.v("SearchResultsActivity", "handleIntent() called");

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
        }
    }

}