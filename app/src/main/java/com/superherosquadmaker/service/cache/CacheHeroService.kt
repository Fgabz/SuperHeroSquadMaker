package com.superherosquadmaker.service.cache

import com.superherosquadmaker.entity.Answer
import com.superherosquadmaker.entity.FailureReason
import com.superherosquadmaker.entity.MarvelHero
import com.superherosquadmaker.repository.service.cache.ICacheHeroService
import javax.inject.Inject

class CacheHeroService @Inject constructor(
    private val cacheTeammate: ICacheHero
) : ICacheHeroService {

    override suspend fun getMarvelHeroById(id: Int): Answer<MarvelHero> {
        val contact = cacheTeammate[id]
        return if (contact != null) {
            Answer.Success(contact)
        } else {
            Answer.Failure(
                NoSuchElementException(),
                "Hero with $id not found",
                FailureReason.CACHE
            )
        }
    }

    override suspend fun getMarvelHeroes(): Answer<List<MarvelHero>> {
        return Answer.Success(cacheTeammate.getAll())
    }

    override suspend fun saveMarvelHeroes(heroList: List<MarvelHero>) {
        removeAll()
        cacheTeammate.saveAll(heroList)
    }

    override suspend fun removeAll() {
        cacheTeammate.flushAll()
    }
}