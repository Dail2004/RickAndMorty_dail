package com.example.RickAndMorty_Dail.ui.fragment.character;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.RickAndMorty_Dail.data.network.dto.CharacterModel;
import com.example.RickAndMorty_Dail.data.repositorie.CharactersRepositories;

import java.util.ArrayList;

public class CharacterViewModel extends ViewModel {

    private final CharactersRepositories repositories = new CharactersRepositories();
    public final LiveData<Boolean> isLoading = repositories._Loading;
    public int page = 1;

    public MutableLiveData<ArrayList<CharacterModel>> fetchCharacters() {
        return repositories.fetchCharacters(page);
    }
}