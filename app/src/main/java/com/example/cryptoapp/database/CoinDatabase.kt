package com.example.cryptoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cryptoapp.data.Coin
import com.example.cryptoapp.utils.Constants

@Database(entities = [Coin::class], version = Constants.DATABASE_VERSION)
abstract class CoinDatabase : RoomDatabase() {
    abstract fun getCoinDao(): CoinDao

    companion object {
        @Volatile
        private var INSTANCE: CoinDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            INSTANCE ?: createDatabase(context).also { INSTANCE = it }
        }

        private fun createDatabase(context: Context): CoinDatabase {
            return Room.databaseBuilder(context, CoinDatabase::class.java, Constants.DATABASE_NAME).build()
        }
    }
}