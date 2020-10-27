package com.diabumma.openmoviemvvm.repositories;

import androidx.lifecycle.MutableLiveData;

import com.diabumma.openmoviemvvm.models.MovieDetail;
import com.diabumma.openmoviemvvm.models.MovieDetailScore;
import com.diabumma.openmoviemvvm.models.Rating;
import com.diabumma.openmoviemvvm.networks.OpenMovieAPI;
import com.diabumma.openmoviemvvm.networks.RetrofitInstance;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailRepository {

    private static MovieDetailRepository instance;
    private MovieDetailScore dataset;
    private MutableLiveData<MovieDetailScore> data = new MutableLiveData<>();

    public static MovieDetailRepository getInstance() {
        if (instance == null)
            instance = new MovieDetailRepository();

        return instance;
    }

    public MutableLiveData<MovieDetailScore> getMovieDetail(String imdbId) {
        OpenMovieAPI openMovieAPI = RetrofitInstance.getInstance().create(OpenMovieAPI.class);
        Call<MovieDetail> call = openMovieAPI.getMovieById(OpenMovieAPI.API_KEY, imdbId);

        call.enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                if (response.isSuccessful()) {

                    HashMap<String, String> ratings = new HashMap<>();
                    for (Rating item: response.body().getRatings()) {
                        ratings.put(item.getSource(), item.getValue());
                    }

                    if (dataset == null)
                        dataset = new MovieDetailScore(ratings , response.body());
                    else {
                        dataset.setScore(ratings);
                        dataset.setMovieDetail(response.body());
                    }

                    data.setValue(dataset);
                }
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {

            }
        });

        return data;
    }
}
