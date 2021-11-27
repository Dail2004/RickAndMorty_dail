package com.example.RickAndMorty_Dail.ui.fragment.episode.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.RickAndMorty_Dail.data.network.dto.model.EpisodeModel;
import com.example.RickAndMorty_Dail.data.repositorie.EpisodeRepositories;

public class EpisodeDetailViewModel extends ViewModel {
    private final EpisodeRepositories repositories = new EpisodeRepositories();
    public final LiveData<Boolean> loading = repositories._Loading;


    public MutableLiveData<EpisodeModel> fetchEpisode(int id) {
        return repositories.fetchEpisode(id);
    }
}