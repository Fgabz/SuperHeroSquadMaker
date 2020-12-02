package com.superherosquadmaker.di

import com.superherosquadmaker.annotation.PerActivity
import com.superherosquadmaker.feature.user_repo.HeroSquadActivity
import com.superherosquadmaker.feature.user_repo.listing.di.HeroListModule
import com.superherosquadmaker.feature.user_repo.repo_detail.di.HeroDetailModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector(
        modules = [
            HeroListModule.Provider::class,
            HeroDetailModule.Provider::class
        ]
    )
    abstract fun contributeHeroActivityInjector(): HeroSquadActivity
}