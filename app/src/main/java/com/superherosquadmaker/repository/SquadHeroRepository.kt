package com.superherosquadmaker.repository

import com.superherosquadmaker.entity.Answer
import com.superherosquadmaker.entity.MarvelHero
import com.superherosquadmaker.repository.service.cache.ICacheSquadService
import javax.inject.Inject

class SquadHeroRepository @Inject constructor(
    private val squadCache: ICacheSquadService
) : SquadDataSource {

    override suspend fun fetchSquad() = squadCache.getSquadHeroes()

    override suspend fun fetchHeroSquadById(id: Int) = squadCache.getSquadHeroById(id)

    override suspend fun removeHeroFromSquad(id: Int) = Answer.Success(squadCache.removeSquadHero(id))

    override suspend fun addHeroInSquad(id: Int, marvelHero: MarvelHero) = squadCache.saveSquadHero(marvelHero)
}