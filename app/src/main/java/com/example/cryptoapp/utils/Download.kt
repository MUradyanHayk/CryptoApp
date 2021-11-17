package com.example.cryptoapp.utils

sealed class Download<T>(val message: String? = null, val data: T? = null) {
    class Success<T>(data: T?) : Download<T>(data = data)
    class Error<T>(message: String?, data: T? = null) : Download<T>(message, data)
    class Loading<T>() : Download<T>()
}