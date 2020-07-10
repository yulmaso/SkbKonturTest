package com.yulmaso.skbkonturtest.data

import com.yulmaso.skbkonturtest.data.web.ContactsService
import com.yulmaso.skbkonturtest.utils.Constants.SOURCE_1
import com.yulmaso.skbkonturtest.utils.Constants.SOURCE_2
import com.yulmaso.skbkonturtest.utils.Constants.SOURCE_3
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val contactsService: ContactsService
){
    fun getContactsFromFirstSource() = contactsService.getContacts(SOURCE_1)
    fun getContactsFromSecondSource() = contactsService.getContacts(SOURCE_2)
    fun getContactsFromThirdSource() = contactsService.getContacts(SOURCE_3)
}