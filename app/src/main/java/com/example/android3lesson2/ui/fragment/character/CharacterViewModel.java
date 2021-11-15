package com.example.android3lesson2.ui.fragment.character;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android3lesson2.App;
import com.example.android3lesson2.model.CharacterModel;
import com.example.android3lesson2.model.RickAndMortyResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterViewModel extends ViewModel {
    public MutableLiveData<ArrayList<CharacterModel>> fetchCharacter() {

        MutableLiveData<ArrayList<CharacterModel>> data = new MutableLiveData<>();
        App.characterApiService.fetchCharacter().enqueue(new Callback<RickAndMortyResponse<CharacterModel>>() {
            @Override
            public void onResponse(@NonNull Call<RickAndMortyResponse<CharacterModel>> call, @NonNull Response<RickAndMortyResponse<CharacterModel>> response) {
                ArrayList<CharacterModel> characterModels = response.body().getResult();
                data.setValue(characterModels);
            }

            @Override
            public void onFailure(@NonNull Call<RickAndMortyResponse<CharacterModel>> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}