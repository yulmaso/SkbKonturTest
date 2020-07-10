package com.yulmaso.skbkonturtest.di

import com.yulmaso.skbkonturtest.MainActivity
import com.yulmaso.skbkonturtest.ui.contacts.ContactsFragment
import com.yulmaso.skbkonturtest.ui.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelModule::class])
abstract class MainActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeContactsFragment(): ContactsFragment

    @ContributesAndroidInjector
    abstract fun contributesProfileFragment(): ProfileFragment
}