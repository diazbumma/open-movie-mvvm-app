package com.diabumma.openmoviemvvm.models;

import java.util.HashMap;

public class MovieDetailScore {

    private HashMap<String, String> score;
    private MovieDetail movieDetail;

    public MovieDetailScore(HashMap<String, String> score, MovieDetail movieDetail) {
        this.score = score;
        this.movieDetail = movieDetail;
    }

    public HashMap<String, String> getScore() {
        return score;
    }

    public void setScore(HashMap<String, String> score) {
        this.score = score;
    }

    public MovieDetail getMovieDetail() {
        return movieDetail;
    }

    public void setMovieDetail(MovieDetail movieDetail) {
        this.movieDetail = movieDetail;
    }
}
