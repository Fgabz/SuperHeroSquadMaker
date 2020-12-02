package com.superherosquadmaker.feature.user_repo.listing.di

import androidx.lifecycle.ViewModel
import com.superherosquadmaker.annotation.PerFragment
import com.superherosquadmaker.annotation.ViewModelKey
import com.superherosquadmaker.domain.FetchMarvelHeroListUseCase
import com.superherosquadmaker.domain.FetchSquadListUseCase
import com.superherosquadmaker.feature.user_repo.listing.HeroInteractor
import com.superherosquadmaker.feature.user_repo.listing.HeroPresenter
import com.superherosquadmaker.feature.user_repo.listing.HeroViewController
import com.superherosquadmaker.feature.user_repo.listing.HeroesFragment
import com.superherosquadmaker.feature.user_repo.listing.IHeroPresenter
import com.superherosquadmaker.feature.user_repo.viewstate.HeroMapperViewState
import com.superherosquadmaker.feature.user_repo.viewstate.IHeroMapperViewState
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class HeroListModule {

    @Module
    abstract class Provider {
        @PerFragment
        @ContributesAndroidInjector(modules = [HeroListModule::class])
        abstract fun contributeHeroFragmentInjector(): HeroesFragment
    }

    @Binds
    abstract fun provideFetchUseCase(interactor: HeroInteractor): FetchMarvelHeroListUseCase

    @Binds
    abstract fun provideFetchSquadListUseCase(interactor: HeroInteractor): FetchSquadListUseCase

    @Binds
    abstract fun providePresenter(presenter: HeroPresenter): IHeroPresenter

    @Binds
    abstract fun provideMapper(mapper: HeroMapperViewState): IHeroMapperViewState

    @Binds
    @IntoMap
    @ViewModelKey(HeroViewController::class)
    abstract fun bindListViewModel(viewModel: HeroViewController): ViewModel
}