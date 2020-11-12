package com.example.rssnewsproject.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rssnewsproject.R;
import com.example.rssnewsproject.adapter.NewsAdapter;
import com.example.rssnewsproject.model.News;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";
    ImageView imgItem;
    TextView tvTitleItem, tvDescriptionItem, tvContent, tvDateItem;
    RecyclerView rvNewsCategory;
    List<News> listNews;
    NewsAdapter newsAdapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgItem = findViewById(R.id.imgItem);
        tvTitleItem = findViewById(R.id.tvTitleItem);
        tvDescriptionItem = findViewById(R.id.tvDescriptionItem);
        tvContent = findViewById(R.id.tvContent);
        tvDateItem = findViewById(R.id.tvDateItem);
        rvNewsCategory = findViewById(R.id.rvNewsCategory);

        Intent intent = getIntent();
        listNews = intent.getParcelableArrayListExtra("list");
        News newsItem = (News) intent.getParcelableExtra("newItem");

        Picasso.with(this).load(newsItem.img).into(imgItem);
        tvTitleItem.setText(newsItem.title);
        tvDateItem.setText(intent.getStringExtra("time"));
        tvDescriptionItem.setText(intent.getStringExtra("description"));
        tvContent.setText("\n" + intent.getStringExtra("content"));


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext(),
                LinearLayoutManager.VERTICAL, false);
        newsAdapter = new NewsAdapter(listNews, getBaseContext());
        rvNewsCategory.setLayoutManager(layoutManager);
        rvNewsCategory.setAdapter(newsAdapter);
        rvNewsCategory.setNestedScrollingEnabled(false);
    }

}

