package com.superherosquadmaker.service.cache

import com.superherosquadmaker.entity.MarvelHero

interface ICacheSquad : ICache<Int, MarvelHero?> {
    fun getAll(): List<MarvelHero>
    fun saveAll(heroes: List<MarvelHero>)
}