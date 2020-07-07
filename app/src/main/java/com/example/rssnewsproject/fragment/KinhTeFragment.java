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
import com.example.rssnewsproject.databinding.FragmentKinhTeBinding;

public class KinhTeFragment extends Fragment {
    FragmentKinhTeBinding binding;

    public static KinhTeFragment newInstance() {
        Bundle args = new Bundle();
        KinhTeFragment fragment = new KinhTeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_kinh_te, container, false);
        return binding.getRoot();
    }
}
