package com.example.android3lesson2.ui.fragment.character.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.android3lesson2.base.BaseFragment;
import com.example.android3lesson2.databinding.FragmentDetailsBinding;

public class DetailsFragment extends BaseFragment<DetailViewModel, FragmentDetailsBinding> {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void initialize() {
        viewModel = new ViewModelProvider(this).get(DetailViewModel.class);
    }

    @Override
    protected void setupRequests() {
        viewModel.fetchCharacter(DetailsFragmentArgs.fromBundle(getArguments()).getId());
    }

    @Override
    protected void setupObservers() {
        viewModel.character.observe(getViewLifecycleOwner(), characterModel -> {
            Glide.with(binding.image)
                    .load(characterModel.getImage())
                    .into(binding.image);
            binding.name.setText(String.valueOf(characterModel.getName()));
            binding.created.setText(String.valueOf(characterModel.getCreated()));
            binding.url.setText(String.valueOf(characterModel.getUrl()));
            binding.species.setText(String.valueOf(characterModel.getSpecies()));
            binding.status.setText(String.valueOf(characterModel.getStatus()));
        });

        viewModel.loading.observe(this, loading -> {
            if (loading) {
                binding.loading.setVisibility(View.VISIBLE);
            } else {
                binding.loading.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}