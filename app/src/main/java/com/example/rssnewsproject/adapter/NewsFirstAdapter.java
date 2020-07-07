package com.example.rssnewsproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rssnewsproject.activity.DetailActivity;
import com.example.rssnewsproject.R;
import com.example.rssnewsproject.model.News;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewsFirstAdapter extends RecyclerView.Adapter<NewsFirstAdapter.ViewHolder> {

    private List<News> newsList;
    private Context context;
    private List<News> listNews;

    public NewsFirstAdapter(List<News> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_first_new, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        News news = newsList.get(0);
        holder.tvTitle.setText(news.title);
        holder.tvDate.setText(news.pubDate);
        Picasso.with(context).load(news.img).centerCrop().resize(400, 400).into(holder.imgNews);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //tránh lỗi android.os.NetworkOnMainThreadException
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    String title = "";
                    String time = "";
                    String content = "";
                    String description = "";
                    ArrayList<News> al_News = new ArrayList<>();
                    al_News.addAll(newsList);
                    Document document = Jsoup.connect(newsList.get(0).getLink()).get();
                    Element element = document.getElementsByClass("title_news_detail").first();
                    title = element.text();
                    Element elementTime = document.getElementsByClass("time left").first();
                    time = elementTime.text();

                    Element elementDescription = document.getElementsByClass("description").first();
                    description = elementDescription.text();

                    Elements elementContent =  document.getElementsByClass("Normal").tagName("p");
                    for (Element eleSubContent: elementContent){
                        content += eleSubContent.select("p").text() + "\n\n";
                    }

                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("title", title);
                    intent.putExtra("description", description);
                    intent.putExtra("content", content);
                    intent.putExtra("time", time);
                    intent.putExtra("newItem", newsList.get(0));
                    intent.putParcelableArrayListExtra("list", al_News);
                    context.startActivity(intent);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDate;
        ImageView imgNews, imgItem_menu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            imgNews = itemView.findViewById(R.id.imgNews);

        }
    }
}
