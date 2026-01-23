package com.abacatepay.service

import com.abacatepay.api.AbacatePay
import com.abacatepay.internal.Constants
import com.abacatepay.internal.toAbacatePayResponse
import com.abacatepay.model.request.WithdrawRequest
import com.abacatepay.model.response.AbacatePayResponse
import com.abacatepay.model.response.WithdrawResponse
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.userAgent

/**
 * Provides functionality to interact with the AbacatePay API for managing withdrawal operations.
 *
 * @param apiKey The API key required to authenticate requests against the AbacatePay API.
 * @param baseUrl The base URL of the AbacatePay API.
 * @param httpClient The HTTP client used for making requests to the API.
 */
class WithdrawService(
    private val apiKey: String,
    private val baseUrl: String,
    private val httpClient: HttpClient,
) : AbacatePay.Withdraw {

    /**
     * Initiates a withdrawal request.
     *
     * @param body the request payload containing withdrawal details such as the amount, external ID, method, and pix information.
     * @return a [Result] object wrapping an [AbacatePayResponse] that contains the withdrawal response data on success or an error message on failure.
     */
    override suspend fun create(body: WithdrawRequest): Result<AbacatePayResponse<WithdrawResponse>> {
        val response = httpClient.post("$baseUrl/withdraw/create") {
            setBody(body)
            contentType(ContentType.Application.Json)
            bearerAuth(apiKey)
            userAgent(Constants.DEFAULT_USER_AGENT)
        }

        return toAbacatePayResponse(response)
    }

    /**
     * Retrieves a list of all withdrawal requests.
     *
     * @return a [Result] object wrapping an [AbacatePayResponse] that contains a list of [WithdrawResponse] objects
     * representing the details of each withdrawal. On failure, includes an error message.
     */
    override suspend fun list(): Result<AbacatePayResponse<List<WithdrawResponse>>> {
        val response = httpClient.get("$baseUrl/withdraw/list"){
            bearerAuth(apiKey)
            userAgent(Constants.DEFAULT_USER_AGENT)
        }
        return toAbacatePayResponse(response)
    }

    /**
     * Retrieves the details of a specific withdrawal request using its external ID.
     *
     * @param externalId the unique identifier of the withdrawal request to be retrieved.
     * @return a [Result] object wrapping an [AbacatePayResponse], which contains the details of the withdrawal as a [WithdrawResponse]
     *         on success, or an error message on failure.
     */
    override suspend fun get(externalId: String): Result<AbacatePayResponse<WithdrawResponse>> {
        val response = httpClient.get(
            "$baseUrl/withdraw/get?externalId=$externalId"
        ){
            bearerAuth(apiKey)
            userAgent(Constants.DEFAULT_USER_AGENT)
        }
        return toAbacatePayResponse(response)
    }
}