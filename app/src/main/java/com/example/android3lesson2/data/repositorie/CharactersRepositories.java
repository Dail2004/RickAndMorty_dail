package com.example.android3lesson2.data.repositorie;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.android3lesson2.App;
import com.example.android3lesson2.data.network.dto.RickAndMortyResponse;
import com.example.android3lesson2.data.network.dto.model.CharacterModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharactersRepositories {

    public final MutableLiveData<Boolean> _Loading = new MutableLiveData<>();

    public MutableLiveData<ArrayList<CharacterModel>> fetchCharacters(int page) {
        MutableLiveData<ArrayList<CharacterModel>> data = new MutableLiveData<>();
        _Loading.setValue(true);
        App.characterApiService.fetchCharacters(page).enqueue(new Callback<RickAndMortyResponse<CharacterModel>>() {
            @Override
            public void onResponse(@NonNull Call<RickAndMortyResponse<CharacterModel>> call, @NonNull Response<RickAndMortyResponse<CharacterModel>> response) {
                ArrayList<CharacterModel> characterModels = response.body().getResult();
                data.setValue(characterModels);
                _Loading.setValue(false);
            }

            @Override
            public void onFailure(@NonNull Call<RickAndMortyResponse<CharacterModel>> call, @NonNull Throwable t) {
                data.setValue(null);
                _Loading.setValue(false);
            }
        });
        return data;
    }

    public MutableLiveData<CharacterModel> fetchCharacter(int id) {
        MutableLiveData<CharacterModel> _character = new MutableLiveData<>();
        _Loading.setValue(true);
        App.characterApiService.fetchCharacter(id).enqueue(new Callback<CharacterModel>() {
            @Override
            public void onResponse(Call<CharacterModel> call, Response<CharacterModel> response) {
                _character.setValue(response.body());
                _Loading.setValue(false);
            }

            @Override
            public void onFailure(Call<CharacterModel> call, Throwable t) {
                _character.setValue(null);
                _Loading.setValue(false);
            }
        });
        return _character;
    }
}
