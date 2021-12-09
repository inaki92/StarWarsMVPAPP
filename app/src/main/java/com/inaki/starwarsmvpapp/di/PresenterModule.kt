package com.inaki.starwarsmvpapp.di

import android.net.ConnectivityManager
import com.inaki.starwarsmvpapp.presenters.CharactersPresenter
import com.inaki.starwarsmvpapp.presenters.ICharactersPresenter
import com.inaki.starwarsmvpapp.rest.StarWarsApi
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class PresenterModule {

    @Provides
    @Singleton
    fun providesCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @Singleton
    fun provideCharactersPresenter(api: StarWarsApi, connectivityManager: ConnectivityManager, compositeDisposable: CompositeDisposable): ICharactersPresenter {
        return CharactersPresenter(api, connectivityManager, compositeDisposable)
    }
}