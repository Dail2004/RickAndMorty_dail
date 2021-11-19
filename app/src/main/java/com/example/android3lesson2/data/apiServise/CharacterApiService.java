package com.example.android3lesson2.data.apiServise;

import com.example.android3lesson2.dto.model.CharacterModel;
import com.example.android3lesson2.dto.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CharacterApiService {
    @GET("api/character")
    Call<RickAndMortyResponse<CharacterModel>> fetchCharacters(
    );

    @GET("api/character/{id}")
    Call<CharacterModel> fetchCharacter(
            @Path("id") int id
    );
}
