package com.superherosquadmaker.service.cache

import com.superherosquadmaker.entity.Answer
import com.superherosquadmaker.entity.FailureReason
import com.superherosquadmaker.entity.MarvelHero
import com.superherosquadmaker.repository.service.cache.ICacheSquadService
import com.superherosquadmaker.service.db.dao.HeroSquadDao
import com.superherosquadmaker.service.db.model.CacheHero
import com.superherosquadmaker.service.mapper.IDbMapper
import javax.inject.Inject

class CacheSquadService @Inject constructor(
    private val cacheSquad: ICacheSquad,
    private val squadDao: HeroSquadDao,
    private val mapper: IDbMapper<CacheHero, MarvelHero>
) : ICacheSquadService {

    override suspend fun getSquadHeroes(): Answer<List<MarvelHero>> {
        cacheSquad.getAll()
            .takeIf { it.isNotEmpty() }
            ?.let { return Answer.Success(it) }
            ?: run {
                val persistentResponse = squadDao.getAllTeammate()
                cacheSquad.saveAll(persistentResponse.map { mapper.cacheToEntity(it) })

                return if (persistentResponse.isNotEmpty()) {
                    Answer.Success(persistentResponse.map { mapper.cacheToEntity(it) })
                } else {
                    Answer.Failure(
                        NoSuchElementException(),
                        "No teammate in cache",
                        FailureReason.CACHE
                    )
                }
            }
    }

    override suspend fun saveSquadHero(marvelHero: MarvelHero) {
        cacheSquad.set(marvelHero.id, marvelHero)
        squadDao.insert(mapper.entityToCache(marvelHero))
    }

    override suspend fun getSquadHeroById(id: Int): Answer<MarvelHero> {
        val response = cacheSquad[id] ?: squadDao.getHeroById(id)?.let { mapper.cacheToEntity(it) }

        return if (response == null) {
            Answer.Failure(NoSuchElementException(), "No squad hero found with $id", FailureReason.CACHE)
        } else {
            Answer.Success(response)
        }
    }

    override suspend fun removeSquadHero(id: Int) {
        cacheSquad.remove(id)
        squadDao.delete(id)
    }
}