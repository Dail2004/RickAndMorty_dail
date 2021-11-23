package com.example.android3lesson2.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android3lesson2.databinding.LocationItemBinding;
import com.example.android3lesson2.data.network.dto.model.LocationModel;

import java.util.ArrayList;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {
    private ArrayList<LocationModel> list = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LocationViewHolder(LocationItemBinding.inflate(LayoutInflater
                .from(parent.getContext()), parent, false), listener);
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
        private final LocationItemBinding binding;
        private final OnItemClickListener listener;

        public LocationViewHolder(@NonNull LocationItemBinding binding, OnItemClickListener listener) {
            super(binding.getRoot());
            this.binding = binding;
            this.listener = listener;
        }

        public void onBind(LocationModel item) {
            binding.name.setText(item.getName());
            binding.type.setText(item.getType());
            binding.localed.setText(item.getDimension());

            binding.getRoot().setOnClickListener(v -> {
                listener.onClickListener(item.getId());
            });

        }
    }

    public interface OnItemClickListener {
        void onClickListener(int id);
    }

    public void setOnClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
