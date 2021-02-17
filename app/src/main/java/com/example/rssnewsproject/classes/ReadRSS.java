package com.example.rssnewsproject.classes;

import android.content.Context;
import android.os.AsyncTask;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rssnewsproject.adapter.NewsAdapter;
import com.example.rssnewsproject.model.News;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadRSS extends AsyncTask<String, Void, List<News>> {

    List<News> listNews;
    NewsAdapter newsAdapter;
    Context context;
    RecyclerView recyclerView;
    RecyclerView recyclerView2;



    public ReadRSS(List<News> listNews, NewsAdapter newsAdapter, Context context, RecyclerView recyclerView) {
        this.listNews = listNews;
        this.newsAdapter = newsAdapter;
        this.context = context;
        this.recyclerView = recyclerView;
    }

//    public ReadRSS(List<News> listNewsCopy, NewsFirstAdapter newsFirstAdapter, RecyclerView recyclerView) {
//        this.listNewsCopy = listNewsCopy;
//        this.newsFirstAdapter = newsFirstAdapter;
//        this.recyclerView = recyclerView;
//    }

    @Override
    protected List<News> doInBackground(String... strings) {
        try {
            listNews = new ArrayList<News>();
            org.jsoup.nodes.Document document = Jsoup.connect(strings[0]).get();
            Elements elements = document.select("item");
            News news = null;
            for (org.jsoup.nodes.Element element : elements){
                news = new News();
                news.setTitle(element.select("title").text());
                news.setImg(Jsoup.parse(element.select("description").text()).select("img").attr("src"));
                news.setLink(element.select("link").text());
                news.setPubDate(element.select("pubDate").text());
                listNews.add(news);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listNews;
    }

    @Override
    protected void onPostExecute(List<News> news) {
        super.onPostExecute(news);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false);
        newsAdapter = new NewsAdapter(listNews, context);
        recyclerView.setLayoutManager(layoutManager1);
        recyclerView.setAdapter(newsAdapter);

//        listNewsCopy = listNews.subList(0, listNews.size());
//        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(context,
//                LinearLayoutManager.VERTICAL, false);
//        newsFirstAdapter = new NewsFirstAdapter(listNewsCopy, context);
//        recyclerView2.setLayoutManager(layoutManager2);
//        recyclerView2.setAdapter(newsFirstAdapter);
//        recyclerView2.setNestedScrollingEnabled(false);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    public void getRecycleView(RecyclerView recyclerView){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false);
        newsAdapter = new NewsAdapter(listNews, context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(newsAdapter);
    }
}
