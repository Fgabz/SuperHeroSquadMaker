package com.superherosquadmaker.service.api

import com.superherosquadmaker.entity.Answer
import com.superherosquadmaker.entity.FailureReason
import retrofit2.Response
import java.io.IOException

fun IOException.throwIOException() =
    Answer.Failure(this, this.message.toString(), FailureReason.NETWORK)

fun <T> Response<T>.throwHttpException(): Answer.Failure {
    val error = HttpErrorException(
        this.code(),
        this.errorBody()?.toString()
    )
    return Answer.Failure(error, error.message.toString(), FailureReason.NETWORK)
}