package com.diabumma.openmoviemvvm.networks;

import com.diabumma.openmoviemvvm.models.MovieDetail;
import com.diabumma.openmoviemvvm.models.Search;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenMovieAPI {

    String API_KEY = "afff3a9b";

    @GET(".")
    Call<Search> getMovieSearchList(@Query("apiKey") String apiKey,
                                    @Query("s") String searchQuery);

    @GET(".")
    Call<MovieDetail> getMovieById(@Query("apiKey") String apiKey,
                                   @Query("i") String imdbId);
}
