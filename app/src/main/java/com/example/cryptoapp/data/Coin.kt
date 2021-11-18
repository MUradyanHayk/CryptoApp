package com.example.cryptoapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.PropertyKey

@Entity(tableName = "_coin_table")
class Coin {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_primary_key")
    var primaryKey = 0

    @ColumnInfo(name = "_id")
    var id = ""

    @ColumnInfo(name = "_name")
    var name = ""

    @ColumnInfo(name = "_icon")
    var icon = ""

    @ColumnInfo(name = "_symbol")
    var symbol = ""

    @ColumnInfo(name = "_price")
    var price = ""
}