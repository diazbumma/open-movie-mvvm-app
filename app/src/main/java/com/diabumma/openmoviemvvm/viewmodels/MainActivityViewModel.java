package com.diabumma.openmoviemvvm.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.diabumma.openmoviemvvm.models.Movie;
import com.diabumma.openmoviemvvm.repositories.MovieSearchRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<Movie>> movieData;
    private MovieSearchRepository movieSearchRepository;

    public void init() {
        if (movieData != null)
            return;

        movieSearchRepository = MovieSearchRepository.getInstance();
        movieData = movieSearchRepository.getMovies();
    }

    public LiveData<List<Movie>> getMovies() {
        return movieData;
    }
}
