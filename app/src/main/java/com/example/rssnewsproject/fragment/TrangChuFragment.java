package com.example.rssnewsproject.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rssnewsproject.R;
import com.example.rssnewsproject.adapter.NewsAdapter;
import com.example.rssnewsproject.adapter.NewsFirstAdapter;
import com.example.rssnewsproject.databinding.FragmentTrangChuBinding;
import com.example.rssnewsproject.model.News;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrangChuFragment extends Fragment {

    FragmentTrangChuBinding binding;
    List<News> listNews;
    List<News> listNewsCopy;
    NewsAdapter newsAdapter;
    NewsFirstAdapter newsFirstAdapter;

    public static TrangChuFragment newInstance() {
        Bundle args = new Bundle();
        TrangChuFragment fragment = new TrangChuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_trang_chu, container, false);

        registerForContextMenu(binding.rvNews);
        new ReadRSS2().execute("https://vnexpress.net/rss/tin-moi-nhat.rss");

        return binding.getRoot();
    }

    private class ReadRSS2 extends AsyncTask<String, Void, List<News>> {

        @Override
        protected List<News> doInBackground(String... strings) {
            listNews = new ArrayList<>();
            try {
                org.jsoup.nodes.Document document = Jsoup.connect(strings[0]).get();
                Elements elements = document.select("item");
                News news = null;
                for (org.jsoup.nodes.Element element : elements) {
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
            listNewsCopy = listNews.subList(1, listNews.size());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                    LinearLayoutManager.VERTICAL, false);
            newsAdapter = new NewsAdapter(listNewsCopy, getContext());
            binding.rvNews.setLayoutManager(layoutManager);
            binding.rvNews.setAdapter(newsAdapter);

            RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getContext(),
                    LinearLayoutManager.VERTICAL, false);
            newsFirstAdapter = new NewsFirstAdapter(listNews, getContext());
            binding.rvItem1.setLayoutManager(layoutManager1);
            binding.rvItem1.setAdapter(newsFirstAdapter);
            binding.rvItem1.setNestedScrollingEnabled(false);

        }
    }

}
