package com.superherosquadmaker.repository.service

import com.superherosquadmaker.entity.Answer
import com.superherosquadmaker.entity.Issue
import com.superherosquadmaker.entity.MarvelHero

interface IRemoteMarvelService {
    suspend fun fetchHeroes(): Answer<List<MarvelHero>>

    suspend fun fetchHeroById(id: Int): Answer<MarvelHero>
}