package com.ellah.ellahveehotels.network;

import com.ellah.ellahveehotels.models.HotelSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookingApi {
    @GET("businesses/search")
    Call<HotelSearchResponse> getHotels(
            @Query("location") String location,
            @Query("term") String term
    );
}
