package com.diglesia.hw2017mobiledev.newsapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class BrowseArticleListFragment extends BaseArticleListFragment {
    private static final String SOURCE_ID_KEY = "sourceId";

    public static BrowseArticleListFragment newInstance(String sourceId) {
        Bundle args = new Bundle();
        args.putString(SOURCE_ID_KEY, sourceId);
        BrowseArticleListFragment fragment = new BrowseArticleListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Set up mListView in BaseArticleListFragment
        View v = super.onCreateView(inflater, container, savedInstanceState);

        // Logic specific to this subclass
        String sourceId = getArguments().getString(SOURCE_ID_KEY);
        NewsSource.get(mContext).getArticles(sourceId, new NewsSource.ArticleListener() {
            @Override
            public void onArticlesReceived(List<? extends Article> articles) {
                BrowseArticleAdapter adapter = new BrowseArticleAdapter(mContext, articles);
                mListView.setAdapter(adapter);
            }
        });

        return v;
    }

    private void shareArticle(final Article article) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_edit_text, null);
        final EditText commentEditText = (EditText) view.findViewById(R.id.edit_text);
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle("Share Article")
                .setView(view)
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // actually post the article to Firebase
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user == null) {
                            //error
                        }
                        String userName = user.getDisplayName();
                        String userId = user.getUid();
                        String comment = commentEditText.getText().toString();
                        SharedArticle sharedArticle = new SharedArticle(article, userName, userId, comment);
                        NewsSource.get(mContext).shareArticle(sharedArticle);
                    }
                })
                .create();
        alertDialog.show();
    }

    private class BrowseArticleAdapter extends ArticleAdapter {

        public BrowseArticleAdapter(Context context, List<? extends Article> articleList) {
            super(context, articleList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final Article article = mArticleList.get(position);
            View rowView = mLayoutInflater.inflate(R.layout.list_item_article, parent, false);

            TextView title = rowView.findViewById(R.id.article_title_text_view);
            title.setText(article.getTitle());

            TextView body = rowView.findViewById(R.id.article_body_text_view);
            body.setText(article.getDescription());

            NetworkImageView imageView = rowView.findViewById(R.id.article_thumbnail);
            imageView.setImageUrl(article.getImageUrl(), NewsSource.get(mContext).getImageLoader());

            ImageButton shareButton = rowView.findViewById(R.id.share_button);
            shareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    shareArticle(article);
                }
            });

            return rowView;
        }
    }
}
