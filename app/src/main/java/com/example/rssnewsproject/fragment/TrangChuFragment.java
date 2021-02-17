package com.example.rssnewsproject.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.rssnewsproject.R;
import com.example.rssnewsproject.adapter.NewsAdapter;
import com.example.rssnewsproject.classes.ReadRSS;
import com.example.rssnewsproject.databinding.FragmentTrangChuBinding;
import com.example.rssnewsproject.model.News;

import java.util.List;

public class TrangChuFragment extends Fragment {

    private static final String TAG = "TAG";
    FragmentTrangChuBinding binding;
    List<News> listNews;
    NewsAdapter newsAdapter;

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
