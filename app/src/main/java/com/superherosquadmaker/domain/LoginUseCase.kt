package com.superherosquadmaker.domain

import android.net.Uri

interface LoginUseCase {
    suspend fun signInUseCase()
    suspend fun getAuthorizationToken(uri: Uri)
}