package com.example.RickAndMorty_Dail.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.RickAndMorty_Dail.databinding.EpisodeItemBinding;
import com.example.RickAndMorty_Dail.data.network.dto.model.EpisodeModel;

import java.util.ArrayList;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder> {
    private ArrayList<EpisodeModel> list = new ArrayList<>();
    private OnItemClickListeners listener;

    @NonNull
    @Override
    public EpisodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EpisodeAdapter.EpisodeViewHolder(EpisodeItemBinding.inflate(LayoutInflater
                .from(parent.getContext()), parent, false), listener);
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
        OnItemClickListeners listener;
        public EpisodeViewHolder(@NonNull EpisodeItemBinding binding, OnItemClickListeners listener) {
            super(binding.getRoot());
            this.binding = binding;
            this.listener = listener;
        }

        public void onBind(EpisodeModel item) {
            binding.episodeName.setText(item.getName());
            binding.episode.setText(item.getEpisode());
            binding.airDate.setText(item.getAir_date());

            binding.getRoot().setOnClickListener(v -> {
                listener.onClickListener(item.getId());
            });
        }
    }

    public interface OnItemClickListeners {
        void onClickListener(int id);
    }

    public void setOnClickListener(OnItemClickListeners listener){
        this.listener = listener;
    }
}
