package com.superherosquadmaker.service.mapper

import com.superherosquadmaker.entity.MarvelHero
import com.superherosquadmaker.service.api.model.RemoteMarvelCharacter

interface IRemoteMarvelCharacterMapper {
    suspend fun remoteToEntity(remote: RemoteMarvelCharacter): MarvelHero
}