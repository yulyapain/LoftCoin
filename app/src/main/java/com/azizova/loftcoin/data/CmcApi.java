package com.azizova.loftcoin.data;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CmcApi {
    String API_KEY = "X-CMC_PRO_API_KEY";

    @GET("cryptocurrency/listings/latest")
    Call<Listings> listings();
}
