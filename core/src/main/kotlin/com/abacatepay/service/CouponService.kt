package com.abacatepay.service

import com.abacatepay.api.AbacatePay
import com.abacatepay.internal.Constants
import com.abacatepay.internal.toAbacatePayResponse
import com.abacatepay.model.request.CouponRequest
import com.abacatepay.model.response.AbacatePayResponse
import com.abacatepay.model.response.CouponResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.userAgent

/**
 * Provides functionality for managing coupon operations in the AbacatePay API.
 *
 * @param apiKey The API key used for authenticating requests to the AbacatePay API.
 * @param baseUrl The base URL of the AbacatePay API endpoints.
 * @param httpClient The HTTP client used for making network requests.
 *
 * @see AbacatePay.Coupon
 */
class CouponService(
    private val apiKey: String,
    private val baseUrl: String,
    private val httpClient: HttpClient,
) : AbacatePay.Coupon {

    /**
     * Creates a new coupon.
     *
     * @param body The request payload containing the details of the coupon to be created.
     * @return A [Result] containing an [AbacatePayResponse] with the created coupon's details upon success,
     *         or an error indicating the reason for failure.
     */
    override suspend fun create(body: CouponRequest): Result<AbacatePayResponse<CouponResponse>> {
        val response = httpClient.post("$baseUrl/coupon/create") {
            setBody(body)
            contentType(ContentType.Application.Json)
            bearerAuth(apiKey)
            userAgent(Constants.DEFAULT_USER_AGENT)
        }

        return toAbacatePayResponse(response)
    }

    /**
     * Retrieves a list of available coupons.
     *
     * @return A [Result] containing an [AbacatePayResponse] with a list of [CouponResponse] instances upon success,
     *         or an error indicating the reason for failure.
     */
    override suspend fun list(): Result<AbacatePayResponse<List<CouponResponse>>> {
        val response = httpClient.get("$baseUrl/coupon/list"){
            bearerAuth(apiKey)
            userAgent(Constants.DEFAULT_USER_AGENT)
        }
        return toAbacatePayResponse(response)
    }

}