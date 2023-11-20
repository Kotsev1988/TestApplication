package com.example.testapplication.di.modules

import android.app.Application
import android.content.Context
import com.example.testapplication.di.scopes.MainActivityScope
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: Application) {
    @Provides
    @MainActivityScope
    fun provideApp(): Application = app

    @Provides
    @MainActivityScope
    fun provideContext(): Context = app.applicationContext
}