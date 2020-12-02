package com.superherosquadmaker.service.di

import com.superherosquadmaker.annotation.PerApp
import com.superherosquadmaker.repository.service.IRemoteMarvelService
import com.superherosquadmaker.service.api.RemoteMarvelService
import com.superherosquadmaker.service.api.interceptor.AuthorizationInterceptor
import com.superherosquadmaker.service.api.webservice.IMarvelHeroesWebService
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module(includes = [RemoteModule.AbstractionModule::class])
class RemoteModule {

    @Module
    abstract class AbstractionModule {
        @Binds
        abstract fun provideAuthorizationInterceptor(interceptor: AuthorizationInterceptor): Interceptor

        @Binds
        abstract fun provideRemoteMarvelService(remoteMarvelService: RemoteMarvelService): IRemoteMarvelService
    }

    @PerApp
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    @PerApp
    @Provides
    fun providesOkHttpClient(authorizationInterceptor: AuthorizationInterceptor, loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {

        val httpClient = OkHttpClient.Builder()
        httpClient.readTimeout(12, TimeUnit.SECONDS)

        httpClient
            .addInterceptor(authorizationInterceptor)
            .addInterceptor(loggingInterceptor)

        return httpClient.build()
    }

    @PerApp
    @Provides
    fun providesRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @PerApp
    @Provides
    fun providesMarvelHeroesWebService(retrofit: Retrofit): IMarvelHeroesWebService {
        return retrofit.create(IMarvelHeroesWebService::class.java)
    }
}