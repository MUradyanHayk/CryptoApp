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
import java.lang.ref.WeakReference

interface CoinAdapterDelegate {
    fun onItemClick(coin:Coin)
}
class CoinAdapter(val context: Context, var coins: MutableList<Coin> = mutableListOf()) : RecyclerView.Adapter<CoinViewHolder>() {
    var delegate:WeakReference<CoinAdapterDelegate>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val item = LayoutInflater.from(context).inflate(R.layout.item_coin, parent, false)
        return CoinViewHolder(item)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.configureItem(coins[position])
        holder.itemView.setOnClickListener {
            delegate?.get()?.onItemClick(coins[position])
        }
    }

    override fun getItemCount(): Int {
        return coins.size
    }
}

class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var nameTextView: TextView? = null
    var priceTextView: TextView? = null
    var favoriteImageView: ImageView? = null
    var iconImageView: ImageView? = null

    init {
        itemView.setBackgroundResource(R.drawable.item_click_background)
        nameTextView = itemView.findViewById(R.id.name_text_view)
        priceTextView = itemView.findViewById(R.id.price_text_view)
        favoriteImageView = itemView.findViewById(R.id.favorite_image_view)
        iconImageView = itemView.findViewById(R.id.icon_image_view)
    }

    fun configureItem(coin: Coin) {
        nameTextView?.text = "${coin.name} (${coin.symbol})"
        priceTextView?.text = coin.price
        Glide.with(itemView.context).load(coin.icon).into(iconImageView!!)
        val favImgRes = if(coin.isFavorite) R.drawable.ic_star_fill else R.drawable.ic_star_border
            favoriteImageView?.setImageResource(favImgRes)
    }
}