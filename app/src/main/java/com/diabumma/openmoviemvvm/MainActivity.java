package com.diabumma.openmoviemvvm;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.diabumma.openmoviemvvm.adapters.MovieRecyclerAdapter;
import com.diabumma.openmoviemvvm.models.Movie;
import com.diabumma.openmoviemvvm.viewmodels.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    private EditText searchMovieInput;
    private RecyclerView movieListRecycler;
    private ProgressBar searchMovieProgressBar;

    private MovieRecyclerAdapter movieRecyclerAdapter;
    private ArrayList<Movie> moviesArrayList = new ArrayList<>();

    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchMovieInput = findViewById(R.id.search_movie_text_input);
        movieListRecycler = findViewById(R.id.movie_list_recycler);
        searchMovieProgressBar = findViewById(R.id.search_progress_bar);

        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainActivityViewModel.class);

        searchMovieInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String query = searchMovieInput.getText().toString().trim();
                    if (query.length() != 0) {
                        viewModel.initSearch(query);
                        showProgressBar();
                    }
                    return true;
                }
                return false;
            }
        });

        viewModel.initSearch("");
        viewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                moviesArrayList.clear();
                moviesArrayList.addAll(movies);
                movieRecyclerAdapter.notifyDataSetChanged();
                hideProgressBar();
            }
        });

        initRecyclerView();
    }

    private void initRecyclerView() {
        movieRecyclerAdapter = new MovieRecyclerAdapter(moviesArrayList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        movieListRecycler.setLayoutManager(layoutManager);
        movieListRecycler.setAdapter(movieRecyclerAdapter);
    }

    private void showProgressBar() {
        searchMovieProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        searchMovieProgressBar.setVisibility(View.GONE);
    }
}