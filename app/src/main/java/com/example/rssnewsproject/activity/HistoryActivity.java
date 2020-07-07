package com.example.rssnewsproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.rssnewsproject.R;
import com.example.rssnewsproject.adapter.NewsAdapter;
import com.example.rssnewsproject.model.News;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    RecyclerView rvListHistory;
    List<News> listNews;
    NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        rvListHistory = findViewById(R.id.rvListHistory);
        listNews = new ArrayList<>();
        Intent intent = getIntent();
        News news = new News();
        news.title = intent.getParcelableExtra("newItem");

        listNews.add(news);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext(),
                LinearLayoutManager.VERTICAL, false);
        newsAdapter = new NewsAdapter(listNews, getBaseContext());
        rvListHistory.setLayoutManager(layoutManager);
        rvListHistory.setAdapter(newsAdapter);
    }
}
