package com.inaki.starwarsmvpapp.di

import android.net.ConnectivityManager
import com.inaki.starwarsmvpapp.presenters.CharactersPresenter
import com.inaki.starwarsmvpapp.presenters.ICharactersPresenter
import com.inaki.starwarsmvpapp.rest.StarWarsApi
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {

    /**
     * This method will provide the presenter needed with the parameters
     * for connectivity manager and network API
     *
     * @param starWarsApi Network API
     * @param connectivityManager Manager to check the network connection
     */
    @Provides
    fun provideCharactersPresenter(starWarsApi: StarWarsApi, connectivityManager: ConnectivityManager): ICharactersPresenter {
        return CharactersPresenter(starWarsApi, connectivityManager)
    }
}