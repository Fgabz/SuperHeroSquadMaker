package com.superherosquadmaker.feature.user_repo.listing

import com.superherosquadmaker.core.ui.BaseView
import com.superherosquadmaker.feature.user_repo.viewstate.HeroViewState

interface HeroView : BaseView {
    fun displayList(value: List<HeroViewState>)
    fun displayError()
    fun displaySquad(squadList: List<HeroViewState>)
    fun hideSquad()
}