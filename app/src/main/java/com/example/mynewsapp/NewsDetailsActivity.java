package com.example.mynewsapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mynewsapp.model.NewsHeadlines;
import com.squareup.picasso.Picasso;

public class NewsDetailsActivity extends AppCompatActivity {
    NewsHeadlines headlines;
    TextView title, author,details,time,content;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        headlines= (NewsHeadlines) getIntent().getSerializableExtra("data");

         title=findViewById(R.id.details_title);
         author=findViewById(R.id.details_author);
       details=findViewById(R.id.details_details);
      time=findViewById(R.id.details_time);
      content=findViewById(R.id.details_content);
        image=findViewById(R.id.details_img);

        title.setText(headlines.getTitle());
        author.setText(headlines.getAuthor());
        time.setText(headlines.getPublishedAt());
        details.setText(headlines.getDescription());
        content.setText(headlines.getContent());


        Picasso.get().load(headlines.getUrlToImage()).into(image);




    }
}