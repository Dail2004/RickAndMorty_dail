package com.example.android3lesson2.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android3lesson2.databinding.LocationItemBinding;
import com.example.android3lesson2.model.EpisodeModel;
import com.example.android3lesson2.model.LocationModel;

import java.util.ArrayList;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {
    ArrayList<LocationModel> list = new ArrayList<>();

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LocationViewHolder(LocationItemBinding.inflate(LayoutInflater
                .from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addList(ArrayList<LocationModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder {
        LocationItemBinding binding;

        public LocationViewHolder(@NonNull LocationItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(LocationModel item){
            binding.name.setText(item.getName());
            binding.url.setText(item.getUrl());
            binding.type.setText(item.getType());
            binding.created.setText(item.getCreated());
            binding.dimension.setText(item.getDimension());
        }
    }
}
