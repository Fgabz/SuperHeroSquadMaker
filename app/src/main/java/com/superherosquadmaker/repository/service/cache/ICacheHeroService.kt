package com.superherosquadmaker.repository.service.cache

import com.superherosquadmaker.entity.Answer
import com.superherosquadmaker.entity.MarvelHero

interface ICacheHeroService {

    suspend fun getMarvelHeroById(id: Int): Answer<MarvelHero>
    suspend fun getMarvelHeroes(): Answer<List<MarvelHero>>
    suspend fun saveMarvelHeroes(heroList: List<MarvelHero>)
    suspend fun removeAll()
}