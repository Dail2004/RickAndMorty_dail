package com.example.android3lesson2.ui.fragment.location;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android3lesson2.App;
import com.example.android3lesson2.dto.model.LocationModel;
import com.example.android3lesson2.dto.RickAndMortyResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<LocationModel>> data = new MutableLiveData<>();
    private final MutableLiveData<Boolean> _Loading = new MutableLiveData<>();
    public final LiveData<Boolean> isLoading = _Loading;

    MutableLiveData<ArrayList<LocationModel>> fetchLocation() {
        _Loading.setValue(true);
        App.locationApiService.fetchLocation().enqueue(new Callback<RickAndMortyResponse<LocationModel>>() {
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
