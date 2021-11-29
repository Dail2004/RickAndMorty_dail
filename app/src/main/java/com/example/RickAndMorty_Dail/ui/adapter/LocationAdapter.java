package com.example.RickAndMorty_Dail.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.RickAndMorty_Dail.data.network.dto.LocationModel;
import com.example.RickAndMorty_Dail.databinding.LocationItemBinding;

import java.util.ArrayList;

public class LocationAdapter extends ListAdapter<LocationModel, LocationAdapter.LocationViewHolder> {
    private ArrayList<LocationModel> list = new ArrayList<>();
    private OnItemClickListener listener;

    public LocationAdapter(@NonNull DiffUtil.ItemCallback<LocationModel> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LocationViewHolder(LocationItemBinding.inflate(LayoutInflater
                .from(parent.getContext()), parent, false), listener);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        holder.onBind(getItem(position));
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

    public void setOnClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class LocationComparator extends DiffUtil.ItemCallback<LocationModel> {

        @Override
        public boolean areItemsTheSame(@NonNull LocationModel oldItem, @NonNull LocationModel newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull LocationModel oldItem, @NonNull LocationModel newItem) {
            return oldItem.equals(newItem);
        }
    }
}
