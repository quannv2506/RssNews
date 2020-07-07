package com.example.rssnewsproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.rssnewsproject.R;
import com.example.rssnewsproject.fragment.ThoiSuFragment;
import com.example.rssnewsproject.fragment.TrangChuFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

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
        getFragment(TrangChuFragment.newInstance());
    }

    public void getFragment(Fragment fragment) {
        try {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
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
                getFragment(TrangChuFragment.newInstance());
                setTitle(R.string.home);
                mDrawerLayout.closeDrawer(nav_view);
                break;
            case R.id.nav_thoiSu:
                getFragment(ThoiSuFragment.newInstance());
                setTitle(R.string.thoiSu);
                mDrawerLayout.closeDrawer(nav_view);
                break;
            case R.id.nav_history:
                startActivity(new Intent(getBaseContext(), HistoryActivity.class));
                mDrawerLayout.closeDrawer(nav_view);
                break;
        }
        return false;
    }


}
