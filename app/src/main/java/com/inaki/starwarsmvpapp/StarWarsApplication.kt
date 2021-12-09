package com.inaki.starwarsmvpapp

import android.app.Application
import com.inaki.starwarsmvpapp.di.AppModule
import com.inaki.starwarsmvpapp.di.DaggerStarWarsComponent
import com.inaki.starwarsmvpapp.di.StarWarsComponent

class StarWarsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // here I am starting the dagger component upon app initialization
        startWarsComponent = DaggerStarWarsComponent
            .builder()
                // I create the app module to be used
            .appModule(AppModule(this))
                // i build the dagger component
            .build()
    }

    companion object {
        // this property I can access to it at any time
        lateinit var startWarsComponent: StarWarsComponent
    }
}