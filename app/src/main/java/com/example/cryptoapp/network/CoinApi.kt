package com.example.cryptoapp.network

import com.example.cryptoapp.data.Coin
import retrofit2.Response
import retrofit2.http.GET

interface CoinApi {
    @GET("coins?skip=0&limit=5&currency=EUR")
    fun getCoins(): Response<Coin>
}