package com.example.android3lesson2.ui.fragment.location.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android3lesson2.App;
import com.example.android3lesson2.dto.model.LocationModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationDetailViewModel extends ViewModel {
    private final MutableLiveData<LocationModel> _location = new MutableLiveData<>();
    public final LiveData<LocationModel> location = _location;
    private final MutableLiveData<Boolean> _loading = new MutableLiveData<>();
    public final LiveData<Boolean> loading  = _loading;

    public void fetchLocation(int id){
        _loading.setValue(true);
        App.locationApiService.fetchLocations(id).enqueue(new Callback<LocationModel>() {
            @Override
            public void onResponse(Call<LocationModel> call, Response<LocationModel> response) {
                _location.setValue(response.body());
                _loading.setValue(false);
            }

            @Override
            public void onFailure(Call<LocationModel> call, Throwable t) {
                _location.setValue(null);
                _loading.setValue(false);
            }
        });
    }
}
