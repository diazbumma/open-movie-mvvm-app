package com.diabumma.openmoviemvvm.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.diabumma.openmoviemvvm.models.Movie;
import com.diabumma.openmoviemvvm.repositories.MovieSearchRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private String TAG = "MainActivityViewModel";

    private MutableLiveData<List<Movie>> movieData;
    private MovieSearchRepository movieSearchRepository;

    public void initSearch(String searchQuery) {
        Log.d(TAG, "init. called" + searchQuery);
        movieSearchRepository = MovieSearchRepository.getInstance();
        movieData = movieSearchRepository.getMovies(searchQuery);
    }

    public LiveData<List<Movie>> getMovies() {
        return movieData;
    }
}
