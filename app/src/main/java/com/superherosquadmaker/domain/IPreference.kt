package com.superherosquadmaker.domain

interface IPreference<T> {
    val key: String
    val preferences: Any

    fun get(): T
    fun set(value: T)

    fun isSet(): Boolean

    fun delete()
}