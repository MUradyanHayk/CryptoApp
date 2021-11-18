package com.example.cryptoapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cryptoapp.data.Coin

@Dao
interface CoinDao {
    @Query("SELECT * FROM _coin_table")
    fun getSavedCoins(): LiveData<MutableList<Coin>>

    @Query("SELECT * FROM _coin_table WHERE _is_favorite = 1")
    fun getFavoriteCoins(): LiveData<MutableList<Coin>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(coin: Coin)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(coin: Coin)

    @Delete
    fun delete(coin: Coin)
}