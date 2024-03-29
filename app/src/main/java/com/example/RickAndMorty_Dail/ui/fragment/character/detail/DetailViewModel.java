package com.example.RickAndMorty_Dail.ui.fragment.character.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.RickAndMorty_Dail.data.network.dto.CharacterModel;
import com.example.RickAndMorty_Dail.data.repositorie.CharactersRepositories;

public class DetailViewModel extends ViewModel {
    private final CharactersRepositories repositories = new CharactersRepositories();
    public final LiveData<Boolean> loading = repositories._Loading;


    public MutableLiveData<CharacterModel> fetchCharacter(int id) {
        return repositories.fetchCharacter(id);
    }
}