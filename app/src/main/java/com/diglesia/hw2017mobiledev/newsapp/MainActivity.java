package com.diglesia.hw2017mobiledev.newsapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_browse:
                    mViewPager.setCurrentItem(0);
                    setTitle(R.string.title_browse);
                    return true;
                case R.id.navigation_myarticles:
                    mViewPager.setCurrentItem(1);
                    setTitle(R.string.title_myarticles);
                    return true;
                case R.id.navigation_feed:
                    mViewPager.setCurrentItem(2);
                    setTitle(R.string.title_feed);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mViewPager = (ViewPager) findViewById(R.id.content_view_pager);
        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return new SourceListFragment();
                } else if (position == 1) {
                    return new MySharedArticlesListFragment();
                } else {
                    return new SharedArticleListFragment();
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    navigation.setSelectedItemId(R.id.navigation_browse);
                } else if (position == 1) {
                    navigation.setSelectedItemId(R.id.navigation_myarticles);
                } else if (position == 2) {
                    navigation.setSelectedItemId(R.id.navigation_feed);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }
}
