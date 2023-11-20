package com.example.testapplication.di.modules

import android.content.Context
import com.example.api.api.IHotelAPI
import com.example.testapplication.di.scopes.MainActivityScope
import com.example.util.network.AndroidNetworkStatus
import com.example.util.network.INetworkStates
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class ApiModule {

    @Named("baseURL")
    @Provides
    fun baseURL(): String = "https://run.mocky.io/"

    @MainActivityScope
    @Provides
    fun api(@Named("baseURL") baseURL: String, gson: Gson): IHotelAPI =
        Retrofit.Builder().baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(IHotelAPI::class.java)

    @MainActivityScope
    @Provides
    fun gson(): Gson = GsonBuilder().setLenient().create()

    @MainActivityScope
    @Provides
    fun networkStatus(context: Context): INetworkStates =
        AndroidNetworkStatus(context)
}