package com.example.android3lesson2.ui.fragment.episode;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android3lesson2.data.network.dto.model.EpisodeModel;
import com.example.android3lesson2.data.repositorie.EpisodeRepositories;

import java.util.ArrayList;

public class EpisodeViewModel extends ViewModel {
    private final EpisodeRepositories repositories = new EpisodeRepositories();
    public final LiveData<Boolean> isLoading = repositories._Loading;
    public int page = 1;

    public MutableLiveData<ArrayList<EpisodeModel>> fetchEpisodes() {
        return repositories.fetchEpisodes(page);
    }
}
