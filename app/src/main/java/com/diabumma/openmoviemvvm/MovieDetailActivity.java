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
import com.diabumma.openmoviemvvm.models.MovieDetail;
import com.diabumma.openmoviemvvm.viewmodels.MovieDetailViewModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView moviePosterBackdrop;
    private TextView movieTitleText;
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
        movieTitleText = findViewById(R.id.movie_detail_title_text);

        Toolbar toolbar = findViewById(R.id.movie_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbar = findViewById(R.id.movie_collapsing_toolbar);

        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MovieDetailViewModel.class);
        viewModel.initDetail(imdbId);
        viewModel.getDetail().observe(this, new Observer<MovieDetail>() {
            @Override
            public void onChanged(MovieDetail movieDetail) {
                Glide
                        .with(getApplicationContext())
                        .load(movieDetail.getPosterUrl())
                        .into(moviePosterBackdrop);
                collapsingToolbar.setTitle(movieDetail.getTitle());
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}