package com.abacatepay.service

import com.abacatepay.api.AbacatePay
import com.abacatepay.internal.Constants
import com.abacatepay.internal.toAbacatePayResponse
import com.abacatepay.model.request.BillingRequest
import com.abacatepay.model.response.AbacatePayResponse
import com.abacatepay.model.response.BillingResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.userAgent

/**
 * Provides functionality for interacting with the billing operations of the AbacatePay API.
 *
 * @param apiKey The API key used for authenticating requests to the AbacatePay API.
 * @param baseUrl The base URL of the AbacatePay API endpoints.
 * @param httpClient The HTTP client instance used to execute network requests.
 *
 * @see AbacatePay.Billing
 */
class BillingService(
    private val apiKey: String,
    private val baseUrl: String,
    private val httpClient: HttpClient,
) : AbacatePay.Billing {

    /**
     * Creates a new billing.
     *
     * @param body The request payload containing billing details.
     * @return A [Result] containing an [AbacatePayResponse] with the created billing details upon success,
     *         or an error indicating the reason for failure.
     */
    override suspend fun create(body: BillingRequest): Result<AbacatePayResponse<BillingResponse>> {
        val response = httpClient.post("$baseUrl/billing/create") {
            setBody(body)
            bearerAuth(apiKey)
            contentType(ContentType.Application.Json)
            userAgent(Constants.DEFAULT_USER_AGENT)
        }
        return toAbacatePayResponse(response)
    }

    /**
     * Retrieves a list of billing records.
     *
     * @return A [Result] containing an [AbacatePayResponse] with a list of [BillingResponse] instances upon success,
     *         or an error indicating the reason for failure.
     */
    override suspend fun list(): Result<AbacatePayResponse<List<BillingResponse>>> {
        val response = httpClient.get("$baseUrl/billing/list"){
            bearerAuth(apiKey)
            userAgent(Constants.DEFAULT_USER_AGENT)
        }
        return toAbacatePayResponse(response)
    }

}