package com.example.rssnewsproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rssnewsproject.activity.DetailActivity;
import com.example.rssnewsproject.IOnClickLitsener;
import com.example.rssnewsproject.R;
import com.example.rssnewsproject.fragment.DetailFragment;
import com.example.rssnewsproject.model.News;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.android.volley.VolleyLog.TAG;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<News> newsList;
    private Context context;
    IOnClickLitsener iOnClickLitsener;

    public void setiOnClickLitsener(IOnClickLitsener iOnClickLitsener) {
        this.iOnClickLitsener = iOnClickLitsener;
    }

    public NewsAdapter(List<News> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_news_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final News news = newsList.get(position);
        holder.tvTitle.setText(news.title);
        holder.tvDate.setText(news.pubDate);
        if (news.img.isEmpty()) {
            holder.imgNews.setImageResource(R.drawable.banner_vnexpress);
        } else {
            Picasso.with(context).load(news.img).resize(100, 80).into(holder.imgNews);
        }

//        holder.imgItem_menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                PopupMenu popupMenu = new PopupMenu(context, holder.imgItem_menu);
////                popupMenu.getMenuInflater().inflate(R.menu.item_menu, popupMenu.getMenu());
////                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
////                    @Override
////                    public boolean onMenuItemClick(MenuItem item) {
////                        switch (item.getItemId()) {
////                            case R.id.mn_tag:
////                                Toast.makeText(context, "Đánh dấu", Toast.LENGTH_SHORT).show();
////                                break;
////                            case R.id.mn_share:
////                                Toast.makeText(context, "Chia sẻ", Toast.LENGTH_SHORT).show();
////                                break;
////                        }
////                        return false;
////                    }
////                });
////                popupMenu.show();
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("object", news);
//                Fragment fragment = new TagFragment();
//                fragment.setArguments(bundle);
//            }
//        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                try {
                //tránh lỗi android.os.NetworkOnMainThreadException
//                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//                    StrictMode.setThreadPolicy(policy);
//                    iOnClickLitsener.onClick(news);

//                    String title = "";
//                    String time = "";
//                    String content = "";
//                    String description = "";
//                    String img = "";
//                    ArrayList<News> al_News = new ArrayList<>();
//                    al_News.addAll(newsList);
//                    Document document = Jsoup.connect(newsList.get(position).getLink()).get();
//                    Element element = document.getElementsByClass("title_news_detail").first();
//                    title = element.text();
//                    Element elementTime = document.getElementsByClass("time left").first();
//                    time = elementTime.text();
//                    Elements elementImgs = document.getElementsByClass("tplCaption").tagName("td");
//                    for (Element elementImg : elementImgs) {
//                        img = elementImg.select("img").attr("src");
//                        content += img;
//                    }
//                    Element elementDescription = document.getElementsByClass("description").first();
//                    description = elementDescription.text();
//                    Elements elementContent = document.getElementsByClass("Normal").tagName("p");
//                    for (Element eleSubContent : elementContent) {
//                        content += eleSubContent.select("p").text() + "\n\n";
//                    }
//                    Intent intent = new Intent(context, DetailActivity.class);
//                    intent.putExtra("title", title);
//                    intent.putExtra("description", description);
//                    intent.putExtra("content", content);
//                    intent.putExtra("time", time);
//                    intent.putExtra("newItem", news);
//                    intent.putParcelableArrayListExtra("list", al_News);
//                    //tránh lỗi khi click vào item của DetailActivity
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(intent);
                SharedPreferences.Editor editor = context.getSharedPreferences("Data", Context.MODE_PRIVATE).edit();
//                    editor.putString("title", title);
//                    editor.putString("description", description);
//                    editor.putString("content", content);
//                    editor.putString("time", time);
//                    editor.putString("img", img);
//                    editor.putString("imgBig", news.img);
                editor.putString("link", news.link);
                editor.apply();
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                DetailFragment newFragment = new DetailFragment();
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, newFragment).addToBackStack(null).commit();
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDate;
        ImageView imgNews, imgItem_menu;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            imgNews = itemView.findViewById(R.id.imgNews);
            cardView = itemView.findViewById(R.id.card_view);
//            cardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });

        }
    }

    public interface OnNewsListener {
        void onNewsClick(News news);
    }

}
