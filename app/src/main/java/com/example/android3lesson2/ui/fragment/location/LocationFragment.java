package com.example.android3lesson2.ui.fragment.location;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.android3lesson2.base.BaseFragment;
import com.example.android3lesson2.databinding.FragmentLocationBinding;
import com.example.android3lesson2.ui.adapter.LocationAdapter;

public class LocationFragment extends BaseFragment<LocationViewModel, FragmentLocationBinding> {
    private final LocationAdapter adapter = new LocationAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLocationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void initialize() {
        viewModel = new ViewModelProvider(this).get(LocationViewModel.class);
        setupCharactersRecycler();
    }

    private void setupCharactersRecycler() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    protected void setupRequests() {
        viewModel.fetchLocation().observe(getViewLifecycleOwner(), locationModels -> {
            adapter.addList(locationModels);
        });

        viewModel.isLoading.observe(this, loading -> {
            if (loading){
                binding.loading.setVisibility(View.VISIBLE);
                binding.recyclerView.setVisibility(View.GONE);
            }else{
                binding.loading.setVisibility(View.GONE);
                binding.recyclerView.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    protected void setupListeners() {
        adapter.setOnClickListener(id -> {
            Navigation.findNavController(requireView()).navigate(
                    LocationFragmentDirections.actionGlobalLocationDetailFragment(id));
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}