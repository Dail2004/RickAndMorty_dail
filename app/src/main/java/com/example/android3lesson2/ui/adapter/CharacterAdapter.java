package com.example.android3lesson2.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android3lesson2.databinding.CharacterItemBinding;
import com.example.android3lesson2.model.CharacterModel;

import java.util.ArrayList;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {
    private ArrayList<CharacterModel> list = new ArrayList<>();

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CharacterViewHolder(CharacterItemBinding.inflate(LayoutInflater
                .from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addList(ArrayList<CharacterModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CharacterViewHolder extends RecyclerView.ViewHolder{
        CharacterItemBinding binding;
        public CharacterViewHolder(@NonNull CharacterItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(CharacterModel item) {
            Glide.with(binding.titleIm)
                    .load(item.getImage())
                    .into(binding.titleIm);
            binding.characterName.setText(item.getName());
        }
    }
}
