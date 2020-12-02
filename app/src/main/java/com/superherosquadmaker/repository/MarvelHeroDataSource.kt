package com.superherosquadmaker.repository

import com.superherosquadmaker.entity.Answer
import com.superherosquadmaker.entity.Issue
import com.superherosquadmaker.entity.MarvelHero

interface MarvelHeroDataSource {
    suspend fun fetchHeroes(): Answer<List<MarvelHero>>

    suspend fun fetchHeroById(id: Int): Answer<MarvelHero>
}