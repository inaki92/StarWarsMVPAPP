package com.inaki.starwarsmvpapp.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    /**
     * Right here I am providing the application context to Dagger
     */
    @Provides
    @Singleton
    fun provideContext(): Context = application.applicationContext

    /**
     * Right here I am providing the shared preferences file
     */
    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("MY_SHARED_PREFS", Context.MODE_PRIVATE)
    }

    /**
     * Right here I am providing the connectivity manager
     */
    @Provides
    @Singleton
    fun provideConnectivityManager(context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}