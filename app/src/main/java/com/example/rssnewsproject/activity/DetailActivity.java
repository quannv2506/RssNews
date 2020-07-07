package com.example.rssnewsproject.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rssnewsproject.R;
import com.example.rssnewsproject.adapter.NewsAdapter;
import com.example.rssnewsproject.fragment.ThoiSuFragment;
import com.example.rssnewsproject.fragment.TrangChuFragment;
import com.example.rssnewsproject.model.News;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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

