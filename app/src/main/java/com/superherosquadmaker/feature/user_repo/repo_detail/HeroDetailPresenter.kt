package com.superherosquadmaker.feature.user_repo.repo_detail

import com.superherosquadmaker.R
import com.superherosquadmaker.annotation.PerFragment
import com.superherosquadmaker.entity.MarvelHero
import com.superherosquadmaker.feature.user_repo.repo_detail.viewstate.HeroSquadButtonViewState
import com.superherosquadmaker.feature.user_repo.viewstate.IHeroMapperViewState
import com.superherosquadmaker.feature.user_repo.viewstate.ImageSize
import javax.inject.Inject

@PerFragment
class HeroDetailPresenter @Inject constructor(private val mapperViewState: IHeroMapperViewState) :
    IHeroDetailPresenter {

    override var view: HeroDetailView? = null

    override fun displayRepositoryDetail(value: MarvelHero) {
        view?.displayRepositoryDetail(mapperViewState.mapEntityToViewState(value, ImageSize.PICTURE_FULL_SIZE_STYLE))
    }

    override fun displayRemoveHeroButton() {
        view?.displayButton(HeroSquadButtonViewState(true, R.drawable.bg_button_remove, R.string.fireFromSquad))
    }

    override fun displayAddHeroButton() {
        view?.displayButton(HeroSquadButtonViewState(false, R.drawable.bg_button_add, R.string.hireToSquad))
    }

    override fun displayError() {
        view?.displayError()
    }
}