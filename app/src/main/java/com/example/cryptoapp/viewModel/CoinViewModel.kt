package com.example.cryptoapp.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.data.Coin
import com.example.cryptoapp.data.CoinsResponse
import com.example.cryptoapp.network.NetworkManager
import com.example.cryptoapp.repository.CoinRepository
import com.example.cryptoapp.utils.Download
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Response

class CoinViewModel : ViewModel() {
    private var repository: CoinRepository? = null
    private var skip: Int = 0
    private var limit: Int = 10

    var download: MutableLiveData<Download<CoinsResponse>> = MutableLiveData()

    fun initializeViewModel(repository: CoinRepository?) {
        this.repository = repository
    }

    fun getAllCoins(context: Context): Job? {
        if (!NetworkManager.isNetworkAvailable(context)) {
            download.postValue(Download.Error("There is no Internet Connection"))
            return null
        }
        return viewModelScope.launch(Dispatchers.IO) {
            safeCoinCall()
        }
    }

    private suspend fun safeCoinCall() {
        this.download.postValue(Download.Loading())
        val response = repository?.getAllCoins(skip, limit)
        if (response != null) {
            this.download.postValue(handleResponse(response))
        } else {
            this.download.postValue(Download.Error("response is null"))
        }
    }

    private fun handleResponse(response: Response<CoinsResponse>): Download<CoinsResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                skip += limit
                return Download.Success(it)
            }
        }
        return Download.Error(response.message())
    }


    fun getSavedCoins(): LiveData<MutableList<Coin>>? {
        return repository?.getSavedCoins()
    }

    fun onItemClick(coin: Coin) {
        coin.isFavorite = !coin.isFavorite
        repository?.updateCoin(coin)
    }
}