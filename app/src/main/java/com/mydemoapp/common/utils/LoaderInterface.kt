package com.mydemoapp.common.utils

interface LoaderInterface {
    fun showLoading()

    fun hideLoading()

    fun showNoInternet()

    fun showError(message: String)
}
