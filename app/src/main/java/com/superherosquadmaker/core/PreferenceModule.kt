package com.superherosquadmaker.core

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.superherosquadmaker.BuildConfig
import com.superherosquadmaker.annotation.MarvelPrivate
import com.superherosquadmaker.annotation.MarvelPublic
import com.superherosquadmaker.annotation.PerApp
import com.superherosquadmaker.domain.IPreference
import dagger.Module
import dagger.Provides

@Module
class PreferenceModule {

    @Provides
    @PerApp
    fun provideSharedPreferences(app: Application): SharedPreferences {
        return app.getSharedPreferences("com.superherosquadmaker.prefs", Context.MODE_PRIVATE)
    }

    @Provides
    @PerApp
    @MarvelPublic
    fun providesMarvelPublic(preferences: SharedPreferences): IPreference<String> {
        return StringPreference(MARVEL_PUBLIC, preferences, BuildConfig.MARVEL_PUBLIC)
    }

    @Provides
    @PerApp
    @MarvelPrivate
    fun providesMarvelPrivate(preferences: SharedPreferences): IPreference<String> {
        return StringPreference(MARVEL_PRIVATE, preferences, BuildConfig.MARVEL_PRIVATE)
    }

    companion object {
        const val MARVEL_PUBLIC = "marvel_public"
        const val MARVEL_PRIVATE = "marvel_private"
    }
}