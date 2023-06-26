package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.newsapp.Model.NewsApiResponses;
import com.example.newsapp.Model.NewsHeadlines;

import java.util.List;

public
class MainActivity extends AppCompatActivity implements SelectListener {
    // recycle
    RecyclerView recyclerView;
    CustomViewAdapter customViewAdapter;
    ProgressDialog dialog;

    @Override
    protected
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog = new ProgressDialog(this);
        dialog.setTitle("getting news...");
        dialog.show();
        // the request manager handeles the api call

        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener,"general",null);




    }

    private final onFetchDataListener<NewsApiResponses> listener = new onFetchDataListener<NewsApiResponses>() {
        @Override
        public
        void onFetchData(List<NewsHeadlines> list, String message) {
            showNews(list);

        }

        @Override
        public
        void onError(String message) {

        }
    };

    private
    void showNews(List<NewsHeadlines> list) {
        recyclerView = findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        customViewAdapter = new CustomViewAdapter(this,list,this);
        recyclerView.setAdapter(customViewAdapter);
        dialog.dismiss();
    }

    @Override
    public
    void OnNewsClicked(NewsHeadlines newsHeadlines) {
        // an item in the recycle view is clicked the function fire
        // the data we get from recycle adapter through interface
        startActivity(new Intent(MainActivity.this,DetailNewsActivity.class).putExtra("data",newsHeadlines));


    }
}