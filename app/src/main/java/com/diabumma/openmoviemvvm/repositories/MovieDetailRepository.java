package com.diabumma.openmoviemvvm.repositories;

import androidx.lifecycle.MutableLiveData;

import com.diabumma.openmoviemvvm.models.MovieDetail;
import com.diabumma.openmoviemvvm.networks.OpenMovieAPI;
import com.diabumma.openmoviemvvm.networks.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailRepository {

    private static MovieDetailRepository instance;
    //private MovieDetail dataset = new MovieDetail();
    private MutableLiveData<MovieDetail> data = new MutableLiveData<>();

    public static MovieDetailRepository getInstance() {
        if (instance == null)
            instance = new MovieDetailRepository();

        return instance;
    }

    public MutableLiveData<MovieDetail> getMovieDetail(String imdbId) {
        OpenMovieAPI openMovieAPI = RetrofitInstance.getInstance().create(OpenMovieAPI.class);
        Call<MovieDetail> call = openMovieAPI.getMovieById(OpenMovieAPI.API_KEY, imdbId);

        call.enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {

            }
        });

        return data;
    }
}
