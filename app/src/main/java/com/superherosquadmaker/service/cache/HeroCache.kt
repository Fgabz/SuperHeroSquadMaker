package com.superherosquadmaker.service.cache

import com.superherosquadmaker.annotation.CacheHeroMap
import com.superherosquadmaker.annotation.PerApp
import com.superherosquadmaker.entity.MarvelHero
import java.util.concurrent.ConcurrentMap
import javax.inject.Inject

@PerApp
class HeroCache @Inject constructor(
    @CacheHeroMap private val map: ConcurrentMap<Int, MarvelHero?>
) : ICacheHero {

    override fun getAll(): List<MarvelHero> {
        val list = mutableListOf<MarvelHero>()

        map.forEach {
            it.value?.let { call ->
                list.add(call)
            }
        }

        return list
    }

    override fun saveAll(contacts: List<MarvelHero>) {
        contacts.forEach {
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

    override fun remove(id: Int) {
        map.remove(id)
    }
}