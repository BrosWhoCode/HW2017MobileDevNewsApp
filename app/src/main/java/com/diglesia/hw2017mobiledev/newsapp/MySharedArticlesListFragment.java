package com.diglesia.hw2017mobiledev.newsapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MySharedArticlesListFragment extends BaseArticleListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Set up mListView in BaseArticleListFragment
        View v = super.onCreateView(inflater, container, savedInstanceState);

        // Logic specific to this subclass
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            //error, show toast
            return v;
        }
        NewsSource.get(mContext).getMySharedArticles(user.getUid(), new NewsSource.ArticleListener() {
            @Override
            public void onArticlesReceived(List<? extends Article> articles) {
                MySharedArticleAdapter adapter = new MySharedArticleAdapter(mContext, articles);
                mListView.setAdapter(adapter);
            }
        });

        return v;
    }

    private void unshareArticle(SharedArticle article) {
        NewsSource.get(mContext).unshareArticle(article);
    }

    private class MySharedArticleAdapter extends ArticleAdapter {

        public MySharedArticleAdapter(Context context, List<? extends Article> articleList) {
            super(context, articleList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final SharedArticle article = (SharedArticle) mArticleList.get(position);
            View rowView = mLayoutInflater.inflate(R.layout.list_item_article, parent, false);

            TextView title = rowView.findViewById(R.id.article_title_text_view);
            title.setText(article.getTitle());

            TextView body = rowView.findViewById(R.id.article_body_text_view);
            // Construct the body text out of the user name and user comment.
            String commentText = article.getUserName() + " says: \"" + article.getUserComment() + "\"";
            body.setText(commentText);

            NetworkImageView imageView = rowView.findViewById(R.id.article_thumbnail);
            imageView.setImageUrl(article.getImageUrl(), NewsSource.get(mContext).getImageLoader());

            ImageButton shareButton = rowView.findViewById(R.id.share_button);
            shareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    unshareArticle(article);
                }
            });

            return rowView;
        }
    }
}
