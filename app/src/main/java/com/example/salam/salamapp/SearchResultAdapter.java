package com.example.salam.salamapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by salam on 12/3/2017.
 */

public class SearchResultAdapter extends BaseAdapter {


    private List<String> results;
    LayoutInflater layoutInflater;

    public SearchResultAdapter(Context context, List<String> results) {
        setResults(results);
        layoutInflater = LayoutInflater.from(context);
    }

    public List<String> getResults() {
        return results;
    }

    public void setResults(List<String> results) {
        this.results = results;
    }

    @Override
    public int getCount() {
        return results == null ? 0 : results.size();

    }

    @Override
    public Object getItem(int position) {
        return results == null ? null : results.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.search_result, parent, false);
            vh.textView = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(vh);

        } else {
            vh = (ViewHolder) convertView.getTag();
        }


        String item = (String) getItem(position);
        vh.textView.setText(item + " "+position);
        return convertView;
    }

    class ViewHolder {
        TextView textView;
    }

}
