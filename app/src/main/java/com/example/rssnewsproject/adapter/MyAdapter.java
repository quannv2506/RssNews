package com.example.rssnewsproject.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.rssnewsproject.fragment.KinhDoanhFragment;
import com.example.rssnewsproject.fragment.KinhTeFragment;
import com.example.rssnewsproject.fragment.QuanSuFragment;
import com.example.rssnewsproject.fragment.TheGioiFragment;
import com.example.rssnewsproject.fragment.ThoiSuFragment;
import com.example.rssnewsproject.fragment.TrangChuFragment;

public class MyAdapter extends FragmentStatePagerAdapter {

    private String listTab[] = {"Trang chủ", "Thời sự", "Thế giới", "Kinh doanh", "Startup", "Giải trí"};
    private ThoiSuFragment thoiSuFragment;
    private TrangChuFragment trangChuFragment;
    private KinhTeFragment kinhTeFragment;
    private QuanSuFragment quanSuFragment;
    private TheGioiFragment theGioiFragment;
    private KinhDoanhFragment kinhDoanhFragment;

    public MyAdapter(FragmentManager fm) {
        super(fm);
        thoiSuFragment = new ThoiSuFragment();
        trangChuFragment = new TrangChuFragment();
        kinhTeFragment = new KinhTeFragment();
        quanSuFragment = new QuanSuFragment();
        theGioiFragment = new TheGioiFragment();
        kinhDoanhFragment = new KinhDoanhFragment();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = trangChuFragment;
        } else if (position == 1) {
            fragment = thoiSuFragment;
        } else if (position == 2) {
            fragment = theGioiFragment;
        } else if (position == 3) {
            fragment = quanSuFragment;
        } else if (position == 4) {
            fragment = kinhTeFragment;
        } else if (position == 5) {
            fragment = kinhDoanhFragment;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return listTab.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTab[position];
    }
}
