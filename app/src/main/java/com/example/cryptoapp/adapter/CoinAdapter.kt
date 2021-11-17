package com.example.cryptoapp.adapter

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptoapp.R
import com.example.cryptoapp.data.Coin

class CoinAdapter(val context: Context, var coins: MutableList<Coin>? = null) : RecyclerView.Adapter<CoinViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val item = LayoutInflater.from(context).inflate(R.layout.item_coin, parent, false)
        return CoinViewHolder(item)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        coins?.get(position)?.let {
            holder.configureItem(it)
        }
    }

    override fun getItemCount(): Int {
        return coins?.size ?: 0
    }
}

class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var nameTextView: TextView? = null
    var priceTextView: TextView? = null
    var favoriteImageView: ImageView? = null
    var iconImageView: ImageView? = null

    init {
        nameTextView = itemView.findViewById(R.id.name_text_view)
        priceTextView = itemView.findViewById(R.id.price_text_view)
        favoriteImageView = itemView.findViewById(R.id.favorite_image_view)
        iconImageView = itemView.findViewById(R.id.icon_image_view)
    }

    fun configureItem(coin: Coin) {
        nameTextView?.text = coin.name
        priceTextView?.text = coin.price
        Glide.with(itemView.context).load(coin.icon).into(iconImageView!!)
    }
}