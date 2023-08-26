package com.example.newsapp;

import com.example.newsapp.Model.NewsApiResponses;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewApiCall {
    @GET("top-headlines")
    Call<NewsApiResponses> callHeadlines(
            @Query("country")
            String country,
            @Query("category")
            String category,
            @Query("q")
            String query ,
            @Query("apiKey")
            String apikey
    );
}
