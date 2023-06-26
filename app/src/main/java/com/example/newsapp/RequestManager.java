package com.example.newsapp;

import android.content.Context;
import android.widget.Toast;

import com.example.newsapp.Model.NewsApiResponses;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public
class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            // this is the base url to fetch data
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

//     ---
    public void getNewsHeadlines(onFetchDataListener listener, String category,String query){
        NewApiCall newApiCall = retrofit.create(NewApiCall.class);
        Call<NewsApiResponses> call =newApiCall.callHeadlines("in",category,query,context.getString(R.string.api_key));

        try{

            call.enqueue(new Callback<NewsApiResponses>() {
                @Override
                public
                void onResponse(Call<NewsApiResponses> call, Response<NewsApiResponses> response) {
                    if (!response.isSuccessful()){
                        Toast.makeText(context, "Error!!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        listener.onFetchData(response.body().getArticles(), response.message());
                    }

                }

                @Override
                public
                void onFailure(Call<NewsApiResponses> call, Throwable t) {
                    listener.onError("Request Fail");

                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public
    RequestManager(Context context) {
        this.context = context;
    }


    public interface NewApiCall{
        @GET("top-headlines")
            Call<NewsApiResponses> callHeadlines(

                    @Query("country") String country,
                    @Query("category") String category,
                    @Query("q") String query,
                    @Query("apiKey") String api_key

            );
    }
}
