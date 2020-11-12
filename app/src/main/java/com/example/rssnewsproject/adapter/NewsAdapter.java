package com.example.rssnewsproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rssnewsproject.R;
import com.example.rssnewsproject.model.News;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<News> newsList;
    private Context context;
    private static final int TYPE_BIG = 1;
    private static final int TYPE_SMALL = 2;


    public NewsAdapter(List<News> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_BIG:
                return new BigViewHolder(LayoutInflater.from(context).inflate(R.layout.item_first_new, parent, false));
            case TYPE_SMALL:
                return new SmallViewHolder(LayoutInflater.from(context).inflate(R.layout.item_news_item, parent, false));
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TYPE_BIG : TYPE_SMALL;
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        if (holder.getItemViewType() == TYPE_BIG) {
            BigViewHolder mHolder = (BigViewHolder) holder;

            News firstNews = newsList.get(position);
            mHolder.tvTitleFirst.setText(firstNews.getTitle());
            mHolder.tvDateFirst.setText(firstNews.getPubDate());
            if (firstNews.img.isEmpty()) {
                ((BigViewHolder) holder).imgFirst.setImageResource(R.drawable.banner_vnexpress);
            } else {
                Picasso.with(context).load(firstNews.img).into(((BigViewHolder) holder).imgFirst);
            }
        } else {
            SmallViewHolder mHolder = (SmallViewHolder) holder;
            News itemNews = newsList.get(position);
            mHolder.tvTitle.setText(itemNews.getTitle());
            mHolder.tvDate.setText(itemNews.getPubDate());
            if (itemNews.img.isEmpty()) {
                ((SmallViewHolder) holder).imgNews.setImageResource(R.drawable.banner_vnexpress);
            } else {
                Picasso.with(context).load(itemNews.img).resize(100, 80).into(((SmallViewHolder) holder).imgNews);
            }
        }

    }


    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }


    public class BigViewHolder extends ViewHolder {
        TextView tvTitleFirst, tvDateFirst;
        ImageView imgFirst;

        public BigViewHolder(View view) {
            super(view);
            this.imgFirst = view.findViewById(R.id.imgSmall);
            this.tvTitleFirst = view.findViewById(R.id.tvTitleFirst);
            this.tvDateFirst = view.findViewById(R.id.tvDateFirst);
        }
    }

    public class SmallViewHolder extends ViewHolder {
        TextView tvTitle, tvDate;
        ImageView imgNews, imgItem_menu;
        CardView cardView;

        public SmallViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvTitle = itemView.findViewById(R.id.tvTitle);
            this.tvDate = itemView.findViewById(R.id.tvDateFirst);
            this.imgNews = itemView.findViewById(R.id.imgSmall);
            this.cardView = itemView.findViewById(R.id.card_view);
        }
    }

}
