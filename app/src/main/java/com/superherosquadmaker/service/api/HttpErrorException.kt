package com.superherosquadmaker.service.api

class HttpErrorException(val httpCode: Int, message: String?) : Exception(message)