package com.example.android3lesson2.ui.fragment.episode;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android3lesson2.data.repositorie.RickAndMortyRepositories;
import com.example.android3lesson2.data.network.dto.model.EpisodeModel;

import java.util.ArrayList;

public class EpisodeViewModel extends ViewModel {
    private final RickAndMortyRepositories repositories = new RickAndMortyRepositories();
    private final MutableLiveData<Boolean> _Loading = new MutableLiveData<>();
    public final LiveData<Boolean> isLoading = _Loading;
    public int page = 1;

    public MutableLiveData<ArrayList<EpisodeModel>> fetchEpisodes() {
        return repositories.fetchEpisode(page);
    }
}
