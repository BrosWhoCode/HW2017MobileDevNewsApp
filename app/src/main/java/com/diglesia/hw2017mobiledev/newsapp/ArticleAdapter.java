package com.diglesia.hw2017mobiledev.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class ArticleAdapter extends BaseAdapter {
    protected Context mContext;
    protected List<? extends Article> mArticleList;
    protected LayoutInflater mLayoutInflater;

    public ArticleAdapter(Context context, List<? extends Article> articleList) {
        mContext = context;
        mArticleList = articleList;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mArticleList.size();
    }

    @Override
    public Object getItem(int position) {
        return mArticleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /* Note that
    public View getView(int position, View convertView, ViewGroup parent) {}
    is not implemented here. All subclasses must implement it themselves. */
}
