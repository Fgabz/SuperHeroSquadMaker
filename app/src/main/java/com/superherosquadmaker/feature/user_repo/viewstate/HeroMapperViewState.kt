package com.superherosquadmaker.feature.user_repo.viewstate

import com.superherosquadmaker.entity.MarvelHero
import javax.inject.Inject

class HeroMapperViewState @Inject constructor() : IHeroMapperViewState {

    override fun mapEntityToViewState(item: MarvelHero, imageSize: ImageSize) = HeroViewState(
        id = item.id,
        name = item.name,
        description = item.description,
        urlImage = "${item.avatarUrl.replace("(http)".toRegex(), "https")}${imageSize.size}.${item.avatarImageExtension}"
    )
}