package com.example.android3lesson2.ui.fragment.character;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android3lesson2.data.network.dto.model.CharacterModel;
import com.example.android3lesson2.data.repositorie.CharactersRepositories;

import java.util.ArrayList;

public class CharacterViewModel extends ViewModel {

    private final CharactersRepositories repositories = new CharactersRepositories();
    public final LiveData<Boolean> isLoading = repositories._Loading;
    public int page = 1;

    public MutableLiveData<ArrayList<CharacterModel>> fetchCharacters() {
        return repositories.fetchCharacters(page);
    }
}