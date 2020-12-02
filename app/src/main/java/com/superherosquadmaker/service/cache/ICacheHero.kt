package com.superherosquadmaker.service.cache

import com.superherosquadmaker.entity.MarvelHero

interface ICacheHero : ICache<Int, MarvelHero?> {
    fun getAll(): List<MarvelHero>
    fun saveAll(contacts: List<MarvelHero>)
}