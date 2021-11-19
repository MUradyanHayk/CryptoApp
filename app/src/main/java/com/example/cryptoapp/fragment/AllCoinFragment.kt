package com.example.cryptoapp.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.HomeActivity
import com.example.cryptoapp.R
import com.example.cryptoapp.adapter.CoinAdapter
import com.example.cryptoapp.adapter.CoinAdapterDelegate
import com.example.cryptoapp.data.Coin
import com.example.cryptoapp.data.CoinsResponse
import com.example.cryptoapp.utils.Download
import com.example.cryptoapp.viewModel.CoinViewModel
import java.lang.ref.WeakReference

class AllCoinFragment : Fragment(), CoinAdapterDelegate {
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
        coinAdapter?.delegate = WeakReference(this)
        recyclerView?.adapter = coinAdapter
        recyclerView?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (isLastItemDisplayed(recyclerView)) {
                    viewModel?.getAllCoins(requireContext())
                }
            }
        })
        progressBar = screen?.findViewById(R.id.progress_bar)
    }

    private fun isLastItemDisplayed(recyclerView: RecyclerView): Boolean {
        val adapter = recyclerView.adapter ?: return false
        if (adapter.itemCount == 0) {
            return false
        }

        val lastVisibleItemPosition = (recyclerView.layoutManager as? LinearLayoutManager?)?.findLastCompletelyVisibleItemPosition()
        if (lastVisibleItemPosition != RecyclerView.NO_POSITION && lastVisibleItemPosition == adapter.itemCount - 1) {
            return true
        }

        return false
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeCoinViewModel()
    }

    private fun observeCoinViewModel() {
        viewModel?.download?.observe(viewLifecycleOwner, this::observeDownload)
        viewModel?.getSavedCoins()?.observe(viewLifecycleOwner, this::observeSavedCoins)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeDownload(coinResponse: Download<CoinsResponse>) {
        when (coinResponse) {
            is Download.Success -> {
                progressBar?.visibility = View.GONE
//                if (coinResponse.data?.coins?.isNullOrEmpty() == false) {
//                    coinAdapter?.coins?.addAll(coinResponse.data.coins)
//                }
//                coinAdapter?.notifyDataSetChanged()
            }
            is Download.Error -> {
                progressBar?.visibility = View.GONE
                if (!coinResponse.message.isNullOrEmpty()) {
                    Toast.makeText(requireContext(), coinResponse.message, Toast.LENGTH_SHORT).show()
                }
            }
            is Download.Loading -> {
                progressBar?.visibility = View.VISIBLE
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeSavedCoins(coins: MutableList<Coin>) {
        coinAdapter?.coins = coins
        coinAdapter?.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onItemClick(coin: Coin) {
        viewModel?.onItemClick(coin)
        coinAdapter?.notifyDataSetChanged()
//        coin.isFavorite = !coin.isFavorite
//        coinAdapter?.notifyDataSetChanged()
    }
}