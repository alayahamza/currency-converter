package com.example.halaya.currencyconverter;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by halaya on 14/03/2017.
 */

public interface RetrofitObjectAPI {

    /*
     * Retrofit get annotation with our URL
     * And our method that will return us details of student.
    */
    @GET("/latest")
    Call<LatestExchangeRates> getLatestExchangeRates(@Query("base") String defaultCurrency);
}
