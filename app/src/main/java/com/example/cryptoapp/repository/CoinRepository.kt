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

    fun deleteAllData() {
        db.getCoinDao().deleteAllData()
    }

    fun updateCoin(coin: Coin) {
        db.getCoinDao().update(coin)
    }

    fun insertCoin(coin: Coin) {
        db.getCoinDao().insert(coin)
    }

    fun insertAllCoin(coins: MutableList<Coin>) {
        for (coin in coins) {
            db.getCoinDao().insert(coin)
        }
    }

    fun insertAllCoinAndUpdateIfNeeded(coins: MutableList<Coin>) {
        for (coin in coins) {
            insertCoin(coin)

            val savedCoin = getSavedCoin(coin.id)
            coin.isFavorite = savedCoin?.isFavorite ?: false
            updateCoin(coin)
        }
    }

    fun updateAllCoin(coins: MutableList<Coin>) {
        for (coin in coins) {
            val savedCoin = getSavedCoin(coin.id)
            coin.isFavorite = savedCoin?.isFavorite ?: false
            db.getCoinDao().update(coin)
        }
    }

    fun deleteCoin(coin: Coin) {
        db.getCoinDao().delete(coin)
    }

    fun getSavedCoin(id: String):Coin? {
        return db.getCoinDao().getSavedCoin(id)
    }
}