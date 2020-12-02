package com.superherosquadmaker

import com.superherosquadmaker.annotation.PerApp
import com.superherosquadmaker.domain.application.InitializeApplicationUseCase
import com.superherosquadmaker.repository.IDatabaseGateway
import javax.inject.Inject

@PerApp
class ApplicationInteractor @Inject constructor(
    private val databaseGateway: IDatabaseGateway
) : InitializeApplicationUseCase {

    override suspend fun initializeApplication() {
        databaseGateway.initDatabase()
    }
}