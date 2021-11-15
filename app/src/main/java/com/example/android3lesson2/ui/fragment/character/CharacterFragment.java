package com.example.android3lesson2.ui.fragment.character;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.android3lesson2.data.base.BaseFragment;
import com.example.android3lesson2.databinding.FragmentCharacterBinding;
import com.example.android3lesson2.model.CharacterModel;
import com.example.android3lesson2.ui.adapter.CharacterAdapter;

import java.util.ArrayList;

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
        viewModel.fetchCharacter().observe(getViewLifecycleOwner(), new Observer<ArrayList<CharacterModel>>() {
            @Override
            public void onChanged(ArrayList<CharacterModel> characterModels) {
                adapter.addList(characterModels);
            }
        });
    }

    @Override
    protected void setupViews() {
        binding.recyclerView.setOnClickListener(v ->
            Navigation.findNavController(v).navigate(
                    CharacterFragmentDirections.actionGlobalDetailsFragment()
            ));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}