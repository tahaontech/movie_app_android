package com.example.movie.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie.Domains.GenresItem;
import com.example.movie.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryEachFilmListAdapter extends RecyclerView.Adapter<CategoryEachFilmListAdapter.ViewHolder> {
    List<String> items;
    Context context;

    public CategoryEachFilmListAdapter(List<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CategoryEachFilmListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category, parent, false);
        return  new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryEachFilmListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.titleText.setText(items.get(position));

        holder.itemView.setOnClickListener(v -> {

        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder {
        TextView titleText;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.categoryTitle);
        }
    }
}
