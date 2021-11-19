package com.example.android3lesson2.ui.fragment.episode.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android3lesson2.App;
import com.example.android3lesson2.dto.RickAndMortyResponse;
import com.example.android3lesson2.dto.model.CharacterModel;
import com.example.android3lesson2.dto.model.EpisodeModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeDetailViewModel extends ViewModel {
    private final MutableLiveData<EpisodeModel> _episode = new MutableLiveData<>();
    public final LiveData<EpisodeModel> character = _episode;
    private final MutableLiveData<Boolean> _loading = new MutableLiveData<>();
    public final LiveData<Boolean> loading = _loading;

    public void fetchEpisode(int id) {
        _loading.setValue(true);
        App.episodeApiService.fetchEpisodes(id).enqueue(new Callback<EpisodeModel>() {
            @Override
            public void onResponse(Call<EpisodeModel> call, Response<EpisodeModel> response) {
                _episode.setValue(response.body());
                _loading.setValue(false);
            }

            @Override
            public void onFailure(Call<EpisodeModel> call, Throwable t) {
                _episode.setValue(null);
                _loading.setValue(false);
            }
        });
    }
}
