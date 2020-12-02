package com.superherosquadmaker.service.api.webservice

import com.superherosquadmaker.service.api.model.RemoteMarvelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IMarvelHeroesWebService {

    @GET("/v1/public/characters")
    suspend fun fetchHeroes(
    ): Response<RemoteMarvelResponse>

    @GET("/v1/public/characters/{characterId}")
    suspend fun fetchHeroById(@Path("characterId") characterId: Int): Response<RemoteMarvelResponse>
}