package com.example.android3lesson2.ui.fragment.location.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android3lesson2.data.network.dto.model.LocationModel;
import com.example.android3lesson2.data.repositorie.LocationRepositories;

public class LocationDetailViewModel extends ViewModel {
    private final LocationRepositories repositories = new LocationRepositories();
    public final LiveData<Boolean> loading = repositories._Loading;


    public MutableLiveData<LocationModel> fetchLocation(int id) {
        return repositories.fetchLocation(id);
    }
}
