package com.abacatepay.service

import com.abacatepay.api.AbacatePay
import com.abacatepay.internal.Constants
import com.abacatepay.internal.toAbacatePayResponse
import com.abacatepay.model.response.AbacatePayResponse
import com.abacatepay.model.response.StoreResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.http.userAgent

/**
 * Provides functionality for managing store operations in the AbacatePay API.
 *
 * @param apiKey The API key used for authenticating requests to the AbacatePay API.
 * @param baseUrl The base URL of the AbacatePay API endpoints.
 * @param httpClient The HTTP client used for making network requests.
 *
 * @see AbacatePay.Store
 */
class StoreService(
    private val apiKey: String,
    private val baseUrl: String,
    private val httpClient: HttpClient,
) : AbacatePay.Store {

    /**
     * Retrieves store information.
     *
     * @return A [Result] containing an [AbacatePayResponse] with the store details ([StoreResponse]) upon success,
     *         or an error indicating the reason for failure.
     */
    override suspend fun get(): Result<AbacatePayResponse<StoreResponse>> {
        val response = httpClient.get("$baseUrl/store/get") {
            bearerAuth(apiKey)
            userAgent(Constants.DEFAULT_USER_AGENT)
        }

        return toAbacatePayResponse(response)
    }
}