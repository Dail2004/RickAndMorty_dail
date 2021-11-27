package com.example.RickAndMorty_Dail.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.RickAndMorty_Dail.databinding.CharacterItemBinding;
import com.example.RickAndMorty_Dail.data.network.dto.model.CharacterModel;

import java.util.ArrayList;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {
    private final ArrayList<CharacterModel> list = new ArrayList<>();
    private OnItemClickListener listener;

    public void setOnClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CharacterViewHolder(CharacterItemBinding.inflate(LayoutInflater
                .from(parent.getContext()), parent, false), listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addList(ArrayList<CharacterModel> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CharacterViewHolder extends RecyclerView.ViewHolder {
        CharacterItemBinding binding;
        private final OnItemClickListener listener;

        public CharacterViewHolder(@NonNull CharacterItemBinding binding, OnItemClickListener listener) {
            super(binding.getRoot());
            this.binding = binding;
            this.listener = listener;
        }

        public void onBind(CharacterModel item) {
            Glide.with(binding.titleIm)
                    .load(item.getImage())
                    .into(binding.titleIm);
            binding.characterName.setText(item.getName());
            binding.status2.setText(item.getStatus());
            binding.species.setText(item.getSpecies());

            binding.getRoot().setOnLongClickListener(v -> {
                listener.onClickListeners(getAdapterPosition(), item);
                return false;
            });

            binding.getRoot().setOnClickListener(v -> {
                listener.onClickListener(item.getId());
            });
        }
    }
    public interface OnItemClickListener {
        void onClickListener(int id);
        void onClickListeners(int position, CharacterModel image);
    }

}
