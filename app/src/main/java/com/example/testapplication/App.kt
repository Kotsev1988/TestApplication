package com.example.testapplication

import android.app.Application
import com.example.booking.di.BookingDepsStore
import com.example.hotel.di.ArticleDepsStore
import com.example.number.di.RoomsDepsStore
import com.example.testapplication.di.AppComponent
import com.example.testapplication.di.DaggerAppComponent
import com.example.testapplication.di.modules.AppModule

class App: Application() {

    companion object {
        lateinit var instance: App
    }

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        ArticleDepsStore.deps = appComponent
        RoomsDepsStore.deps = appComponent
        BookingDepsStore.deps = appComponent
    }
}