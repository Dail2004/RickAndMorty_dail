package com.example.RickAndMorty_Dail.data.network.apiservice;

import com.example.RickAndMorty_Dail.data.network.dto.EpisodeModel;
import com.example.RickAndMorty_Dail.data.network.dto.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EpisodeApiservice {
    @GET("api/episode")
    Call<RickAndMortyResponse<EpisodeModel>> fetchEpisodes(
            @Query("page") int page
    );

    @GET("api/episode/{id}")
    Call<EpisodeModel> fetchEpisode(
            @Path("id") int id
    );
}
