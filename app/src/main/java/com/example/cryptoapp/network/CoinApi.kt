package com.example.cryptoapp.network

import com.example.cryptoapp.data.CoinsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinApi {
//    @GET("coins?skip=0&limit=25&currency=EUR")
    @GET("coins?currency=EUR")
    suspend fun getCoins(@Query("skip") skip: Int, @Query("limit") limit: Int): Response<CoinsResponse>
}