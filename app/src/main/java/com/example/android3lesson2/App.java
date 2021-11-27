package com.example.android3lesson2;

import android.app.Application;

import com.example.android3lesson2.data.network.apiservice.CharacterApiservice;
import com.example.android3lesson2.data.network.apiservice.EpisodeApiservice;
import com.example.android3lesson2.data.network.apiservice.LocationApiservice;
import com.example.android3lesson2.data.network.RetrofitClient;

public class App extends Application {
    public static CharacterApiservice characterApiService;
    public static EpisodeApiservice episodeApiService;
    public static LocationApiservice locationApiService;

    public static RetrofitClient retrofitClient =  new RetrofitClient();
    @Override
    public void onCreate() {
        super.onCreate();

        characterApiService =retrofitClient.provideCharacterApiService();
        episodeApiService = retrofitClient.provideEpisodeApiService();
        locationApiService = retrofitClient.provideLocationApiService();
    }
}
