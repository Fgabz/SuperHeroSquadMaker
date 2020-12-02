package com.superherosquadmaker.feature.user_repo.repo_detail.viewstate

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ErrorViewState(
    val message: String = "Hero not found"
)

data class HeroSquadButtonViewState(
    val isInSquad: Boolean,
    @DrawableRes val backgroundRes: Int,
    @StringRes val messageRes: Int
)