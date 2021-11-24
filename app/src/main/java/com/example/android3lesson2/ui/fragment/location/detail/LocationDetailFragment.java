package com.example.android3lesson2.ui.fragment.location.detail;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android3lesson2.base.BaseFragment;
import com.example.android3lesson2.databinding.FragmentLocationDetailBinding;

public class LocationDetailFragment extends BaseFragment<LocationDetailViewModel, FragmentLocationDetailBinding> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLocationDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void initialize() {
        viewModel = new ViewModelProvider(this).get(LocationDetailViewModel.class);
    }

    @Override
    protected void setupRequests() {
        viewModel.fetchLocation(LocationDetailFragmentArgs.fromBundle(getArguments()).getId());
    }

    @Override
    protected void setupObservers() {
        viewModel.fetchLocation(LocationDetailFragmentArgs.fromBundle(getArguments()).getId()).observe(getViewLifecycleOwner(), locationModel -> {
            binding.name.setText(String.valueOf(locationModel.getName()));
            binding.url.setText(String.valueOf(locationModel.getUrl()));
            binding.type.setText(String.valueOf(locationModel.getType()));
            binding.created.setText(String.valueOf(locationModel.getCreated()));
            binding.dimension.setText(String.valueOf(locationModel.getDimension()));
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}