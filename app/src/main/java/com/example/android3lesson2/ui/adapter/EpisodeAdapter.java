package com.example.android3lesson2.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android3lesson2.databinding.CharacterItemBinding;
import com.example.android3lesson2.databinding.EpisodeItemBinding;
import com.example.android3lesson2.model.CharacterModel;
import com.example.android3lesson2.model.EpisodeModel;

import java.util.ArrayList;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder> {
    private ArrayList<EpisodeModel> list = new ArrayList<>();

    @NonNull
    @Override
    public EpisodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EpisodeAdapter.EpisodeViewHolder(EpisodeItemBinding.inflate(LayoutInflater
                .from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addList(ArrayList<EpisodeModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EpisodeViewHolder extends RecyclerView.ViewHolder {
        EpisodeItemBinding binding;
        public EpisodeViewHolder(@NonNull EpisodeItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(EpisodeModel item) {
            binding.episodeName.setText(item.getName());
            binding.episode.setText(item.getEpisode());
            binding.airDate.setText(item.getAir_date());
            binding.url.setText(item.getUrl());
            binding.created.setText(item.getCreated());
        }
    }
}
