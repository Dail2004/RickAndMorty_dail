package com.example.RickAndMorty_Dail.ui.fragment.episode;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.RickAndMorty_Dail.base.BaseFragment;
import com.example.RickAndMorty_Dail.databinding.FragmentEpisodeBinding;
import com.example.RickAndMorty_Dail.ui.adapter.EpisodeAdapter;

public class EpisodeFragment extends BaseFragment<EpisodeViewModel, FragmentEpisodeBinding> {
    private final EpisodeAdapter adapter = new EpisodeAdapter();
    private LinearLayoutManager episodeLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEpisodeBinding.inflate(inflater , container, false);
        return binding.getRoot();
    }

    @Override
    protected void initialize() {
        viewModel = new ViewModelProvider(this).get(EpisodeViewModel.class);
        setupCharactersRecycler();
    }

    private void setupCharactersRecycler() {
        episodeLayoutManager = new LinearLayoutManager(getContext());
        binding.recyclerView.setLayoutManager(episodeLayoutManager);
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    protected void setupRequests() {
        viewModel.fetchEpisodes().observe(getViewLifecycleOwner(), episodeModels -> {
            adapter.addList(episodeModels);
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
            Navigation.findNavController(EpisodeFragment.this.requireView()).navigate(
                    EpisodeFragmentDirections.actionGlobalDetailEpisodeFragment(id));
        });

        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.canScrollVertically(1) && dy > 0) {
                    visibleItemCount = episodeLayoutManager.getChildCount();
                    totalItemCount = episodeLayoutManager.getItemCount();
                    pastVisibleItem = episodeLayoutManager.findFirstVisibleItemPosition();
                    if ((visibleItemCount + pastVisibleItem) >= totalItemCount) {
                        viewModel.page++;
                        viewModel.fetchEpisodes().observe(getViewLifecycleOwner(), characterModels -> {
                            adapter.addList(characterModels);
                        });
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