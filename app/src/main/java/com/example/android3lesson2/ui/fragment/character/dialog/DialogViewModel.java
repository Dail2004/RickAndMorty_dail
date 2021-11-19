package com.example.android3lesson2.ui.fragment.character.dialog;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android3lesson2.App;
import com.example.android3lesson2.dto.model.CharacterModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogViewModel extends ViewModel {
    private final MutableLiveData<CharacterModel> _character = new MutableLiveData<>();
    public final LiveData<CharacterModel> character = _character;
    private final MutableLiveData<Boolean> _loading = new MutableLiveData<>();
    public final LiveData<Boolean> loading = _loading;

    public void fetchCharacter(int id) {
        _loading.setValue(true);
        App.characterApiService.fetchCharacter(id).enqueue(new Callback<CharacterModel>() {
            @Override
            public void onResponse(Call<CharacterModel> call, Response<CharacterModel> response) {
                _character.setValue(response.body());
                _loading.setValue(false);
            }

            @Override
            public void onFailure(Call<CharacterModel> call, Throwable t) {
                _character.setValue(null);
                _loading.setValue(false);
            }
        });
    }
}
