package com.superherosquadmaker.service.cache

interface ICache<K, V> {

    fun set(key: K, value: V)

    operator fun get(key: K): V

    fun containsKey(key: K): Boolean

    fun flushAll()

    fun remove(key: K) = Unit
}

