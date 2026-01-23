package com.abacatepay.service

import com.abacatepay.api.AbacatePay
import com.abacatepay.internal.Constants
import com.abacatepay.internal.toAbacatePayResponse
import com.abacatepay.model.request.PixQRCodeRequest
import com.abacatepay.model.request.SimulatePaymentRequest
import com.abacatepay.model.response.AbacatePayResponse
import com.abacatepay.model.response.PixQRCodeResponse
import com.abacatepay.model.response.PixQRCodeStatusResponse
import io.ktor.client.*
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.userAgent

/**
 * Provides functionality for managing Pix QR Code operations in the AbacatePay API.
 *
 * @param apiKey The API key used for authenticating requests to the AbacatePay API.
 * @param baseUrl The base URL of the AbacatePay API endpoints.
 * @param httpClient The HTTP client used for making network requests.
 *
 * @see AbacatePay.PixQRCode
 */
class PixQRCodeService(
    private val apiKey: String,
    private val baseUrl: String,
    private val httpClient: HttpClient,
) : AbacatePay.PixQRCode {

    /**
     * Creates a new Pix QR Code.
     *
     * @param body The request payload containing the details required to create a Pix QR Code
     * @return A [Result] containing an [AbacatePayResponse] with the generated Pix QR Code details
     *         ([PixQRCodeResponse]) upon success, or an error indicating the reason for failure.
     */
    override suspend fun create(body: PixQRCodeRequest): Result<AbacatePayResponse<PixQRCodeResponse>> {
        val response = httpClient.post("$baseUrl/pixQrCode/create") {
            setBody(body)
            contentType(ContentType.Application.Json)
            userAgent(Constants.DEFAULT_USER_AGENT)
            bearerAuth(apiKey)
        }
        return toAbacatePayResponse(response)
    }

    /**
     * Simulates a payment for a given Pix QR Code.
     *
     * @param qrCodeId The unique identifier of the Pix QR Code for which the payment simulation is to be performed.
     * @param body The request payload containing additional metadata for the simulation.
     * @return A [Result] containing an [AbacatePayResponse] with the simulated Pix QR Code details
     *         ([PixQRCodeResponse]) upon success, or an error indicating the reason for failure.
     */
    override suspend fun simulatePayment(
        qrCodeId: String,
        body: SimulatePaymentRequest?
    ): Result<AbacatePayResponse<PixQRCodeResponse>> {
        val response = httpClient.post("$baseUrl/pixQrCode/simulate-payment?id=$qrCodeId") {
            body?.let {
                setBody(it)
                contentType(ContentType.Application.Json)
            }
            userAgent(Constants.DEFAULT_USER_AGENT)
            bearerAuth(apiKey)
        }
        return toAbacatePayResponse(response)
    }

    /**
     * Checks the status of a Pix QR Code.
     *
     * @param qrCodeId The unique identifier of the Pix QR Code to be verified.
     * @return A [Result] containing an [AbacatePayResponse] with the Pix QR Code status details
     *         ([PixQRCodeStatusResponse]) upon success, or an error indicating the reason for failure.
     */
    override suspend fun check(qrCodeId: String): Result<AbacatePayResponse<PixQRCodeStatusResponse>> {
        val response = httpClient.get("$baseUrl/pixQrCode/check?id=$qrCodeId"){
            bearerAuth(apiKey)
            userAgent(Constants.DEFAULT_USER_AGENT)
        }

        return toAbacatePayResponse(response)
    }

}