package com.superherosquadmaker.di

import com.aircall.application.IApplicationController
import com.superherosquadmaker.ApplicationController
import com.superherosquadmaker.ApplicationInteractor
import com.superherosquadmaker.domain.application.InitializeApplicationUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import org.threeten.bp.Clock

@Module(includes = [AppModule.AbstractionModule::class])
class AppModule {

    @Module
    abstract class AbstractionModule {
        @Binds
        abstract fun provideApplicationController(controller: ApplicationController): IApplicationController

        @Binds
        abstract fun provideInitializeApplicationUseCase(interactor: ApplicationInteractor): InitializeApplicationUseCase
    }

    @Provides
    fun provideClock(): Clock {
        return Clock.systemDefaultZone()
    }
}