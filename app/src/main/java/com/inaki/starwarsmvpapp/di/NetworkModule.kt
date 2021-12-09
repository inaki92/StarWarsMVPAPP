package com.inaki.starwarsmvpapp.di

import com.inaki.starwarsmvpapp.rest.StarWarsApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    /**
     * This method will provide the logging interceptor needed in the [providesOkHttpClient()]
     */
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    /**
     * This method will provide the okHttp client needed in the network provide method
     *
     * @param loggingInterceptor This is an interceptor that will be provided by Dagger
     */
    @Provides
    fun providesOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    /**
     * This method will provide the network API that will be injected in other classes by Dagger
     *
     * @param okHttpClient This is the http client we need in the retrofit object
     */
    @Provides
    fun provideNetworkApi(okHttpClient: OkHttpClient): StarWarsApi {
        return Retrofit.Builder()
            .baseUrl(StarWarsApi.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(StarWarsApi::class.java)
    }
}