package com.example.cryptoapp.repository

import com.example.cryptoapp.data.CoinsResponse
import com.example.cryptoapp.network.RetrofitInstance
import retrofit2.Response

class CoinRepository() {
    suspend fun getAllCoins(): Response<CoinsResponse> {
        return RetrofitInstance.api.getCoins()
    }
}