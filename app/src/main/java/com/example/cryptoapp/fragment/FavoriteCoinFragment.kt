package com.example.cryptoapp.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.HomeActivity
import com.example.cryptoapp.R
import com.example.cryptoapp.adapter.CoinAdapter
import com.example.cryptoapp.data.Coin
import com.example.cryptoapp.viewModel.CoinViewModel

class FavoriteCoinFragment: Fragment() {
    private var viewModel: CoinViewModel? = null
    var screen: View? = null
    var coinAdapter: CoinAdapter? = null
    var recyclerView: RecyclerView? = null
    var progressBar: ProgressBar? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        screen = inflater.inflate(R.layout.fragment_all_coin, container, false)
        initView()
        viewModel = (activity as? HomeActivity?)?.coinViewModel
        viewModel?.getAllCoins(requireContext())
        return screen
    }

    private fun initView() {
        recyclerView = screen?.findViewById(R.id.recycler_view)
        coinAdapter = CoinAdapter(requireContext())
        recyclerView?.adapter = coinAdapter
        recyclerView?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        progressBar = screen?.findViewById(R.id.progress_bar)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeCoinViewModel()
    }

    private fun observeCoinViewModel() {
        viewModel?.getSavedFavoriteCoins()?.observe(viewLifecycleOwner, this::observeSavedFavoriteCoins)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeSavedFavoriteCoins(coins: MutableList<Coin>) {
        coinAdapter?.coins = coins
        coinAdapter?.notifyDataSetChanged()
    }
}