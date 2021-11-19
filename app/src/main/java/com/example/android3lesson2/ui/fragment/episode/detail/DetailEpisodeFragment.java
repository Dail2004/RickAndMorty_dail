package com.example.android3lesson2.ui.fragment.episode.detail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android3lesson2.R;
import com.example.android3lesson2.base.BaseFragment;
import com.example.android3lesson2.databinding.FragmentEpisodeBinding;
import com.example.android3lesson2.ui.fragment.character.detail.DetailViewModel;
import com.example.android3lesson2.ui.fragment.character.detail.DetailsFragmentArgs;

public class DetailEpisodeFragment extends BaseFragment<EpisodeDetailViewModel, FragmentEpisodeBinding> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEpisodeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void initialize() {
        viewModel = new ViewModelProvider(this).get(EpisodeDetailViewModel.class);
    }

    @Override
    protected void setupRequests() {
        viewModel.fetchEpisode(DetailEpisodeFragmentArgs.fromBundle(getArguments()).getId());
    }

    @Override
    protected void setupObservers() {
        viewModel.loading.observe(this, loading -> {
            if (loading){
                binding.loading.setVisibility(View.VISIBLE);
            }else{
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