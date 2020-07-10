package com.yulmaso.skbkonturtest.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yulmaso.skbkonturtest.data.model.Contact
import com.yulmaso.skbkonturtest.ui.BaseViewModel
import javax.inject.Inject

class ProfileViewModel @Inject constructor(): BaseViewModel() {

    var navigator: ProfileNavigator? = null

    private val contactMLive = MutableLiveData<Contact>()
    val contact: LiveData<Contact> = contactMLive

    fun setContact(contact: Contact) {
        contactMLive.value = contact
    }

    fun onBackBtnClick() {
        navigator?.goBack()
    }

    fun onPhoneClick() {
        navigator?.performPhoneDial()
    }
}