package com.example.cryptoapp.adapter

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.R
import com.example.cryptoapp.data.Coin

class CoinAdapter(val context: Context, var coins: MutableList<Coin>? = null) : RecyclerView.Adapter<CoinViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val item = TextView(context)
        return CoinViewHolder(item)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val item = holder.itemView as TextView
        item.text = coins?.get(position)?.name
        item.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
        item.setTextColor(ContextCompat.getColor(context, R.color.black))
    }

    override fun getItemCount(): Int {
        return coins?.size ?: 0
    }
}

class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
//    private var textView: TextView? = null
//
//    init {
//        textView = itemView.findViewById(R.id.te)
//    }
}