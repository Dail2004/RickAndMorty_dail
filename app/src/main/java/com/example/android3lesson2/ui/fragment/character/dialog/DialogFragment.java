package com.example.android3lesson2.ui.fragment.character.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.android3lesson2.base.BaseFragment;
import com.example.android3lesson2.databinding.FragmentDialogBinding;

public class DialogFragment extends BaseFragment<DialogViewModel, FragmentDialogBinding> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDialogBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void initialize() {
        viewModel = new ViewModelProvider(this).get(DialogViewModel.class);
    }

    @Override
    protected void setupObservers() {
        viewModel.fetchCharacter(DialogFragmentArgs.fromBundle(getArguments()).getId());
    }

    @Override
    protected void setupRequests() {
        viewModel.character.observe(getViewLifecycleOwner(), characterModel -> {
            Glide.with(binding.dialogImage)
                    .load(characterModel.getImage())
                    .into(binding.dialogImage);
        });
    }
}