package com.example.android3lesson2.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<ViewModel, Binding> extends Fragment {
    protected Binding binding;
    protected ViewModel viewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initialize();
        setupListeners();
        setupRequests();
        setupObservers();
        setupViews();
    }

    protected void setupViews() {

    }

    protected void setupRequests() {

    }

    protected void initialize() {

    }

    protected void setupListeners() {

    }

    protected void setupObservers() {

    }

}
