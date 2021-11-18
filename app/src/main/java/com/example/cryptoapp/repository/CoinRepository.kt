package com.example.cryptoapp.repository

import com.example.cryptoapp.data.CoinsResponse
import com.example.cryptoapp.database.CoinDatabase
import com.example.cryptoapp.network.RetrofitInstance
import retrofit2.Response

class CoinRepository(db: CoinDatabase) {
    suspend fun getAllCoins(): Response<CoinsResponse> {
        return RetrofitInstance.api.getCoins()
    }
}