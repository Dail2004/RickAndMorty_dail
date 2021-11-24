package com.example.android3lesson2.data.repositorie;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.android3lesson2.App;
import com.example.android3lesson2.data.network.dto.RickAndMortyResponse;
import com.example.android3lesson2.data.network.dto.model.LocationModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationRepositories {
    public final MutableLiveData<Boolean> _Loading = new MutableLiveData<>();

    public MutableLiveData<ArrayList<LocationModel>> fetchLocations(int page) {
        MutableLiveData<ArrayList<LocationModel>> data = new MutableLiveData<>();
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

    public MutableLiveData<LocationModel> fetchLocation(int id) {
        MutableLiveData<LocationModel> _location = new MutableLiveData<>();
        _Loading.setValue(true);
        App.locationApiService.fetchLocations(id).enqueue(new Callback<LocationModel>() {
            @Override
            public void onResponse(Call<LocationModel> call, Response<LocationModel> response) {
                _location.setValue(response.body());
                _Loading.setValue(false);
            }

            @Override
            public void onFailure(Call<LocationModel> call, Throwable t) {
                _location.setValue(null);
                _Loading.setValue(false);
            }
        });
        return _location;
    }
}
