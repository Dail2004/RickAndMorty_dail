package com.example.RickAndMorty_Dail.data.network.apiservice;

import com.example.RickAndMorty_Dail.data.network.dto.LocationModel;
import com.example.RickAndMorty_Dail.data.network.dto.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LocationApiservice {

    @GET("api/location")
    Call<RickAndMortyResponse<LocationModel>> fetchLocation(
            @Query("page") int page
    );

    @GET("api/location/{id}")
    Call<LocationModel> fetchLocations(
            @Path("id") int id
    );
}
