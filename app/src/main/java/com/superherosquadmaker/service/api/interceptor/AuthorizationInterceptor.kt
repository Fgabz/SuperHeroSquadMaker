package com.superherosquadmaker.service.api.interceptor

import com.superherosquadmaker.annotation.MarvelPrivate
import com.superherosquadmaker.annotation.MarvelPublic
import com.superherosquadmaker.core.toMd5
import com.superherosquadmaker.domain.IPreference
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(
    @MarvelPublic private val marvelPublic: IPreference<String>,
    @MarvelPrivate private val marvelPrivate: IPreference<String>
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val timestamp = System.currentTimeMillis().toString()
        val apiKey = marvelPublic.get()
        val stringToHash = timestamp + marvelPrivate.get() + apiKey

        val url = original.url.newBuilder()
            .addQueryParameter(TIMESTAMP_KEY, timestamp)
            .addQueryParameter(APIKEY_KEY, apiKey)
            .addQueryParameter(HASH_KEY, stringToHash.toMd5())
            .build()


        return chain.proceed(original.newBuilder().url(url).build())
    }

    companion object {
        const val TIMESTAMP_KEY = "ts"
        const val APIKEY_KEY = "apikey"
        const val HASH_KEY = "hash"
    }
}