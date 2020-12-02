package com.superherosquadmaker.repository.service.cache

import com.superherosquadmaker.entity.Answer
import com.superherosquadmaker.entity.MarvelHero

interface ICacheSquadService {

    suspend fun getSquadHeroes(): Answer<List<MarvelHero>>
    suspend fun saveSquadHero(marvelHero: MarvelHero)
    suspend fun getSquadHeroById(id: Int): Answer<MarvelHero>
    suspend fun removeSquadHero(id: Int)
}