package com.example.cryptoapp.repository

import com.example.cryptoapp.data.Coin
import com.example.cryptoapp.network.RetrofitInstance
import retrofit2.Response

class CoinRepository() {
    suspend fun getAllCoins(): Response<Coin> {
        return RetrofitInstance.api.getCoins()
    }
}