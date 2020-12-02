package com.superherosquadmaker.feature.user_repo.listing

import com.superherosquadmaker.core.ui.BasePresenter
import com.superherosquadmaker.entity.MarvelHero

interface IHeroPresenter : BasePresenter<HeroView> {
    fun displayList(value: List<MarvelHero>)
    fun displayError()
    fun displaySquad(squad: List<MarvelHero>)
    fun hideSquad()
}