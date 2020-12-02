package com.superherosquadmaker.repository

import com.superherosquadmaker.repository.service.IDatabaseService
import javax.inject.Inject

class DatabaseGateway @Inject constructor(
        private val service: IDatabaseService
) : IDatabaseGateway {

    override suspend fun initDatabase() = service.initDatabase()
}