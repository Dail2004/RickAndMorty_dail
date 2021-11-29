package com.example.RickAndMorty_Dail.ui.fragment.episode;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.RickAndMorty_Dail.data.network.dto.EpisodeModel;
import com.example.RickAndMorty_Dail.data.repositorie.EpisodeRepositories;

import java.util.ArrayList;

public class EpisodeViewModel extends ViewModel {
    private final EpisodeRepositories repositories = new EpisodeRepositories();
    public final LiveData<Boolean> isLoading = repositories._Loading;
    public int page = 1;

    public MutableLiveData<ArrayList<EpisodeModel>> fetchEpisodes() {
        return repositories.fetchEpisodes(page);
    }
}
