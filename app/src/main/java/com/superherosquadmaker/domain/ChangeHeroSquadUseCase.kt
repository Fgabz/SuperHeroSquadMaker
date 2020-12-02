package com.superherosquadmaker.domain

interface ChangeHeroSquadUseCase {
    suspend fun changeHeroSquad(id: Int)
}