package com.diglesia.hw2017mobiledev.newsapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

public class SharedArticleListFragment extends BaseArticleListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Set up mListView in BaseArticleListFragment
        View v = super.onCreateView(inflater, container, savedInstanceState);

        // Logic specific to this subclass
        NewsSource.get(getContext()).getSharedArticles(new NewsSource.ArticleListener() {
            @Override
            public void onArticlesReceived(List<? extends Article> articles) {
                SharedArticleAdapter adapter = new SharedArticleAdapter(getContext(), articles);
                mListView.setAdapter(adapter);
            }
        });

        return v;
    }

    private class SharedArticleAdapter extends ArticleAdapter {

        public SharedArticleAdapter(Context context, List<? extends Article> articleList) {
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

            // Hide the image button.
            ImageButton shareButton = rowView.findViewById(R.id.share_button);
            shareButton.setVisibility(View.GONE);

            return rowView;
        }
    }
}
