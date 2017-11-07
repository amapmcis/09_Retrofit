package com.example.f1.a09_retrofit;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.f1.a09_retrofit.model.Repo;

import io.realm.RealmResults;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private RealmResults<Repo> listOfRepos;

    public static class ViewHolder extends  RecyclerView.ViewHolder {

        public TextView tvName;
        public TextView tvUrl;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvName = (TextView) itemView.findViewById(R.id.repo_name);
            this.tvUrl = (TextView) itemView.findViewById(R.id.repo_url);
        }
    }

    // Class constructor
    public MyAdapter(RealmResults<Repo> list) {
        this.listOfRepos = list;
    }

    // Creates a new view (invoke by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View listItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_of_list, parent, false);
        final ViewHolder viewHolder = new ViewHolder(listItemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(listOfRepos.get(position).getName());
        holder.tvUrl.setText(listOfRepos.get(position).getHtml_url());
    }

    // Returns the size of the list
    @Override
    public int getItemCount() {
        return listOfRepos.size();
    }
}