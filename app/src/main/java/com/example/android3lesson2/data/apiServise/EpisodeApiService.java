package com.example.android3lesson2.data.apiServise;

import com.example.android3lesson2.dto.model.EpisodeModel;
import com.example.android3lesson2.dto.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EpisodeApiService {
    @GET("api/episode")
    Call<RickAndMortyResponse<EpisodeModel>> fetchEpisode(
    );

    @GET("api/episode{id}")
    Call<EpisodeModel> fetchEpisodes(
            @Path("id") int id
    );
}
