package com.example.android3lesson2.ui.fragment.location;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android3lesson2.App;
import com.example.android3lesson2.model.LocationModel;
import com.example.android3lesson2.model.RickAndMortyResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationViewModel extends ViewModel {
    MutableLiveData<ArrayList<LocationModel>> fetchLocation() {
        MutableLiveData<ArrayList<LocationModel>> data = new MutableLiveData<>();
        App.locationApiService.fetchLocation().enqueue(new Callback<RickAndMortyResponse<LocationModel>>() {
            @Override
            public void onResponse(@NonNull Call<RickAndMortyResponse<LocationModel>> call, @NonNull Response<RickAndMortyResponse<LocationModel>> response) {
                ArrayList<LocationModel> episodeModels = response.body().getResult();
                data.setValue(episodeModels);
            }

            @Override
            public void onFailure(@NonNull Call<RickAndMortyResponse<LocationModel>> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
