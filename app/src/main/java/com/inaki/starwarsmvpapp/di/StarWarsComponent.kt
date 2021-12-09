package com.inaki.starwarsmvpapp.di

import com.inaki.starwarsmvpapp.MainActivity
import com.inaki.starwarsmvpapp.views.CharactersFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    AppModule::class,
    NetworkModule::class,
    PresenterModule::class
])
@Singleton
interface StarWarsComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(charactersFragment: CharactersFragment)
}