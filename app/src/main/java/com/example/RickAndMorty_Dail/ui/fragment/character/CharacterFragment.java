package com.example.RickAndMorty_Dail.ui.fragment.character;

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
import com.example.RickAndMorty_Dail.data.network.dto.model.CharacterModel;
import com.example.RickAndMorty_Dail.databinding.FragmentCharacterBinding;
import com.example.RickAndMorty_Dail.ui.adapter.CharacterAdapter;

public class CharacterFragment extends BaseFragment<CharacterViewModel, FragmentCharacterBinding> {

    private final CharacterAdapter adapter = new CharacterAdapter(new CharacterAdapter.CharacterComparator());
    private LinearLayoutManager characterLayoutManager;
    private int visibleItemCount;
    private int totalItemCount;
    private int pastVisibleItem;


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
        characterLayoutManager = new LinearLayoutManager(getContext());
        binding.recyclerView.setLayoutManager(characterLayoutManager);
        binding.recyclerView.setAdapter(adapter);
        setupPagination();
    }

    private void setupPagination() {
        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.canScrollVertically(1) && dy > 0) {
                    visibleItemCount = characterLayoutManager.getChildCount();
                    totalItemCount = characterLayoutManager.getItemCount();
                    pastVisibleItem = characterLayoutManager.findFirstVisibleItemPosition();
                    if ((visibleItemCount + pastVisibleItem) >= totalItemCount) {
                        viewModel.page++;
                        viewModel.fetchCharacters().observe(getViewLifecycleOwner(), characterModels -> {
                            adapter.addList(characterModels);
                        });
                    }
                }
            }
        });
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
            public void onClickListeners(int position, CharacterModel image) {
                Navigation.findNavController(requireView()).navigate(
                        CharacterFragmentDirections.actionCharacterFragmentToDialogFragment(image.getImage())
                );
            }
        });

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}