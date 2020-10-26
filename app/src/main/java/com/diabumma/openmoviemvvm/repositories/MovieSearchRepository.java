package com.diabumma.openmoviemvvm.repositories;

import androidx.lifecycle.MutableLiveData;

import com.diabumma.openmoviemvvm.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieSearchRepository {

    private static MovieSearchRepository instance;
    private ArrayList<Movie> dataset = new ArrayList<>();

    public static MovieSearchRepository getInstance() {
        if (instance == null)
            instance = new MovieSearchRepository();

        return instance;
    }

    public MutableLiveData<List<Movie>> getMovies() {
        setMovieDummies();

        MutableLiveData<List<Movie>> data = new MutableLiveData<>();
        data.setValue(dataset);
        return data;
    }

    private void setMovieDummies() {
        dataset.add(new Movie("The Avengers", "2012", "tt0848228", "movie", "https://m.media-amazon.com/images/M/MV5BNDYxNjQyMjAtNTdiOS00NGYwLWFmNTAtNThmYjU5ZGI2YTI1XkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg"));
        dataset.add(new Movie("Avengers: Infinity War", "2018", "tt4154756", "movie", "https://m.media-amazon.com/images/M/MV5BMjMxNjY2MDU1OV5BMl5BanBnXkFtZTgwNzY1MTUwNTM@._V1_SX300.jpg"));
        dataset.add(new Movie("Avengers: Endgame", "2019", "tt4154796", "movie", "https://m.media-amazon.com/images/M/MV5BMTc5MDE2ODcwNV5BMl5BanBnXkFtZTgwMzI2NzQ2NzM@._V1_SX300.jpg"));
    }
}
