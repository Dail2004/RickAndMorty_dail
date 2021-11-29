package com.example.RickAndMorty_Dail.ui.fragment.location;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.RickAndMorty_Dail.data.repositorie.LocationRepositories;
import com.example.RickAndMorty_Dail.data.network.dto.LocationModel;

import java.util.ArrayList;

public class LocationViewModel extends ViewModel {
    private final LocationRepositories repositories = new LocationRepositories();
    public final LiveData<Boolean> isLoading = repositories._Loading;
    public int page = 1;

    public MutableLiveData<ArrayList<LocationModel>> fetchLocations() {
        return repositories.fetchLocations(page);
    }
}
