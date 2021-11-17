package com.example.cryptoapp.viewModel

import androidx.lifecycle.ViewModel
import com.example.cryptoapp.repository.CoinRepository

class CoinViewModel : ViewModel() {
    private var repository: CoinRepository? = null
    fun initializeViewModel(repository: CoinRepository?) {
        this.repository = repository
    }
}