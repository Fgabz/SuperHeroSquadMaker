package com.superherosquadmaker.feature.user_repo.viewstate

import com.superherosquadmaker.entity.MarvelHero

interface IHeroMapperViewState {
    fun mapEntityToViewState(item: MarvelHero, imageSize: ImageSize): HeroViewState
}

enum class ImageSize(val size: String) {
    PICTURE_PORTRAIT_FANTASTIC_STYLE("/portrait_fantastic"),
    PICTURE_PORTRAIT_MEDIUM_STYLE("/portrait_medium"),
    PICTURE_FULL_SIZE_STYLE("")
}