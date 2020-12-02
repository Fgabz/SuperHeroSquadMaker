package com.superherosquadmaker

import com.aircall.application.IApplicationController
import com.superherosquadmaker.annotation.PerApp
import com.superherosquadmaker.domain.application.InitializeApplicationUseCase
import javax.inject.Inject

@PerApp
class ApplicationController @Inject constructor(
    private val initializeApplicationUseCase: InitializeApplicationUseCase
) : IApplicationController {

    override suspend fun onApplicationCreated() {
        initializeApplicationUseCase.initializeApplication()
    }
}