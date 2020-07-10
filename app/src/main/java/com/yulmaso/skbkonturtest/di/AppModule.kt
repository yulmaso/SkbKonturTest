package com.yulmaso.skbkonturtest.di

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.yulmaso.skbkonturtest.data.web.CalendarDeserializer
import com.yulmaso.skbkonturtest.data.web.ContactsService
import com.yulmaso.skbkonturtest.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    fun provideGsonDateFormat(): Gson =
        GsonBuilder()
            .registerTypeAdapter(Calendar::class.java, CalendarDeserializer())
            .create()

    @Provides
    fun provideOkHttpClient(app: Application): OkHttpClient {
        val cacheSize = (5 * 1024 * 1024).toLong() // 5mb for cache
        val cache = Cache(app.cacheDir, cacheSize)
        return OkHttpClient.Builder()
            .cache(cache)
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    // Takes data from the cache if its age is less than 60 seconds
                    .header("Cache-Control", "public, max-age=" + 60)
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    fun provideContactsService(r: Retrofit) = r.create(ContactsService::class.java)
}