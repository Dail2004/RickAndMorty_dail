package com.example.android3lesson2.ui.fragment.character.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.android3lesson2.R;
import com.example.android3lesson2.data.base.BaseFragment;
import com.example.android3lesson2.databinding.FragmentDetailsBinding;
import com.example.android3lesson2.model.CharacterModel;
import com.example.android3lesson2.ui.fragment.character.CharacterViewModel;

import java.util.ArrayList;

public class DetailsFragment extends BaseFragment<CharacterViewModel, FragmentDetailsBinding> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void setupViews() {

    }

    @Override
    protected void initialize() {
        viewModel = new ViewModelProvider(this).get(CharacterViewModel.class);
    }

    @Override
    protected void setupRequests() {
        viewModel.fetchCharacter().observe(getViewLifecycleOwner(), characterModels -> {

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}