package com.superherosquadmaker.feature.user_repo.repo_detail

import com.superherosquadmaker.core.ui.BaseView
import com.superherosquadmaker.feature.user_repo.repo_detail.viewstate.HeroSquadButtonViewState
import com.superherosquadmaker.feature.user_repo.viewstate.HeroViewState

interface HeroDetailView : BaseView {
    fun displayRepositoryDetail(value: HeroViewState)
    fun displayError()
    fun displayButton(heroSquadButtonViewState: HeroSquadButtonViewState)
}