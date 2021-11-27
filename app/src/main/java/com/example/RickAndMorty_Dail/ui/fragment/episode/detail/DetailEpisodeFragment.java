package com.example.RickAndMorty_Dail.ui.fragment.episode.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.example.RickAndMorty_Dail.base.BaseFragment;
import com.example.RickAndMorty_Dail.databinding.FragmentDetailEpisodeBinding;

public class DetailEpisodeFragment extends BaseFragment<EpisodeDetailViewModel, FragmentDetailEpisodeBinding> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailEpisodeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void initialize() {
        viewModel = new ViewModelProvider(this).get(EpisodeDetailViewModel.class);
    }

    @Override
    protected void setupRequests() {

    }

    @Override
    protected void setupObservers() {
        viewModel.fetchEpisode(DetailEpisodeFragmentArgs.fromBundle(getArguments()).getId()).observe(getViewLifecycleOwner(), episodeModel -> {
            binding.episodeName.setText(String.valueOf(episodeModel.getName()));
            binding.episode.setText(String.valueOf(episodeModel.getEpisode()));
            binding.airDate.setText(String.valueOf(episodeModel.getAir_date()));
            binding.created.setText(String.valueOf(episodeModel.getCreated()));
            binding.url.setText(String.valueOf(episodeModel.getUrl()));
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