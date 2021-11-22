package com.example.android3lesson2.data.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android3lesson2.App;
import com.example.android3lesson2.dto.RickAndMortyResponse;
import com.example.android3lesson2.dto.model.LocationModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationRepositories extends ViewModel {
    private final MutableLiveData<ArrayList<LocationModel>> data = new MutableLiveData<>();
    private final MutableLiveData<Boolean> _Loading = new MutableLiveData<>();
    public final LiveData<Boolean> isLoading = _Loading;

    public MutableLiveData<ArrayList<LocationModel>> fetchLocation(int page) {
        _Loading.setValue(true);
        App.locationApiService.fetchLocation(page).enqueue(new Callback<RickAndMortyResponse<LocationModel>>() {
            @Override
            public void onResponse(@NonNull Call<RickAndMortyResponse<LocationModel>> call, @NonNull Response<RickAndMortyResponse<LocationModel>> response) {
                ArrayList<LocationModel> episodeModels = response.body().getResult();
                data.setValue(episodeModels);
                _Loading.setValue(false);
            }

            @Override
            public void onFailure(@NonNull Call<RickAndMortyResponse<LocationModel>> call, @NonNull Throwable t) {
                data.setValue(null);
                _Loading.setValue(false);
            }
        });
        return data;
    }
}
