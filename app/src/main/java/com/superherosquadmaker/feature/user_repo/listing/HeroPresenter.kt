package com.superherosquadmaker.feature.user_repo.listing

import com.superherosquadmaker.annotation.PerFragment
import com.superherosquadmaker.entity.MarvelHero
import com.superherosquadmaker.feature.user_repo.viewstate.IHeroMapperViewState
import com.superherosquadmaker.feature.user_repo.viewstate.ImageSize
import javax.inject.Inject

@PerFragment
class HeroPresenter @Inject constructor(private val mapperViewState: IHeroMapperViewState) : IHeroPresenter {

    override var view: HeroView? = null

    override fun displayList(value: List<MarvelHero>) {
        view?.displayList(value.map { item -> mapperViewState.mapEntityToViewState(item, ImageSize.PICTURE_PORTRAIT_FANTASTIC_STYLE) })
    }

    override fun displayError() {
        view?.displayError()
    }

    override fun displaySquad(squad: List<MarvelHero>) {
        view?.displaySquad(squad.map { item -> mapperViewState.mapEntityToViewState(item, ImageSize.PICTURE_PORTRAIT_MEDIUM_STYLE) })
    }

    override fun hideSquad() {
        view?.hideSquad()
    }
}