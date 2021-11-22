package com.example.android3lesson2.data.apiServise;

import com.example.android3lesson2.dto.model.LocationModel;
import com.example.android3lesson2.dto.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LocationApiService {

    @GET("api/location")
    Call<RickAndMortyResponse<LocationModel>> fetchLocation(
            @Query("page") int page
    );

    @GET("api/location/{id}")
    Call<LocationModel> fetchLocations(
            @Path("id") int id
    );
}
