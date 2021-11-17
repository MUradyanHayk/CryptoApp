package com.example.cryptoapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.data.CoinsResponse
import com.example.cryptoapp.repository.CoinRepository
import com.example.cryptoapp.utils.Download
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Response

class CoinViewModel : ViewModel() {
    private var repository: CoinRepository? = null
    var download: MutableLiveData<Download<CoinsResponse>> = MutableLiveData()

    fun initializeViewModel(repository: CoinRepository?) {
        this.repository = repository
    }

    fun getAllCoins(): Job {
        return viewModelScope.launch(Dispatchers.IO) {
            safeCoinCall()
        }
    }

    private suspend fun safeCoinCall() {
        this.download.postValue(Download.Loading())
        val response = repository?.getAllCoins()
        if (response != null) {
            this.download.postValue(handleResponse(response))
        } else {
            this.download.postValue(Download.Error("response is null"))
        }
    }

    private fun handleResponse(response: Response<CoinsResponse>): Download<CoinsResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Download.Success(it)
            }
        }
        return Download.Error(response.message())
    }
}