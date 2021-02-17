package com.example.rssnewsproject.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.rssnewsproject.R;
import com.example.rssnewsproject.activity.DetailActivity;
import com.example.rssnewsproject.databinding.FragmentDetailsBinding;
import com.example.rssnewsproject.model.News;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class DetailFragment extends Fragment {
    FragmentDetailsBinding binding;

    public static DetailFragment newInstance() {
        Bundle args = new Bundle();
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false);
        SharedPreferences prefs = getActivity().getSharedPreferences("Data", Context.MODE_PRIVATE);
        final String link = prefs.getString("link", null);
        String title = "";
        String time = "";
        String content = "";
        String description = "";
        String img = "";

        try {
            //tránh lỗi android.os.NetworkOnMainThreadException
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Document document = Jsoup.connect(link).get();
            Element element = document.getElementsByClass("title_news_detail").first();
            title = element.text();
            Element elementTime = document.getElementsByClass("time left").first();
            time = elementTime.text();
            Elements elementImgs = document.getElementsByClass("tplCaption").tagName("td");
            for (Element elementImg : elementImgs) {
                img = elementImg.select("img").attr("src");
                content += img;
            }

            Element elementDescription = document.getElementsByClass("description").first();
            description = elementDescription.text();

            Elements elementContent = document.getElementsByClass("Normal").tagName("p");
            for (Element eleSubContent : elementContent) {
                content += eleSubContent.select("p").text() + "\n\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (img.isEmpty()){
            binding.imgItem.setImageResource(R.drawable.banner_vnexpress);
        } else {
            Picasso.with(getContext()).load(img).into(binding.imgItem);
        }
        binding.tvTitleItem.setText(title);
        binding.tvDateItem.setText(time);
        binding.tvDescriptionItem.setText(description);
        binding.tvContent.setText(content);


//        String imgBanner = prefs.getString("img", null);
//        if (!imgBanner.isEmpty()) {
//            Picasso.with(getContext()).load(imgBanner).into(binding.imgItem);
////        } else if (imgBanner.isEmpty()){
////            Picasso.with(getContext()).load(prefs.getString("imgBig", null)).into(binding.imgItem);
//        } else {
//            binding.imgItem.setImageResource(R.drawable.banner_vnexpress);
//        }
//        if (imgBanner.isEmpty()){
//            binding.imgContent.setVisibility(View.GONE);
//        } else {
//            Picasso.with(getContext()).load(imgBanner).into(binding.imgContent);
//        }
//        binding.tvTitleItem.setText(prefs.getString("title", null));
//        binding.tvDateItem.setText(prefs.getString("time", null));
//        binding.tvDescriptionItem.setText(prefs.getString("description", null));
//        binding.tvContent.setText("\n" + prefs.getString("content", null));
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
