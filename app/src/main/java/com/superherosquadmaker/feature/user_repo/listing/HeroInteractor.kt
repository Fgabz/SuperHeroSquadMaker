package com.superherosquadmaker.feature.user_repo.listing

import com.superherosquadmaker.annotation.PerFragment
import com.superherosquadmaker.domain.FetchMarvelHeroListUseCase
import com.superherosquadmaker.domain.FetchSquadListUseCase
import com.superherosquadmaker.entity.Answer
import com.superherosquadmaker.entity.onFailure
import com.superherosquadmaker.entity.onSuccess
import com.superherosquadmaker.repository.MarvelHeroDataSource
import com.superherosquadmaker.repository.SquadDataSource
import javax.inject.Inject

@PerFragment
class HeroInteractor @Inject constructor(
    private val heroDataSource: MarvelHeroDataSource,
    private val squadDataSource: SquadDataSource,
    private val presenter: IHeroPresenter
) : FetchMarvelHeroListUseCase, FetchSquadListUseCase {

    override suspend fun fetchMarvelHeroList() {
        when (val repos = heroDataSource.fetchHeroes()) {
            is Answer.Success -> presenter.displayList(repos.value.sortedBy { it.name })
            is Answer.Failure -> presenter.displayError()
        }
    }

    override suspend fun fetchSquadList() {
        squadDataSource.fetchSquad().onSuccess {
            presenter.displaySquad(it)
        }.onFailure { _, _, _ ->
            presenter.hideSquad()
        }
    }
}