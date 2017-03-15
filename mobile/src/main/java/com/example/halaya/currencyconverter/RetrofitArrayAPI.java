package com.example.halaya.currencyconverter;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by halaya on 14/03/2017.
 */

public interface RetrofitArrayAPI {

    /*
     * Retrofit get annotation with our URL
     * And our method that will return us details of student.
    */
    @GET("/latest")
    Call<List<LatestExchangeRates>> getLatestExchangeRates(@Query("base")String defaultCurrency);

}
