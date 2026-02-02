package com.abacatepay.service

import com.abacatepay.api.AbacatePay
import com.abacatepay.internal.Constants
import com.abacatepay.internal.toAbacatePayResponse
import com.abacatepay.model.response.AbacatePayResponse
import com.abacatepay.model.response.MRRResponse
import com.abacatepay.model.response.MerchantInfoResponse
import com.abacatepay.model.response.RevenueResponse
import io.ktor.client.*
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.http.userAgent

/**
 * Provides functionality for interacting with the Public Monthly Recurring Revenue (MRR)
 * operations in the AbacatePay API.
 *
 * @param apiKey The API key used for authenticating requests to the AbacatePay API.
 * @param baseUrl The base URL of the AbacatePay API endpoints.
 * @param httpClient The HTTP client used for making network requests.
 *
 * @see AbacatePay.PublicMRR
 */
class PublicMRRService(
    private val apiKey: String,
    private val baseUrl: String,
    private val httpClient: HttpClient,
) : AbacatePay.PublicMRR {

    /**
     * Retrieves the Monthly Recurring Revenue (MRR) and associated subscription information.
     *
     * @return A [Result] containing an [AbacatePayResponse] with the MRR data ([MRRResponse]) upon success,
     *         or an error indicating the reason for failure.
     */
    override suspend fun getMRR(): Result<AbacatePayResponse<MRRResponse>> {
        val response = httpClient.get("$baseUrl/public-mrr/mrr"){
            bearerAuth(apiKey)
            userAgent(Constants.DEFAULT_USER_AGENT)
        }

        return toAbacatePayResponse(response)
    }

    /**
     * Retrieves merchant-related information.
     *
     * @return A [Result] containing an [AbacatePayResponse] with the merchant details ([MerchantInfoResponse]) upon success,
     *         or an error indicating the reason for failure.
     */
    override suspend fun getMerchantInfo(): Result<AbacatePayResponse<MerchantInfoResponse>> {
        val response = httpClient.get("$baseUrl/public-mrr/merchant-info"){
            bearerAuth(apiKey)
            userAgent(Constants.DEFAULT_USER_AGENT)
        }

        return toAbacatePayResponse(response)
    }


    /**
     * Retrieves revenue-related details for a specified date range.
     *
     * @param startDate The start date of the revenue period (format YYYY-MM-DD)
     * @param endDate The end date of the revenue period (format YYYY-MM-DD)
     * @return A [Result] containing an [AbacatePayResponse] with the revenue details ([RevenueResponse]) upon success,
     *         or an error indicating the reason for failure.
     */
    override suspend fun getRevenue(
        startDate: String,
        endDate: String
    ): Result<AbacatePayResponse<RevenueResponse>> {
        val response = httpClient.get("$baseUrl/public-mrr/revenue?startDate=$startDate&endDate=$endDate"){
            bearerAuth(apiKey)
            userAgent(Constants.DEFAULT_USER_AGENT)
        }
        return toAbacatePayResponse(response)
    }
}