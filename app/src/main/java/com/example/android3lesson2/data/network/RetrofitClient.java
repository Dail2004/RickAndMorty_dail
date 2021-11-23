package com.example.android3lesson2.data.network;

import com.example.android3lesson2.data.apiservice.CharacterApiservice;
import com.example.android3lesson2.data.apiservice.EpisodeApiservice;
import com.example.android3lesson2.data.apiservice.LocationApiservice;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(provideLoggingInterceptor())
            .build();

    private HttpLoggingInterceptor provideLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build();

    public CharacterApiservice provideCharacterApiService() {
        return retrofit.create(CharacterApiservice.class);
    }

    public EpisodeApiservice provideEpisodeApiService() {
        return retrofit.create(EpisodeApiservice.class);
    }

    public LocationApiservice provideLocationApiService() {
        return retrofit.create(LocationApiservice.class);
    }

}
