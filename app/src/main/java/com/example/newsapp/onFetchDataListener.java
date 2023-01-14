package com.example.newsapp;

import com.example.newsapp.Model.NewsHeadlines;

import java.util.List;

public
interface onFetchDataListener<NewsApiResponses> {
    void onFetchData(List<NewsHeadlines> list, String message);
    void onError(String message);
}
