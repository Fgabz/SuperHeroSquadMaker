package com.superherosquadmaker.domain

interface FetchHeroUseCase {
    suspend fun fetchHeroUseCase(id: Int)
}