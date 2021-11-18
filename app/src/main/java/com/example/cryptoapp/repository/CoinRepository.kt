package com.example.cryptoapp.repository

import androidx.lifecycle.LiveData
import com.example.cryptoapp.data.Coin
import com.example.cryptoapp.data.CoinsResponse
import com.example.cryptoapp.database.CoinDatabase
import com.example.cryptoapp.network.RetrofitInstance
import retrofit2.Response

class CoinRepository(private val db: CoinDatabase) {
    suspend fun getAllCoins(skip: Int, limit: Int): Response<CoinsResponse> {
        return RetrofitInstance.api.getCoins(skip, limit)
    }

    fun getSavedCoins(): LiveData<MutableList<Coin>> {
        return db.getCoinDao().getSavedCoins()
    }

    fun getFavoriteCoins(): LiveData<MutableList<Coin>> {
        return db.getCoinDao().getFavoriteCoins()
    }

    fun updateCoin(coin: Coin) {
        db.getCoinDao().update(coin)
    }

    fun insertCoin(coin: Coin) {
        db.getCoinDao().insert(coin)
    }

    fun deleteCoin(coin: Coin) {
        db.getCoinDao().delete(coin)
    }
}