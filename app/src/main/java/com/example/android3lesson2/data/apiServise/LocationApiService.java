package com.example.android3lesson2.data.apiServise;

import com.example.android3lesson2.model.LocationModel;
import com.example.android3lesson2.model.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LocationApiService {

    @GET("api/location")
    Call<RickAndMortyResponse<LocationModel>> fetchLocation();

    @GET("api/location{id}")
    Call<RickAndMortyResponse<LocationModel>> fetchLocations(
            @Path("id") int id
    );
}
