package com.example.rssnewsproject.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class News implements Parcelable {
    public String img;
    public String title;
    public String pubDate;
    public String link;

    public News(String img, String title, String pubDate, String link) {
        this.img = img;
        this.title = title;
        this.pubDate = pubDate;
        this.link = link;
    }

    public News() {
        super();
    }


    protected News(Parcel in) {
        img = in.readString();
        title = in.readString();
        pubDate = in.readString();
        link = in.readString();
    }


    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(img);
        dest.writeString(title);
        dest.writeString(pubDate);
        dest.writeString(link);
    }
}
