package com.example.android3lesson2.data.apiServise;

import com.example.android3lesson2.dto.model.CharacterModel;
import com.example.android3lesson2.dto.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CharacterApiService {
    @GET("api/character")
    Call<RickAndMortyResponse<CharacterModel>> fetchCharacters(
            @Query("page") int page
    );

    @GET("api/character/{id}")
    Call<CharacterModel> fetchCharacter(
            @Path("id") int id
    );
}
