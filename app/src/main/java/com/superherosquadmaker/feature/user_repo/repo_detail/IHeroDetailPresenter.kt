package com.superherosquadmaker.feature.user_repo.repo_detail

import com.superherosquadmaker.core.ui.BasePresenter
import com.superherosquadmaker.entity.MarvelHero

interface IHeroDetailPresenter: BasePresenter<HeroDetailView> {
    fun displayError()
    fun displayRepositoryDetail(value: MarvelHero)
    fun displayRemoveHeroButton()
    fun displayAddHeroButton()
}