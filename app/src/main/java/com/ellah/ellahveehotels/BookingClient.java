package com.ellah.ellahveehotels;

import static com.ellah.ellahveehotels.Constants.BOOKING_API_KEY;
import static com.ellah.ellahveehotels.Constants.BOOKING_BASE_URL;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookingClient {
    private static  Retrofit retrofit = null;

    public static BookingApi getClient() {

        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request newRequest  = chain.request().newBuilder()
                                    .addHeader("X-RapidAPI-Host", "booking-com.p.rapidapi.com")
                                    .addHeader("X-RapidAPI-Key", BOOKING_API_KEY)
                                    .build();
                            return chain.proceed(newRequest);//proceeds with the request
                        }
                    })
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BOOKING_BASE_URL)//
                    .client(okHttpClient)//adds the authorization header
                    .addConverterFactory(GsonConverterFactory.create())//handles data serialization from JSON to Java objects
                    .build();
        }

        return retrofit.create(BookingApi.class);
    }
}
