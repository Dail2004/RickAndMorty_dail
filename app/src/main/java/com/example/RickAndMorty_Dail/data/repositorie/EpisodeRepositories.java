package com.example.RickAndMorty_Dail.data.repositorie;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.RickAndMorty_Dail.App;
import com.example.RickAndMorty_Dail.data.network.dto.RickAndMortyResponse;
import com.example.RickAndMorty_Dail.data.network.dto.EpisodeModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeRepositories {
    public final MutableLiveData<Boolean> _Loading = new MutableLiveData<>();

    public MutableLiveData<ArrayList<EpisodeModel>> fetchEpisodes(int page) {
        MutableLiveData<ArrayList<EpisodeModel>> data = new MutableLiveData<>();
        _Loading.setValue(true);
        App.episodeApiService.fetchEpisodes(page).enqueue(new Callback<RickAndMortyResponse<EpisodeModel>>() {
            @Override
            public void onResponse(@NonNull Call<RickAndMortyResponse<EpisodeModel>> call, @NonNull Response<RickAndMortyResponse<EpisodeModel>> response) {
                if (response.body() != null) {
                    ArrayList<EpisodeModel> episodeModels = response.body().getResult();
                    data.setValue(episodeModels);
                }
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

    public MutableLiveData<EpisodeModel> fetchEpisode(int id) {
        MutableLiveData<EpisodeModel> _episode = new MutableLiveData<>();
        _Loading.setValue(true);
        App.episodeApiService.fetchEpisode(id).enqueue(new Callback<EpisodeModel>() {
            @Override
            public void onResponse(Call<EpisodeModel> call, Response<EpisodeModel> response) {
                    _episode.setValue(response.body());
                    _Loading.setValue(false);
            }

            @Override
            public void onFailure(Call<EpisodeModel> call, Throwable t) {
                _episode.setValue(null);
                _Loading.setValue(false);
            }
        });
        return _episode;
    }
}
