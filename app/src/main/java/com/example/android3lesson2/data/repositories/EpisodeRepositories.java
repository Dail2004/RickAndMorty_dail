package com.example.android3lesson2.data.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android3lesson2.App;
import com.example.android3lesson2.dto.RickAndMortyResponse;
import com.example.android3lesson2.dto.model.EpisodeModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeRepositories extends ViewModel {
    private final MutableLiveData<ArrayList<EpisodeModel>> data = new MutableLiveData<>();
    private final MutableLiveData<Boolean> _Loading = new MutableLiveData<>();
    public final LiveData<Boolean> isLoading = _Loading;

    public MutableLiveData<ArrayList<EpisodeModel>> fetchEpisode(int page) {
        _Loading.setValue(true);
        App.episodeApiService.fetchEpisodes(page).enqueue(new Callback<RickAndMortyResponse<EpisodeModel>>() {
            @Override
            public void onResponse(@NonNull Call<RickAndMortyResponse<EpisodeModel>> call, @NonNull Response<RickAndMortyResponse<EpisodeModel>> response) {
                ArrayList<EpisodeModel> episodeModels = response.body().getResult();
                data.setValue(episodeModels);
                _Loading.setValue(false);
            }

            @Override
            public void onFailure(@NonNull Call<RickAndMortyResponse<EpisodeModel>> call, @NonNull Throwable t) {
                data.setValue(null);
                _Loading.setValue(false);
            }
        });
        return data;
    }
}