package com.example.android3lesson2.ui.fragment.episode;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android3lesson2.App;
import com.example.android3lesson2.model.EpisodeModel;
import com.example.android3lesson2.model.RickAndMortyResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeViewModel extends ViewModel {
    public MutableLiveData<ArrayList<EpisodeModel>> fetchEpisode() {
        MutableLiveData<ArrayList<EpisodeModel>> data = new MutableLiveData<>();
        App.episodeApiService.fetchEpisode().enqueue(new Callback<RickAndMortyResponse<EpisodeModel>>() {
            @Override
            public void onResponse(@NonNull Call<RickAndMortyResponse<EpisodeModel>> call, @NonNull Response<RickAndMortyResponse<EpisodeModel>> response) {
                ArrayList<EpisodeModel> episodeModels = response.body().getResult();
                data.setValue(episodeModels);
            }

            @Override
            public void onFailure(@NonNull Call<RickAndMortyResponse<EpisodeModel>> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
