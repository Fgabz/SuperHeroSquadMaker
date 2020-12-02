package com.superherosquadmaker.feature.user_repo.repo_detail

import com.superherosquadmaker.annotation.PerFragment
import com.superherosquadmaker.domain.ChangeHeroSquadUseCase
import com.superherosquadmaker.domain.FetchHeroUseCase
import com.superherosquadmaker.entity.Answer
import com.superherosquadmaker.entity.MarvelHero
import com.superherosquadmaker.entity.onFailure
import com.superherosquadmaker.entity.onSuccess
import com.superherosquadmaker.repository.MarvelHeroDataSource
import com.superherosquadmaker.repository.SquadDataSource
import javax.inject.Inject

@PerFragment
class HeroDetailInteractor @Inject constructor(
    private val marvelHeroDatasource: MarvelHeroDataSource,
    private val squadDataSource: SquadDataSource,
    private val presenter: IHeroDetailPresenter
) : FetchHeroUseCase, ChangeHeroSquadUseCase {

    var marvelHero: MarvelHero? = null

    override suspend fun fetchHeroUseCase(id: Int) {
        marvelHeroDatasource.fetchHeroById(id).onSuccess {
            marvelHero = it
            presenter.displayRepositoryDetail(it)

            if (isInSquad(id)) {
                presenter.displayRemoveHeroButton()
            } else {
                presenter.displayAddHeroButton()
            }
        }.onFailure { _, _, _ ->
            presenter.displayError()
        }
    }

    override suspend fun changeHeroSquad(id: Int) {
        if (isInSquad(id)) {
            squadDataSource.removeHeroFromSquad(id)
            presenter.displayAddHeroButton()
        } else {
            squadDataSource.addHeroInSquad(id, checkNotNull(marvelHero))
            presenter.displayRemoveHeroButton()
        }
    }

    private suspend fun isInSquad(id: Int) = (squadDataSource.fetchHeroSquadById(id) as? Answer.Success)?.value != null
}