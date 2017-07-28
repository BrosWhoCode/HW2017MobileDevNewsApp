package com.diglesia.hw2017mobiledev.newsapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class SourceListFragment extends Fragment {
    private ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sourcelist, container, false);

        mListView = v.findViewById(R.id.list_view);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Source source = (Source) parent.getAdapter().getItem(position);
                if (getView().findViewById(R.id.detail_container) == null) {
                    Intent intent = ArticleListFragmentActivity.newIntent(getContext(), source.getId(), source.getName());
                    startActivity(intent);
                } else {
                    BrowseArticleListFragment browseArticleListFragment = BrowseArticleListFragment.newInstance(source.getId());
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.detail_container, browseArticleListFragment).commit();
                }
            }
        });


        NewsSource.get(getContext()).getSources(new NewsSource.SourceListener() {
            @Override
            public void onSourcesReceived(List<Source> sources) {
                SourceAdapter adapter = new SourceAdapter(sources);
                mListView.setAdapter(adapter);
            }
        });

        return v;
    }

    private class SourceAdapter extends BaseAdapter {
        private List<Source> mSourceList;
        private LayoutInflater mLayoutInflater;

        public SourceAdapter(List<Source> sourceList) {
            mSourceList = sourceList;
            mLayoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mSourceList.size();
        }

        @Override
        public Object getItem(int position) {
            return mSourceList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final Source source = mSourceList.get(position);
            View rowView = mLayoutInflater.inflate(R.layout.list_item_source, parent, false);

            TextView title = rowView.findViewById(R.id.source_list_title);
            title.setText(source.getName());

            TextView body = rowView.findViewById(R.id.source_list_subtitle);
            body.setText(source.getDescription());

            return rowView;
        }
    }
}
