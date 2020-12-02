package com.superherosquadmaker.service.db

import com.superherosquadmaker.annotation.PerApp
import com.superherosquadmaker.repository.service.IDatabaseService
import timber.log.Timber
import javax.inject.Inject

@PerApp
class DatabaseService @Inject constructor(
    private val database: SuperHeroSquadDatabase
) : IDatabaseService {

    override fun initDatabase() {
        Timber.i("Database initialized")
    }
}