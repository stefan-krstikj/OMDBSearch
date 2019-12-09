package com.example.android.moviesactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    ImageView Poster;
    TextView Plot;
    TextView Year;
    TextView Title;
    TextView Director;
    TextView Actors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details_material);

        initViewsMaterial();
        setViews(getIntent());
    }

    private void initViewsMaterial(){
        Poster = findViewById(R.id.listMovieMaterialPoster);
        Plot = findViewById(R.id.materialPlot);
        Year = findViewById(R.id.listMovieMaterialYear);
        Title = findViewById(R.id.listMovieMaterialTitle);
        Actors = findViewById(R.id.materialActors);
        Director = findViewById(R.id.materialDirector);
    }

    private void setViews(Intent intent){
        Picasso.get()
                .load(intent.getStringExtra("Poster"))
                .into(Poster);
        Plot.setText(intent.getStringExtra("Plot"));
        Year.setText(intent.getStringExtra("Year"));
        Title.setText(intent.getStringExtra("Title"));
        Actors.setText(intent.getStringExtra("Actors"));
        Director.setText(intent.getStringExtra("Director"));
    }
}
