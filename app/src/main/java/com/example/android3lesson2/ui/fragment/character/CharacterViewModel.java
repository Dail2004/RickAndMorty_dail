package com.example.android3lesson2.ui.fragment.character;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android3lesson2.App;
import com.example.android3lesson2.dto.model.CharacterModel;
import com.example.android3lesson2.dto.RickAndMortyResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<CharacterModel>> data = new MutableLiveData<>();
    private final MutableLiveData<Boolean> _Loading = new MutableLiveData<>();
    public final LiveData<Boolean> isLoading = _Loading;

    public MutableLiveData<ArrayList<CharacterModel>> fetchCharacters() {
        _Loading.setValue(true);
        App.characterApiService.fetchCharacters().enqueue(new Callback<RickAndMortyResponse<CharacterModel>>() {
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
}