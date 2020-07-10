package com.yulmaso.skbkonturtest.ui.contacts

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yulmaso.skbkonturtest.data.Repository
import com.yulmaso.skbkonturtest.data.model.Contact
import com.yulmaso.skbkonturtest.ui.BaseViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class ContactsViewModel @Inject constructor(
    private val repository: Repository
): BaseViewModel() {

    val refreshing = ObservableBoolean()

    // Input of contacts_search_et
    val searchInput = MutableLiveData<String>()

    // Contacts data holder
    private val contactsMLive = MutableLiveData<List<Contact>>()
    val contacts: LiveData<List<Contact>> = contactsMLive

    fun updateData() {
        // For disabling endless progress of swipeRefreshLayout
        refreshing.set(true)
        refreshing.set(false)

        clearSearchInput()
        val resultList: MutableList<Contact> = ArrayList()
        val firstList = repository.getContactsFromFirstSource()
        val secondList = repository.getContactsFromSecondSource()
        val thirdList = repository.getContactsFromThirdSource()
        isLoading.set(true)
        disposables.add(
            Observable.merge(firstList, secondList, thirdList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    resultList.addAll(it)
                    contactsMLive.value = resultList
                    isLoading.set(false)
                }, {
                    isLoading.set(false)
                    handleRequestFailure(it)
                })
        )
    }

    fun clearSearchInput() {
        searchInput.value = ""
    }
}