package com.example.cryptoapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cryptoapp.data.Coin

@Dao
interface CoinDao {
    @Query("SELECT * FROM _coin_table")
    fun getSavedCoins(): LiveData<Coin>

    @Insert
    fun insert(coin: Coin)

    @Update
    fun update(coin: Coin)

    @Delete
    fun delete(coin: Coin)
}