package com.example.RickAndMorty_Dail.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.RickAndMorty_Dail.data.network.dto.EpisodeModel;
import com.example.RickAndMorty_Dail.databinding.EpisodeItemBinding;

import java.util.ArrayList;

public class EpisodeAdapter extends ListAdapter<EpisodeModel, EpisodeAdapter.EpisodeViewHolder> {
    private ArrayList<EpisodeModel> list = new ArrayList<>();
    private OnItemClickListeners listener;

    public EpisodeAdapter(@NonNull DiffUtil.ItemCallback<EpisodeModel> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public EpisodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EpisodeAdapter.EpisodeViewHolder(EpisodeItemBinding.inflate(LayoutInflater
                .from(parent.getContext()), parent, false), listener);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeViewHolder holder, int position) {
        holder.onBind(getItem(position));
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

    public void setOnClickListener(OnItemClickListeners listener) {
        this.listener = listener;
    }

    public static class EpisodeComparator extends DiffUtil.ItemCallback<EpisodeModel> {

        @Override
        public boolean areItemsTheSame(@NonNull EpisodeModel oldItem, @NonNull EpisodeModel newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull EpisodeModel oldItem, @NonNull EpisodeModel newItem) {
            return oldItem.equals(newItem);
        }
    }
}
