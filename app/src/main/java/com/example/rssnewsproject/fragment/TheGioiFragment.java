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
import com.example.rssnewsproject.databinding.FragmentQuanSuBinding;
import com.example.rssnewsproject.databinding.FragmentTheGioiBinding;

public class TheGioiFragment extends Fragment {
    FragmentTheGioiBinding binding;

    public static TheGioiFragment newInstance() {
        Bundle args = new Bundle();
        TheGioiFragment fragment = new TheGioiFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_the_gioi, container, false);
        return binding.getRoot();
    }
}