package com.example.android3lesson2.ui.fragment.character;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.android3lesson2.base.BaseFragment;
import com.example.android3lesson2.databinding.FragmentCharacterBinding;
import com.example.android3lesson2.ui.adapter.CharacterAdapter;
import com.example.android3lesson2.ui.fragment.character.dialog.DialogFragment;
import com.example.android3lesson2.ui.fragment.character.dialog.DialogFragmentDirections;

public class CharacterFragment extends BaseFragment<CharacterViewModel, FragmentCharacterBinding> {

    private final CharacterAdapter adapter = new CharacterAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharacterBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void initialize() {
        viewModel = new ViewModelProvider(this).get(CharacterViewModel.class);
        setupCharactersRecycler();
    }

    private void setupCharactersRecycler() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    protected void setupRequests() {
        viewModel.fetchCharacters().observe(getViewLifecycleOwner(), characterModels -> {
            adapter.addList(characterModels);
        });

        viewModel.isLoading.observe(this, loading -> {
            if (loading) {
                binding.loading.setVisibility(View.VISIBLE);
                binding.recyclerView.setVisibility(View.GONE);
            } else {
                binding.loading.setVisibility(View.GONE);
                binding.recyclerView.setVisibility(View.VISIBLE);
            }
        });
    }


    @Override
    protected void setupListeners() {
        adapter.setOnClickListener(new CharacterAdapter.OnItemClickListener() {
            @Override
            public void onClickListener(int id) {
                Navigation.findNavController(requireView()).navigate(
                        CharacterFragmentDirections.actionGlobalDetailsFragment(id));
            }

            @Override
            public void onClickListeners(int id) {
                Navigation.findNavController(requireView()).navigate(
                        DialogFragmentDirections.actionGlobalDialogFragment(id));

                DialogFragment dialogFragment = new DialogFragment();
                dialogFragment.
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}