package com.example.android3lesson2.data.network.apiservice;

import com.example.android3lesson2.data.network.dto.model.CharacterModel;
import com.example.android3lesson2.data.network.dto.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CharacterApiservice {
    @GET("api/character")
    Call<RickAndMortyResponse<CharacterModel>> fetchCharacters(
            @Query("page") int page
    );

    @GET("api/character/{id}")
    Call<CharacterModel> fetchCharacter(
            @Path("id") int id
    );
}
