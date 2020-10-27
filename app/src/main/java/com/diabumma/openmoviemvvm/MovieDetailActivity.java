package com.diabumma.openmoviemvvm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.diabumma.openmoviemvvm.models.MovieDetailScore;
import com.diabumma.openmoviemvvm.viewmodels.MovieDetailViewModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView moviePosterBackdrop;
    private TextView moviePlotText;
    private TextView directorText;
    private TextView writersText;
    private TextView productionStudioText;
    private TextView actorsText;
    private TextView awardsText;
    private TextView imdbScoreText;
    private TextView rottenTomatoesText;
    private TextView metacriticText;
    private CollapsingToolbarLayout collapsingToolbar;

    private MovieDetailViewModel viewModel;
    private String imdbId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Intent intent = getIntent();
        if (intent.hasExtra("IMDB_ID"))
            imdbId = intent.getStringExtra("IMDB_ID");

        moviePosterBackdrop = findViewById(R.id.movie_detail_poster_image);
        moviePlotText = findViewById(R.id.movie_detail_plot_text);
        directorText = findViewById(R.id.movie_detail_director_text);
        writersText = findViewById(R.id.movie_detail_writers_text);
        productionStudioText = findViewById(R.id.movie_detail_studio_text);
        actorsText = findViewById(R.id.movie_detail_actors_text);
        awardsText = findViewById(R.id.movie_detail_awards_text);
        imdbScoreText = findViewById(R.id.imdb_score);
        rottenTomatoesText = findViewById(R.id.rotten_tomatoes_score);
        metacriticText = findViewById(R.id.metacritic_score);

        Toolbar toolbar = findViewById(R.id.movie_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbar = findViewById(R.id.movie_collapsing_toolbar);

        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MovieDetailViewModel.class);
        viewModel.initDetail(imdbId);
        viewModel.getDetail().observe(this, new Observer<MovieDetailScore>() {
            @Override
            public void onChanged(MovieDetailScore movieDetail) {
                Glide
                        .with(getApplicationContext())
                        .load(movieDetail.getMovieDetail().getPosterUrl())
                        .into(moviePosterBackdrop);
                collapsingToolbar.setTitle(movieDetail.getMovieDetail().getTitle());
                moviePlotText.setText(movieDetail.getMovieDetail().getPlot());
                directorText.setText(movieDetail.getMovieDetail().getDirector());
                writersText.setText(movieDetail.getMovieDetail().getWriter());
                productionStudioText.setText(movieDetail.getMovieDetail().getProduction());
                actorsText.setText(movieDetail.getMovieDetail().getActors());
                awardsText.setText(movieDetail.getMovieDetail().getAwards());

                if (movieDetail.getScore().containsKey("Internet Movie Database"))
                    imdbScoreText.setText(movieDetail.getScore().get("Internet Movie Database"));
                if (movieDetail.getScore().containsKey("Rotten Tomatoes"))
                    rottenTomatoesText.setText(movieDetail.getScore().get("Rotten Tomatoes"));
                if (movieDetail.getScore().containsKey("Metacritic"))
                    metacriticText.setText(movieDetail.getScore().get("Metacritic"));
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}