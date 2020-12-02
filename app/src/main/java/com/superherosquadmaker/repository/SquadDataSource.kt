package com.superherosquadmaker.repository

import com.superherosquadmaker.entity.Answer
import com.superherosquadmaker.entity.MarvelHero

interface SquadDataSource {
    suspend fun fetchSquad(): Answer<List<MarvelHero>>

    suspend fun fetchHeroSquadById(id: Int): Answer<MarvelHero>

    suspend fun removeHeroFromSquad(id: Int): Answer<Unit>

    suspend fun addHeroInSquad(id: Int, marvelHero: MarvelHero)
}