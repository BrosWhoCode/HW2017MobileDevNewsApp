package com.diglesia.hw2017mobiledev.newsapp;

import org.json.JSONException;
import org.json.JSONObject;

public class Source {
    private String mId;
    private String mName;
    private String mDescription;
    private String mURLString;

    public Source(JSONObject sourceObj) {
        try {
            mId = sourceObj.getString("id");
            mName = sourceObj.getString("name");
            mDescription = sourceObj.getString("description");
            mURLString = sourceObj.getString("url");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getURLString() {
        return mURLString;
    }
}
