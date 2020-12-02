package com.superherosquadmaker.feature.user_repo.repo_detail.di

import androidx.lifecycle.ViewModel
import com.superherosquadmaker.annotation.PerFragment
import com.superherosquadmaker.annotation.ViewModelKey
import com.superherosquadmaker.domain.FetchHeroUseCase
import com.superherosquadmaker.domain.ChangeHeroSquadUseCase
import com.superherosquadmaker.feature.user_repo.repo_detail.IHeroDetailPresenter
import com.superherosquadmaker.feature.user_repo.repo_detail.HeroDetailFragment
import com.superherosquadmaker.feature.user_repo.repo_detail.HeroDetailInteractor
import com.superherosquadmaker.feature.user_repo.repo_detail.HeroDetailPresenter
import com.superherosquadmaker.feature.user_repo.repo_detail.HeroDetailViewController
import com.superherosquadmaker.feature.user_repo.viewstate.HeroMapperViewState
import com.superherosquadmaker.feature.user_repo.viewstate.IHeroMapperViewState
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class HeroDetailModule {
    @Module
    abstract class Provider {
        @PerFragment
        @ContributesAndroidInjector(modules = [HeroDetailModule::class])
        abstract fun contributeRepositoryDetailFragmentInjector(): HeroDetailFragment
    }

    @Binds
    abstract fun provideFetchUseCase(interactor: HeroDetailInteractor): FetchHeroUseCase

    @Binds
    abstract fun provideFetchIssuesUseCase(interactor: HeroDetailInteractor): ChangeHeroSquadUseCase

    @Binds
    abstract fun providePresenter(presenter: HeroDetailPresenter): IHeroDetailPresenter

    @Binds
    abstract fun provideMapper(mapper: HeroMapperViewState): IHeroMapperViewState

    @Binds
    @IntoMap
    @ViewModelKey(HeroDetailViewController::class)
    abstract fun bindRepoDetailViewModel(viewModel: HeroDetailViewController): ViewModel
}