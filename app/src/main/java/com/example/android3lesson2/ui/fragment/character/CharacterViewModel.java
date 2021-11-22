package com.example.android3lesson2.ui.fragment.character;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android3lesson2.data.repositories.CharacterRepositories;
import com.example.android3lesson2.dto.model.CharacterModel;

import java.util.ArrayList;

public class CharacterViewModel extends ViewModel {

    private final CharacterRepositories repositories = new CharacterRepositories();
    private final MutableLiveData<Boolean> _Loading = new MutableLiveData<>();
    public final LiveData<Boolean> isLoading = _Loading;
    public int page = 1;

    public MutableLiveData<ArrayList<CharacterModel>> fetchCharacters() {
        return repositories.fetchCharacters(page);
    }
}