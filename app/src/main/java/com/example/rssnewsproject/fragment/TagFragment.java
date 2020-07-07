package com.example.rssnewsproject.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
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

import com.example.rssnewsproject.databinding.FragmentTagBinding;

import com.example.rssnewsproject.model.News;

import java.util.ArrayList;
import java.util.List;

public class TagFragment extends Fragment {
    FragmentTagBinding binding;
    List<News> listNews;
    NewsAdapter newsAdapter;

    public static TagFragment newInstance() {
        Bundle args = new Bundle();
        TagFragment fragment = new TagFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tag, container, false);

        Bundle bundle = getArguments();
        News newsItem = bundle.getParcelable("object");
        listNews = new ArrayList<>();
        listNews.add(newsItem);

        newsAdapter = new NewsAdapter(listNews, getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        binding.rvTag.setLayoutManager(layoutManager);
        binding.rvTag.setAdapter(newsAdapter);

        return binding.getRoot();
    }
}
