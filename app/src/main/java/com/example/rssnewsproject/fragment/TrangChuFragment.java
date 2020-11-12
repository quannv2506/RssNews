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
import com.example.rssnewsproject.classes.ReadRSS;
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
        if (getArguments() != null){
            String link = this.getArguments().getString("link");
            new ReadRSS(listNews, newsAdapter, getContext(), binding.rvNews).execute(link);
        }

        return binding.getRoot();
    }



}
