package com.ellah.ellahveehotels;

import com.ellah.ellahveehotels.models.BookingApiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookingApi {
    @GET("hotels/locations")
    Call<List<BookingApiResponse>> getHotels(
    @Query("locale") String locale,
    @Query("name") String name
    );
}
