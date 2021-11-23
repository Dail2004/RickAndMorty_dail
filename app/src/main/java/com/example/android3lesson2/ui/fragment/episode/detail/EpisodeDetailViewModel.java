package com.example.android3lesson2.ui.fragment.episode.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android3lesson2.App;
import com.example.android3lesson2.data.network.dto.model.CharacterModel;
import com.example.android3lesson2.data.network.dto.model.EpisodeModel;
import com.example.android3lesson2.data.repositorie.EpisodeRepositories;
import com.example.android3lesson2.data.repositorie.RickAndMortyRepositories;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeDetailViewModel extends ViewModel {
    private final EpisodeRepositories repositories = new EpisodeRepositories();
    public final LiveData<Boolean> loading = repositories._Loading;


    public MutableLiveData<EpisodeModel> fetchEpisode(int id) {
        return repositories.fetchEpisode(id);
    }
}