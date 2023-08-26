package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;


import com.example.newsapp.Model.NewsHeadlines;

import java.util.List;

public
class MainActivity extends AppCompatActivity implements SelectListener,onFetchDataListener {
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
        manager.getNewsHeadlines(this,"general",null);




    }



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

    @Override
    public void onFetchData(List list, String message) {
        showNews(list);
    }

    @Override
    public void onError(String message) {

    }
}