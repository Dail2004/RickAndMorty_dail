package com.example.android3lesson2.ui.fragment.location;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android3lesson2.base.BaseFragment;
import com.example.android3lesson2.databinding.FragmentLocationBinding;
import com.example.android3lesson2.ui.adapter.LocationAdapter;

public class LocationFragment extends BaseFragment<LocationViewModel, FragmentLocationBinding> {
    private final LocationAdapter adapter = new LocationAdapter();
    private LinearLayoutManager locationLayoutManager;

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
        locationLayoutManager = new LinearLayoutManager(getContext());
        binding.recyclerView.setLayoutManager(locationLayoutManager);
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    protected void setupRequests() {
        viewModel.fetchLocations().observe(getViewLifecycleOwner(), locationModels -> {
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
    private int visibleItemCount;
    private int totalItemCount;
    private int pastVisibleItem;

    @Override
    protected void setupListeners() {
        adapter.setOnClickListener(id -> {
            Navigation.findNavController(requireView()).navigate(
                    LocationFragmentDirections.actionGlobalLocationDetailFragment(id));
        });

        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    visibleItemCount = locationLayoutManager.getChildCount();
                    totalItemCount = locationLayoutManager.getItemCount();
                    pastVisibleItem = locationLayoutManager.findFirstVisibleItemPosition();
                    if ((visibleItemCount + pastVisibleItem) >= totalItemCount) {
                        viewModel.page++;
                        viewModel.fetchLocations().observe(getViewLifecycleOwner(), characterModels -> {
                            adapter.addList(characterModels);
                        }   );
                    }
                }
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}