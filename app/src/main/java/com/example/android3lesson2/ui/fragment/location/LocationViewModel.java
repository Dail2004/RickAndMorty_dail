package com.example.android3lesson2.ui.fragment.location;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android3lesson2.data.repositories.LocationRepositories;
import com.example.android3lesson2.dto.model.LocationModel;

import java.util.ArrayList;

public class LocationViewModel extends ViewModel {
    private final LocationRepositories repositories = new LocationRepositories();
    private final MutableLiveData<Boolean> _Loading = new MutableLiveData<>();
    public final LiveData<Boolean> isLoading = _Loading;
    public int page = 1;

    public MutableLiveData<ArrayList<LocationModel>> fetchLocation() {
        return repositories.fetchLocation(page);
    }
}
