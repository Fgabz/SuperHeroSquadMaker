package com.superherosquadmaker.service.api

import com.superherosquadmaker.entity.Answer
import com.superherosquadmaker.entity.FailureReason
import com.superherosquadmaker.entity.MarvelHero
import com.superherosquadmaker.repository.service.IRemoteMarvelService
import com.superherosquadmaker.service.api.model.RemoteMarvelResponse
import com.superherosquadmaker.service.api.webservice.IMarvelHeroesWebService
import com.superherosquadmaker.service.mapper.IRemoteMarvelCharacterMapper
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class RemoteMarvelService @Inject constructor(
    private val webService: IMarvelHeroesWebService,
    private val repoMapper: IRemoteMarvelCharacterMapper
) : IRemoteMarvelService {

    override suspend fun fetchHeroById(id: Int): Answer<MarvelHero> {
        val response: Response<RemoteMarvelResponse>

        try {
            response = webService.fetchHeroById(id)
        } catch (e: IOException) {
            return e.throwIOException()
        }

        response.body()?.let {
            val answer = it.data?.results?.let { remoteHero ->
                repoMapper.remoteToEntity(remoteHero.first())
            }

            return if (answer != null) {
                Answer.Success(answer)
            } else {
                Answer.Failure(NoSuchElementException(), "Error not found", FailureReason.NETWORK)
            }
        } ?: run {
            return response.throwHttpException()
        }
    }

    override suspend fun fetchHeroes(): Answer<List<MarvelHero>> {
        val response: Response<RemoteMarvelResponse>

        try {
            response = webService.fetchHeroes()
        } catch (e: IOException) {
            return e.throwIOException()
        }

        response.body()?.let { result ->
            return Answer.Success(result.data?.results?.map {
                repoMapper.remoteToEntity(it)
            } ?: emptyList())
        } ?: run {
            return response.throwHttpException()
        }
    }
}