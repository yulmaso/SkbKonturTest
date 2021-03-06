package com.yulmaso.skbkonturtest.ui

import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment: DaggerFragment() {
    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory
}