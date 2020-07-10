package com.yulmaso.skbkonturtest.data.web

import com.yulmaso.skbkonturtest.data.model.Contact
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ContactsService {
    @GET("{file}")
    fun getContacts(@Path("file") file: String): Observable<List<Contact>>
}