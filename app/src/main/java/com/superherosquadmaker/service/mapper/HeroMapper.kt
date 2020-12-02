package com.superherosquadmaker.service.mapper

import com.superherosquadmaker.entity.MarvelHero
import com.superherosquadmaker.service.api.model.RemoteMarvelCharacter
import com.superherosquadmaker.service.db.model.CacheHero
import javax.inject.Inject

class HeroMapper @Inject constructor() : IRemoteMarvelCharacterMapper, IDbMapper<CacheHero, MarvelHero> {
    override suspend fun remoteToEntity(remote: RemoteMarvelCharacter) = MarvelHero(
        id = remote.id,
        description = remote.description ?: NO_REPO_DESCRIPTION,
        name = remote.name,
        avatarUrl = remote.thumbnail.path,
        avatarImageExtension = remote.thumbnail.extension
    )

    override fun cacheToEntity(cache: CacheHero) = MarvelHero(
        id = cache.id,
        description = cache.description,
        name = cache.name,
        avatarUrl = cache.avatarUrl,
        avatarImageExtension = cache.avatarImageExtension
    )

    override fun entityToCache(entity: MarvelHero) = CacheHero(
        id = entity.id,
        description = entity.description,
        name = entity.name,
        avatarUrl = entity.avatarUrl,
        avatarImageExtension = entity.avatarImageExtension
    )

    private companion object {
        const val NO_REPO_DESCRIPTION = "No description"
    }
}