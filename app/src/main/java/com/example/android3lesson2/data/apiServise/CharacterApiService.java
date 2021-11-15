package com.example.android3lesson2.data.apiServise;

import com.example.android3lesson2.model.CharacterModel;
import com.example.android3lesson2.model.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CharacterApiService {
    @GET("api/character")
    Call<RickAndMortyResponse<CharacterModel>> fetchCharacter(
    );

    @GET("api/character/{id}")
    Call<RickAndMortyResponse<CharacterModel>> fetchCharacters(
            @Path("id") int id
    );
}
