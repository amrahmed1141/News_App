package com.example.mynewsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.GnssAntennaInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynewsapp.adapter.NewsAdapter;
import com.example.mynewsapp.model.NewsApi;
import com.example.mynewsapp.model.NewsHeadlines;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements SelectListener, View.OnClickListener {

    RecyclerView recyclerView;
    NewsAdapter newsAdapter;
    ProgressDialog dialog;
    Button b1,b2,b3,b4,b5,b6,b7;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog=new ProgressDialog(this);
        dialog.setTitle("Searching News...");
        dialog.show();
        searchView=findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Searching  " + query);
                dialog.show();
                RequestManager manager=new RequestManager(MainActivity.this);
                manager.getNews(listener,"general",query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        b1=findViewById(R.id.btn1);
        b1.setOnClickListener(this);

        b2=findViewById(R.id.btn2);
        b2.setOnClickListener(this);

        b3=findViewById(R.id.btn3);
        b3.setOnClickListener(this);

        b4=findViewById(R.id.btn4);
        b4.setOnClickListener(this);

        b5=findViewById(R.id.btn5);
        b5.setOnClickListener(this);

        b6=findViewById(R.id.btn6);
        b6.setOnClickListener(this);

        b7=findViewById(R.id.btn7);
        b7.setOnClickListener(this);

        RequestManager manager=new RequestManager(this);
        manager.getNews(listener,"general",null);


    }


    private final OnFetchDataListener<NewsApi> listener= new OnFetchDataListener<NewsApi>() {
        @Override
        public void onFetchData(List<NewsHeadlines> list, String message) {
            if (list.isEmpty()){
                Toast.makeText(MainActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            } else {
                showNews(list);
                dialog.dismiss();
            }
        }
        @Override
        public void onError(String message) {

        }
    };

    private void showNews(List<NewsHeadlines> list) {
        recyclerView=findViewById(R.id.main_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        newsAdapter=new NewsAdapter(this,list,this);
        recyclerView.setAdapter(newsAdapter);

    }

    @Override
    public void OnNewsClicked(NewsHeadlines headlines) {
        startActivity(new Intent(MainActivity.this, NewsDetailsActivity.class).putExtra("data",headlines));

    }

    @Override
    public void onClick(View v) {
        Button button=(Button) v;
        String category = button.getText().toString();
        dialog.setTitle("Searching "  +  category);
        dialog.show();
        RequestManager manager=new RequestManager(this);
        manager.getNews(listener,category,null);

    }
}