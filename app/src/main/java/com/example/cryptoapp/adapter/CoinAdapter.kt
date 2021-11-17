package com.example.cryptoapp.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CoinAdapter(val context: Context, var coins: MutableList<String>) : RecyclerView.Adapter<CoinViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val item = TextView(context)
        return CoinViewHolder(item)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return coins.size
    }
}

class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)