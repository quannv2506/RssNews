package com.example.rssnewsproject.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.rssnewsproject.R;
import com.example.rssnewsproject.fragment.TrangChuFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView nav_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.home);

        nav_view = findViewById(R.id.nav_view);
        mDrawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nav_view.setItemIconTintList(null);
        nav_view.setBackgroundResource(R.color.white);
        nav_view.setNavigationItemSelectedListener(this);
        getFragment(TrangChuFragment.newInstance(), "https://vnexpress.net/rss/tin-moi-nhat.rss");
    }

    public void getFragment(Fragment fragment, String link) {
        try {
            Bundle bundle = new Bundle();
            bundle.putString("link", link);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
            fragment.setArguments(bundle);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "getFragment: " + e.getMessage());
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id) {
            case R.id.nav_home:
                getFragment(TrangChuFragment.newInstance(), "https://vnexpress.net/rss/tin-moi-nhat.rss");
                setTitle(R.string.home);
                mDrawerLayout.closeDrawer(nav_view);
                overridePendingTransition(R.xml.slide_in_from_right, R.xml.slide_out_to_left);
                break;
            case R.id.nav_thoiSu:
                getFragment(TrangChuFragment.newInstance(), "https://vnexpress.net/rss/thoi-su.rss");
                setTitle(R.string.thoiSu);
                mDrawerLayout.closeDrawer(nav_view);
                overridePendingTransition(R.xml.slide_in_from_right, R.xml.slide_out_to_left);

                break;
            case R.id.nav_theGioi:
                getFragment(TrangChuFragment.newInstance(), "https://vnexpress.net/rss/the-gioi.rss");
                setTitle(R.string.theGioi);
                mDrawerLayout.closeDrawer(nav_view);
                break;
            case R.id.nav_doiSong:
                getFragment(TrangChuFragment.newInstance(), "https://vnexpress.net/rss/doi-song.rss");
                setTitle(R.string.doiSong);
                mDrawerLayout.closeDrawer(nav_view);
                break;
            case R.id.nav_sucKhoe:
                getFragment(TrangChuFragment.newInstance(), "https://vnexpress.net/rss/suc-khoe.rss");
                setTitle(R.string.sucKhoe);
                mDrawerLayout.closeDrawer(nav_view);
                break;
            case R.id.nav_duLich:
                getFragment(TrangChuFragment.newInstance(), "https://vnexpress.net/rss/du-lich.rss");
                setTitle(R.string.duLich);
                mDrawerLayout.closeDrawer(nav_view);
                break;
            case R.id.nav_kinhDoanh:
                getFragment(TrangChuFragment.newInstance(), "https://vnexpress.net/rss/kinh-doanh.rss");
                setTitle(R.string.kinh_doanh);
                mDrawerLayout.closeDrawer(nav_view);
                break;
            case R.id.nav_khoaHoc:
                getFragment(TrangChuFragment.newInstance(), "https://vnexpress.net/rss/khoa-hoc.rss");
                setTitle(R.string.khoaHoc);
                mDrawerLayout.closeDrawer(nav_view);
                break;
            case R.id.nav_startup:
                getFragment(TrangChuFragment.newInstance(), "https://vnexpress.net/rss/startup.rss");
                setTitle(R.string.startup);
                mDrawerLayout.closeDrawer(nav_view);
                break;
            case R.id.nav_soHoa:
                getFragment(TrangChuFragment.newInstance(), "https://vnexpress.net/rss/so-hoa.rss");
                setTitle(R.string.soHoa);
                mDrawerLayout.closeDrawer(nav_view);
                break;
            case R.id.nav_giaiTri:
                getFragment(TrangChuFragment.newInstance(), "https://vnexpress.net/rss/giai-tri.rss");
                setTitle(R.string.giaiTri);
                mDrawerLayout.closeDrawer(nav_view);
                break;
            case R.id.nav_xe:
                getFragment(TrangChuFragment.newInstance(), "https://vnexpress.net/rss/xe.rss");
                setTitle(R.string.xe);
                mDrawerLayout.closeDrawer(nav_view);
                break;
            case R.id.nav_theThao:
                getFragment(TrangChuFragment.newInstance(), "https://vnexpress.net/rss/the-thao.rss");
                setTitle(R.string.theThao);
                mDrawerLayout.closeDrawer(nav_view);
                break;
            case R.id.nav_yKien:
                getFragment(TrangChuFragment.newInstance(), "https://vnexpress.net/rss/y-kien.rss");
                setTitle(R.string.yKien);
                mDrawerLayout.closeDrawer(nav_view);
                break;
            case R.id.nav_phapLuat:
                getFragment(TrangChuFragment.newInstance(), "https://vnexpress.net/rss/phap-luat.rss");
                setTitle(R.string.phapLuat);
                mDrawerLayout.closeDrawer(nav_view);
                break;
            case R.id.nav_tamSu:
                getFragment(TrangChuFragment.newInstance(), "https://vnexpress.net/rss/tam-su.rss");
                setTitle(R.string.tam_su);
                mDrawerLayout.closeDrawer(nav_view);
                break;
            case R.id.nav_giaoDuc:
                getFragment(TrangChuFragment.newInstance(), "https://vnexpress.net/rss/giao-duc.rss");
                setTitle(R.string.giaoDuc);
                mDrawerLayout.closeDrawer(nav_view);
                break;
            case R.id.nav_cuoi:
                getFragment(TrangChuFragment.newInstance(), "https://vnexpress.net/rss/cuoi.rss");
                setTitle(R.string.cuoi);
                mDrawerLayout.closeDrawer(nav_view);
                break;
        }
        return false;
    }


}
