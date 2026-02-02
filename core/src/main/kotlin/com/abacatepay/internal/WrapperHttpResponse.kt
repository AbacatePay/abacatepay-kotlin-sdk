package com.abacatepay.internal

import com.abacatepay.exception.AbacatePayGenericException
import com.abacatepay.model.response.AbacatePayResponse
import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*

internal suspend inline fun <reified T> toAbacatePayResponse(response: HttpResponse): Result<AbacatePayResponse<T>> {
    return try {
        val abacatePayResponse: AbacatePayResponse<T> = response.body()
        if (response.status.isSuccess())
            Result.success(abacatePayResponse)
        else
            Result.failure(AbacatePayGenericException(abacatePayResponse.error.orEmpty(), response.status.value))
    } catch (e: Throwable){
        val body = response.bodyAsText()
        if (body.isNotEmpty()){
            Result.failure(AbacatePayGenericException(body, response.status.value))
        } else {
            Result.failure(e)
        }
    }
}