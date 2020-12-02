package com.superherosquadmaker

import android.app.Application
import com.aircall.application.IApplicationController
import com.jakewharton.threetenabp.AndroidThreeTen
import com.superherosquadmaker.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class SuperHeroSquadMakerApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var controller: IApplicationController

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)

        DaggerAppComponent
            .builder()
            .application(this)
            .context(this.applicationContext)
            .build()
            .inject(this)

        Timber.plant(Timber.DebugTree())

        GlobalScope.launch {
            controller.onApplicationCreated()
        }
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }
}