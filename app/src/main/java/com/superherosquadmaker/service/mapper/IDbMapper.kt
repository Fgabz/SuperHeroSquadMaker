package com.superherosquadmaker.service.mapper

interface IDbMapper<C, E> {

    fun cacheToEntity(cache: C): E
    fun entityToCache(entity: E): C
}