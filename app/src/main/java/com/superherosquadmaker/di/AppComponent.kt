package com.superherosquadmaker.di

import android.app.Application
import android.content.Context
import com.superherosquadmaker.SuperHeroSquadMakerApplication
import com.superherosquadmaker.annotation.PerApp
import com.superherosquadmaker.core.PreferenceModule
import com.superherosquadmaker.repository.di.RepositoryModule
import com.superherosquadmaker.service.di.PersistenceModule
import com.superherosquadmaker.service.di.RemoteModule
import com.superherosquadmaker.service.mapper.di.MapperModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

@PerApp
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityBuilder::class,
        RemoteModule::class,
        PersistenceModule::class,
        RepositoryModule::class,
        PreferenceModule::class,
        DaggerFactoryModule::class,
        MapperModule::class
    ]
)
interface AppComponent {

    fun inject(application: SuperHeroSquadMakerApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}