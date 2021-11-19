package com.example.android3lesson2.ui.fragment.episode;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android3lesson2.base.BaseFragment;
import com.example.android3lesson2.databinding.FragmentEpisodeBinding;
import com.example.android3lesson2.ui.adapter.EpisodeAdapter;
import com.example.android3lesson2.ui.fragment.character.CharacterFragmentDirections;

public class EpisodeFragment extends BaseFragment<EpisodeViewModel, FragmentEpisodeBinding> {
    private final EpisodeAdapter adapter = new EpisodeAdapter();

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
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    protected void setupRequests() {
        viewModel.fetchEpisode().observe(getViewLifecycleOwner(), episodeModels -> {
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

    @Override
    protected void setupListeners() {
        adapter.setOnClickListener(id -> {
            Navigation.findNavController(requireView()).navigate(
                    EpisodeFragmentDirections.actionGlobalDetailsFragment(id)
            );
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}