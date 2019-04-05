package com.example.shivam97.eventos.addeventsclasses;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SelectOrgAdapter extends RecyclerView.Adapter<SelectOrgAdapter.RecyclerViewHolder>
                                implements Filterable {
    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public SelectOrgAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SelectOrgAdapter.RecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
