package com.abacatepay.service

import com.abacatepay.httpClient
import com.abacatepay.mockEngine
import com.abacatepay.model.enums.BillingStatus
import com.abacatepay.model.enums.PaymentFrequency
import com.abacatepay.model.enums.PaymentMethod
import com.abacatepay.model.request.BillingRequest
import com.abacatepay.model.request.CustomerRequest
import com.abacatepay.model.request.ProductRequest
import com.abacatepay.model.response.AbacatePayResponse
import com.abacatepay.model.response.BillingResponse
import com.abacatepay.model.response.CustomerResponse
import com.abacatepay.model.response.ProductResponse
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class BillingServiceTest {

    @Test
    fun `should create billing`() {
        val billingService = billingServiceMock(AbacatePayResponse(data = billingResponseTemplate))
        val response = runBlocking { billingService.create(billingRequestTemplate) }
        assertTrue(response.isSuccess)
        response.onSuccess {
            assertNotNull(it.data)
        }
    }

    @Test
    fun `should list billing`() {
        val billingService = billingServiceMock(AbacatePayResponse(data = listOf(billingResponseTemplate)))
        val response = runBlocking { billingService.list() }
        assertTrue(response.isSuccess)
        response.onSuccess {
            assertNotNull(it.data)
            assertEquals(1, it.data.size)
        }
    }
    
    
    private val billingRequestTemplate: BillingRequest = BillingRequest(
        frequency = PaymentFrequency.ONE_TIME,
        methods = listOf(PaymentMethod.PIX),
        products = listOf(
            ProductRequest(externalId = "prod-1234", name = "Assinatura de Programa Fitness", quantity = 2, description = "Acesso ao programa fitness premium por 1 mês.", price = 2000)
        ),
        returnUrl = "https://example.com/billing",
        completionUrl = "https://example.com/completion",
        customerId = "cust_abcdefghij",
        customer = CustomerRequest(name = "Daniel Lima", cellphone = "(11) 4002-8922", email = "daniel_lima@abacatepay.com", taxId = "123.456.789-01"),
        allowCoupons = false,
        coupons = listOf("ABKT10", "ABKT5", "PROMO10"),
        externalId = "seu_id_123",
        metadata = mapOf("externalId" to "123")
    )

    private val billingResponseTemplate: BillingResponse = BillingResponse(
        id = "bill_123456",
        url = "https://pay.abacatepay.com/bill-5678",
        status = BillingStatus.PENDING,
        devMode = true,
        paymentMethods = listOf(PaymentMethod.PIX),
        products = listOf(
            ProductResponse(
                id = "prod_123456",
                externalId = "prod-1234",
                quantity = 2
            )
        ),
        frequency = PaymentFrequency.ONE_TIME,
        amount = 4000,
        nextBilling = "null",
        customer = CustomerResponse(
            id = "bill_123456",
            metadata = CustomerRequest(
                name = "Daniel Lima",
                cellphone = "(11) 4002-8922",
                email= "daniel_lima@abacatepay.com",
                taxId= "123.456.789-01"
            )
        ),
        allowCoupons = false,
        coupons = emptyList()
    )

    private inline fun <reified T> billingServiceMock(
        response: T,
        httpStatusCode: HttpStatusCode = HttpStatusCode.OK,
        contentType: String? = null
    ) = BillingService(
        apiKey = "test-api-key",
        baseUrl = "http://localhost:8080",
        httpClient = httpClient(mockEngine(response, httpStatusCode, contentType))
    )
}