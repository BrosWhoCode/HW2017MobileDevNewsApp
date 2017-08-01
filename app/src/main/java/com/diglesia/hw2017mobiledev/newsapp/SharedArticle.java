package com.diglesia.hw2017mobiledev.newsapp;

//import com.google.firebase.database.DataSnapshot;

import com.google.firebase.database.DataSnapshot;

public class SharedArticle extends Article {

    private String mUserName;
    private String mUserComment;
    private String mUserId;
    private String mFirebaseKey;

    // Construct from a (browsed) Article and user info.
    public SharedArticle(Article origArticle, String userName, String userId, String userComment) {
        mTitle = origArticle.getTitle();
        mImageUrl = origArticle.getImageUrl();
        mArticleUrl = origArticle.getArticleUrl();

        mUserName = userName;
        mUserComment = userComment;
        mUserId = userId;
        // firebase key is empty, that is only generated on upload.
    }

    // Construct from a DataSnapshot from Firebase
    public SharedArticle(DataSnapshot articleDataSnapshot) {
        mTitle = articleDataSnapshot.child("title").getValue(String.class); // or no-param and cast
        mImageUrl = articleDataSnapshot.child("imgUrl").getValue(String.class);
        mArticleUrl = articleDataSnapshot.child("url").getValue(String.class);

        mUserName = articleDataSnapshot.child("userName").getValue(String.class);
        mUserComment = articleDataSnapshot.child("userComment").getValue(String.class);
        mUserId = articleDataSnapshot.child("userId").getValue(String.class);
        mFirebaseKey = articleDataSnapshot.getKey();
    }

    public String getUserComment() {
        return mUserComment;
    }

    public String getUserName() {
        return mUserName;
    }

    public String getUserId() {
        return mUserId;
    }

    public String getFirebaseKey() { return mFirebaseKey;}
}
