package com.abacatepay.service

import com.abacatepay.api.AbacatePay
import com.abacatepay.internal.Constants
import com.abacatepay.internal.toAbacatePayResponse
import com.abacatepay.model.request.CustomerRequest
import com.abacatepay.model.response.AbacatePayResponse
import com.abacatepay.model.response.CustomerResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.userAgent

/**
 * Provides functionality for managing customer operations in the AbacatePay API.
 *
 * @param apiKey The API key used to authenticate requests to the AbacatePay API.
 * @param baseUrl The base URL of the AbacatePay API.
 * @param httpClient The HTTP client used to execute network requests.
 *
 * @see AbacatePay.Customer
 */
class CustomerService(
    private val apiKey: String,
    private val baseUrl: String,
    private val httpClient: HttpClient,
) : AbacatePay.Customer {

    /**
     * Creates a new customer.
     *
     * @param body The request payload containing customer details to be created.
     * @return A [Result] containing an [AbacatePayResponse] with the created customer's details upon success,
     *         or an error indicating the reason for failure.
     */
    override suspend fun create(body: CustomerRequest): Result<AbacatePayResponse<CustomerResponse>> {
        val response = httpClient.post("$baseUrl/customer/create") {
            setBody(body)
            bearerAuth(apiKey)
            contentType(ContentType.Application.Json)
            userAgent(Constants.DEFAULT_USER_AGENT)
        }

        return toAbacatePayResponse(response)
    }

    /**
     * Retrieves a list of customers from the AbacatePay API.
     *
     * @return A [Result] containing an [AbacatePayResponse] with a list of [CustomerResponse] instances upon success,
     *         or an error indicating the reason for failure.
     */
    override suspend fun list(): Result<AbacatePayResponse<List<CustomerResponse>>> {
        val response = httpClient.get("$baseUrl/customer/list"){
            bearerAuth(apiKey)
            userAgent(Constants.DEFAULT_USER_AGENT)
        }
        return toAbacatePayResponse(response)
    }
}