package com.yulmaso.skbkonturtest.ui

interface RequestListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}