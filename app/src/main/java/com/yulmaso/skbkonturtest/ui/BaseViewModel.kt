package com.yulmaso.skbkonturtest.ui

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel: ViewModel() {

    val isLoading = ObservableBoolean(false)

    var requestListener: RequestListener? = null

    // Disposable to dispose rx objects
    protected val disposables = CompositeDisposable()

    // Disposing disposables
    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    fun handleRequestFailure(ex: Throwable) {
        ex.message?.let { requestListener?.onFailure(it) }
    }
}