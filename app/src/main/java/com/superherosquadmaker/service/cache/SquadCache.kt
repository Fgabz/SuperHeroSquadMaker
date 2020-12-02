package com.superherosquadmaker.service.cache

import com.superherosquadmaker.annotation.CacheSquadMap
import com.superherosquadmaker.annotation.PerApp
import com.superherosquadmaker.entity.MarvelHero
import java.util.concurrent.ConcurrentMap
import javax.inject.Inject

@PerApp
class SquadCache @Inject constructor(
    @CacheSquadMap private val map: ConcurrentMap<Int, MarvelHero?>
) : ICacheSquad {

    override fun getAll(): List<MarvelHero> {
        val list = mutableListOf<MarvelHero>()

        map.forEach {
            it.value?.let { call ->
                list.add(call)
            }
        }

        return list
    }

    override fun saveAll(heroes: List<MarvelHero>) {
        heroes.forEach {
            map[it.id] = it
        }
    }

    override fun set(key: Int, value: MarvelHero?) {
        map[key] = value
    }

    override fun get(key: Int) = map[key]

    override fun containsKey(key: Int): Boolean = map.containsKey(key)

    override fun flushAll() {
        map.clear()
    }

    override fun remove(key: Int) {
        map.remove(key)
    }
}