package com.example.salam.salamapp;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
/**
 * Created by salam on 12/3/2017.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {


    private List<ListItem> data;

    public List<ListItem> getData() {
        return data;
    }

    public void setData(List<ListItem> data) {
        this.data = data;
    }

    public RecycleViewAdapter(List<ListItem> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_result,parent,false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.title);
            imageView = (ImageView) itemView.findViewById(R.id.img);

        }

        public void bind(ListItem input) {
            textView.setText(input.getName());
            imageView.setImageURI(Uri.parse(input.getImg()));
        }

    }
}
