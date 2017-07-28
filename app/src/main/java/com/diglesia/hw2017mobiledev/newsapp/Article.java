package com.diglesia.hw2017mobiledev.newsapp;

import org.json.JSONException;
import org.json.JSONObject;

public class Article {
    protected String mTitle;
    protected String mDescription;
    protected String mArticleUrl;
    protected String mImageUrl;

    public Article() {
        // This needs to be here because subclasses will complain if there isn't a basic empty constructor.
    }

    public Article(JSONObject articleObj) {
        try {
            mTitle = articleObj.getString("title");
            mDescription = articleObj.getString("description");
            mArticleUrl = articleObj.getString("url");
            mImageUrl = articleObj.getString("urlToImage");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getArticleUrl() {
        return mArticleUrl;
    }

    public String getImageUrl() {
        return mImageUrl;
    }
}
