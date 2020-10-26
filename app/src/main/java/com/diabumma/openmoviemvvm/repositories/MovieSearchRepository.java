package com.diabumma.openmoviemvvm.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.diabumma.openmoviemvvm.models.Movie;
import com.diabumma.openmoviemvvm.models.Search;
import com.diabumma.openmoviemvvm.networks.MovieAPI;
import com.diabumma.openmoviemvvm.networks.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieSearchRepository {

    private final String TAG = "MovieSearch Repository";

    private static MovieSearchRepository instance;
    private ArrayList<Movie> dataset = new ArrayList<>();
    private MutableLiveData<List<Movie>> data = new MutableLiveData<>();

    public static MovieSearchRepository getInstance() {
        if (instance == null)
            instance = new MovieSearchRepository();

        return instance;
    }

    public MutableLiveData<List<Movie>> getMovies(String searchQuery) {
        //setMovieDummies();
        dataset.clear();

        if (searchQuery.equals("")) {
            data.setValue(dataset);
            return data;
        }

        MovieAPI movieAPI = RetrofitInstance.getInstance().create(MovieAPI.class);
        Call<Search> call = movieAPI.getMovieSearchList(MovieAPI.API_KEY, searchQuery);

        call.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                if (response.isSuccessful()) {
                    Search search = response.body();
                    dataset.addAll(search.getMovies());
                    data.setValue(dataset);
                }
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });

        return data;
    }

    private void setMovieDummies() {
        dataset.add(new Movie("The Avengers", "2012", "tt0848228", "movie", "https://m.media-amazon.com/images/M/MV5BNDYxNjQyMjAtNTdiOS00NGYwLWFmNTAtNThmYjU5ZGI2YTI1XkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg"));
        dataset.add(new Movie("Avengers: Infinity War", "2018", "tt4154756", "movie", "https://m.media-amazon.com/images/M/MV5BMjMxNjY2MDU1OV5BMl5BanBnXkFtZTgwNzY1MTUwNTM@._V1_SX300.jpg"));
        dataset.add(new Movie("Avengers: Endgame", "2019", "tt4154796", "movie", "https://m.media-amazon.com/images/M/MV5BMTc5MDE2ODcwNV5BMl5BanBnXkFtZTgwMzI2NzQ2NzM@._V1_SX300.jpg"));
    }
}
