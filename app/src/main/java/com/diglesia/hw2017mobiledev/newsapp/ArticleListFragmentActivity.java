package com.diglesia.hw2017mobiledev.newsapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class ArticleListFragmentActivity extends AppCompatActivity {
    private static final String SOURCE_ID_KEY = "sourceId";
    private static final String SOURCE_NAME_KEY = "sourceName";

    public static Intent newIntent(Context context, String sourceId, String sourceName) {
        Intent i = new Intent(context, ArticleListFragmentActivity.class);
        i.putExtra(SOURCE_ID_KEY, sourceId);
        i.putExtra(SOURCE_NAME_KEY, sourceName);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);

        Bundle extras = getIntent().getExtras();
        setTitle(extras.getString(SOURCE_NAME_KEY));

        FragmentManager fm = getSupportFragmentManager();
        if (fm.findFragmentById(R.id.container) == null) {
            // If it there isn't already a fragment in the container, create it and display.
            String sourceId = extras.getString(SOURCE_ID_KEY);
            BrowseArticleListFragment browseArticleListFragment = BrowseArticleListFragment.newInstance(sourceId);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, browseArticleListFragment).commit();
        }
    }
}
