package com.example.mynewsapp;

import com.example.mynewsapp.model.NewsHeadlines;

import java.util.List;

public interface OnFetchDataListener<NewsApi> {
    void onFetchData(List<NewsHeadlines> list, String message);
    void onError(String message);
}
