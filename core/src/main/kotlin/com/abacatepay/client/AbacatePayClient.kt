package com.abacatepay.client

import com.abacatepay.api.AbacatePay.Customer
import com.abacatepay.api.AbacatePay.Billing
import com.abacatepay.api.AbacatePay.Store
import com.abacatepay.api.AbacatePay.Withdraw
import com.abacatepay.api.AbacatePay.PixQRCode
import com.abacatepay.api.AbacatePay.PublicMRR
import com.abacatepay.api.AbacatePay.Coupon
import com.abacatepay.service.BillingService
import com.abacatepay.service.CouponService
import com.abacatepay.service.CustomerService
import com.abacatepay.service.PixQRCodeService
import com.abacatepay.service.PublicMRRService
import com.abacatepay.service.StoreService
import com.abacatepay.service.WithdrawService
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.*

/**
 * Client for interacting with the AbacatePay API.
 *
 * @param apiKey The API key used to authenticate requests to the AbacatePay API.
 * @param options Configuration options for the client, such as logging and request timeout.
 * @param engine Optional HTTP engine used for executing network requests. Defaults to the CIO engine.
 */
class AbacatePayClient(

    /**
     * The API Key used for authenticating requests to the AbacatePay API.
     */
    private val apiKey: String,

    /**
     * The configuration options for the client
     */
    private val options: AbacatePayOptions = AbacatePayOptions(),

    /**
     * The HTTP engine used for making network requests
     */
    engine: HttpClientEngine = CIO.create(),
) {

    /**
     * Configures and initializes the HTTP client for making network requests.
     *
     * The client is customized with the following features:
     * - Content negotiation with JSON support to parse and serialize JSON payloads.
     * - Logging to track HTTP request and response details, controlled by the `enableLogging` option.
     * - Timeout settings for request and connection, defined by the `timeout` property in `AbacatePayOptions`.
     *
     * This HTTP client is used for communication with AbacatePay API endpoints.
     */
    private val httpClient: HttpClient = HttpClient(engine) {
        install(ContentNegotiation) {
            json()
        }
        install(Logging){
            level = if (options.enableLogging) LogLevel.ALL else LogLevel.NONE
        }
        install(HttpTimeout){
            requestTimeoutMillis = options.timeout
            connectTimeoutMillis = options.timeout
        }
    }

    /**
     * Handles customer operations within the AbacatePay API.
     *
     * @see Customer
     */
    val customer: Customer = CustomerService(
        apiKey = apiKey,
        baseUrl = options.baseUrl,
        httpClient = httpClient
    )

    /**
     * Handles billing operations within the AbacatePay API.
     */
    val billing: Billing = BillingService(
        apiKey = apiKey,
        baseUrl = options.baseUrl,
        httpClient = httpClient
    )

    /**
     * Handles store operations within the AbacatePay API.
     *
     * @see Store
     */
    val store: Store = StoreService(
        apiKey = apiKey,
        baseUrl = options.baseUrl,
        httpClient = httpClient
    )

    /**
     * Handles coupon operations within the AbacatePay API.
     *
     * @see Coupon
     */
    val coupon: Coupon = CouponService(
        apiKey = apiKey,
        baseUrl = options.baseUrl,
        httpClient = httpClient
    )

    /**
     * Handles Pix QR Code operations within the AbacatePay API.
     *
     * @see PixQRCode
     */
    val pixQRCode: PixQRCode = PixQRCodeService(
        apiKey = apiKey,
        baseUrl = options.baseUrl,
        httpClient = httpClient
    )

    /**
     * Handles withdrawal operations within the AbacatePay API.
     *
     * @see Withdraw
     */
    val withdraw: Withdraw = WithdrawService(
        apiKey = apiKey,
        baseUrl = options.baseUrl,
        httpClient = httpClient
    )

    /**
     * Handles MRR operations within the AbacatePay API.
     *
     * @see PublicMRR
     */
    val publicMRR: PublicMRR = PublicMRRService(
        apiKey = apiKey,
        baseUrl = options.baseUrl,
        httpClient = httpClient
    )

}