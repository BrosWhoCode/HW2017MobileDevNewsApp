package com.diglesia.hw2017mobiledev.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

// Parent class for BrowseArticleListFragment, MySharedArticleListFragment, and SharedArticleListFragment.
// Contains common logic for inflating view from fragment_articlelist.xml, and setting up the list.
public abstract class BaseArticleListFragment extends Fragment {

    protected ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_articlelist, container, false);

        mListView = v.findViewById(R.id.list_view);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Launch article url into browser.
                Article article = (Article) parent.getAdapter().getItem(position);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(article.getArticleUrl()));
                startActivity(i);
            }
        });

        return v;
    }
}
