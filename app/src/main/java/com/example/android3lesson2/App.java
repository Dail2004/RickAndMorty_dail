package com.example.android3lesson2;

import android.app.Application;

import com.example.android3lesson2.data.apiServise.CharacterApiService;
import com.example.android3lesson2.data.apiServise.EpisodeApiService;
import com.example.android3lesson2.data.apiServise.LocationApiService;
import com.example.android3lesson2.data.network.RetrofitClient;

public class App extends Application {
    public static CharacterApiService characterApiService;
    public static EpisodeApiService episodeApiService;
    public static LocationApiService locationApiService;

    @Override
    public void onCreate() {
        super.onCreate();

        characterApiService = new RetrofitClient().provideCharacterApiService();
        episodeApiService = new RetrofitClient().provideEpisodeApiService();
        locationApiService = new RetrofitClient().provideLocationApiService();
    }
}
