package com.diabumma.openmoviemvvm.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.diabumma.openmoviemvvm.models.MovieDetail;
import com.diabumma.openmoviemvvm.repositories.MovieDetailRepository;

public class MovieDetailViewModel extends ViewModel {

    private MutableLiveData<MovieDetail> movieDetailData;
    private MovieDetailRepository movieDetailRepository;

    public void initDetail(String imdbId) {
        movieDetailRepository = MovieDetailRepository.getInstance();
        movieDetailData = movieDetailRepository.getMovieDetail(imdbId);
    }

    public LiveData<MovieDetail> getDetail() {
        return movieDetailData;
    }
}
