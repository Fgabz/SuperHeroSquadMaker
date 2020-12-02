package com.superherosquadmaker.repository

import com.superherosquadmaker.entity.Answer
import com.superherosquadmaker.entity.MarvelHero
import com.superherosquadmaker.repository.service.IRemoteMarvelService
import com.superherosquadmaker.repository.service.cache.ICacheHeroService
import javax.inject.Inject

class MarvelHeroRepository @Inject constructor(
    private val remoteMarvelService: IRemoteMarvelService,
    private val cacheHero: ICacheHeroService
) : MarvelHeroDataSource {

    override suspend fun fetchHeroes(): Answer<List<MarvelHero>> {
        val response = remoteMarvelService.fetchHeroes()

        if (response is Answer.Success) {
            cacheHero.saveMarvelHeroes(response.value)
        }

        return response
    }

    override suspend fun fetchHeroById(id: Int): Answer<MarvelHero> {
        val response = cacheHero.getMarvelHeroById(id)

        return if (response is Answer.Success) {
            response
        } else {
            remoteMarvelService.fetchHeroById(id)
        }
    }
}