package com.example.android3lesson2.ui.fragment.character;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android3lesson2.base.BaseFragment;
import com.example.android3lesson2.databinding.FragmentCharacterBinding;
import com.example.android3lesson2.ui.adapter.CharacterAdapter;
import com.example.android3lesson2.ui.fragment.character.dialog.DialogFragmentDirections;

public class CharacterFragment extends BaseFragment<CharacterViewModel, FragmentCharacterBinding> {

    private final CharacterAdapter adapter = new CharacterAdapter();
    private final LinearLayoutManager characterLayoutManager = new LinearLayoutManager(getContext());

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
        binding.recyclerView.setLayoutManager(characterLayoutManager);
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


    private int visibleItemCount;
    private int totalItemCount;
    private int pastVisibleItem;

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
            }
        });


        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
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
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

//- Прочитать про обобщения (generics)
//- Добавить Repository и Base class'ы
//- Сделать пагинацию