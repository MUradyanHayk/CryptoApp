package com.example.cryptoapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.cryptoapp.fragment.AllCoinFragment
import com.example.cryptoapp.fragment.FavoriteCoinFragment

open class CoinFragmentStateAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AllCoinFragment()
            1 -> FavoriteCoinFragment()
            else -> throw IllegalStateException("Fragment with this position not found !!!")
        }
    }
}