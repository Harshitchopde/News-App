package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsapp.Model.NewsHeadlines;
import com.squareup.picasso.Picasso;

public
class DetailNewsActivity extends AppCompatActivity {
    NewsHeadlines newsHeadlines;
    TextView text_detail_title, text_detail_author, text_detail_time, text_detail_content, text_detail_discreption;
    ImageView imageView;
    CardView cardView;

    @Override
    protected
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);
        text_detail_author = findViewById(R.id.text_detail_auther);
        text_detail_time = findViewById(R.id.text_detail_time);
        text_detail_title = findViewById(R.id.text_title);
        text_detail_content = findViewById(R.id.text_detail_content);
        text_detail_discreption = findViewById(R.id.text_detail_detail);
        imageView =findViewById(R.id.img_detail_news);
        cardView = findViewById(R.id.cardView_url_web);
        Intent intent = getIntent();
        // get the content in the serializable form
        newsHeadlines = (NewsHeadlines) intent.getSerializableExtra("data");
        text_detail_author.setText(newsHeadlines.getAuthor());
        text_detail_time.setText(newsHeadlines.getPublishedAt());
        text_detail_title.setText(newsHeadlines.getTitle());
        text_detail_content.setText(newsHeadlines.getContent());
        text_detail_discreption.setText(newsHeadlines.getDescription());
        Log.e("fdf", "onCreate: "+newsHeadlines.getContent() );
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public
            void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_VIEW);
                intent1.setData(Uri.parse(newsHeadlines.getUrl()));
                startActivity(intent1);
            }
        });
        Picasso.get().load(newsHeadlines.getUrlToImage()).into(imageView);
    }
}