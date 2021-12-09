package com.inaki.starwarsmvpapp

import android.app.Application
import com.inaki.starwarsmvpapp.di.AppModule
import com.inaki.starwarsmvpapp.di.DaggerStarWarsComponent
import com.inaki.starwarsmvpapp.di.StarWarsComponent

class StarWarsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        starWarsComponent = DaggerStarWarsComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {
        lateinit var starWarsComponent: StarWarsComponent
    }
}