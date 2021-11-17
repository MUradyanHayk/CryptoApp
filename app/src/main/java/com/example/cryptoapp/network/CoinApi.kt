package com.example.cryptoapp.network

import com.example.cryptoapp.data.CoinsResponse
import retrofit2.Response
import retrofit2.http.GET

interface CoinApi {
    @GET("coins?skip=0&limit=25&currency=EUR")
    suspend fun getCoins(): Response<CoinsResponse>
}