package com.abacatepay.service

import com.abacatepay.exception.AbacatePayGenericException
import com.abacatepay.httpClient
import com.abacatepay.mockEngine
import com.abacatepay.model.response.AbacatePayResponse
import com.abacatepay.model.response.MRRResponse
import com.abacatepay.model.response.MerchantInfoResponse
import com.abacatepay.model.response.RevenueResponse
import com.abacatepay.model.response.TransactionPerDayResponse
import com.abacatepay.model.response.TransactionSummaryResponse
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class PublicMRRServiceTest {

    @Test
    fun `should retrieve mrr`() {
        val mrrService = mrrServiceMock(AbacatePayResponse(data = mrrResponseTemplate))
        val response = runBlocking { mrrService.getMRR() }
        assertTrue(response.isSuccess)
        response.onSuccess {
            assertNotNull(it.data)
        }
    }

    @Test
    fun `should fail when retrieve mrr`(){
        val mrrService = mrrServiceMock(AbacatePayResponse(success = false, data = null, error = "Insufficient permissions"))
        val response = runBlocking { mrrService.getMRR() }
        assertTrue(response.isSuccess)
        response.onSuccess {
            assertEquals("Insufficient permissions", it.error)
            assertFalse(it.success!!)
        }
    }

    @Test
    fun `should retrieve merchant info`(){
        val mrrService = mrrServiceMock(AbacatePayResponse(data = merchantInfoResponseTemplate))
        val response = runBlocking { mrrService.getMerchantInfo() }
        assertTrue(response.isSuccess)
        response.onSuccess {
            assertNotNull(it.data)
        }
    }

    @Test
    fun `should retrieve revenue per period`(){
        val mrrService = mrrServiceMock(response = AbacatePayResponse(data = revenuePerPeriodResponseTemplate))
        val response = runBlocking { mrrService.getRevenue("2024-01-15", "2024-01-16") }
        assertTrue(response.isSuccess)
        response.onSuccess {
            assertNotNull(it.data)
        }
    }

    private val mrrResponseTemplate = MRRResponse(
        mrr = 0,
        totalActiveSubscriptions = 0
    )

    private val merchantInfoResponseTemplate = MerchantInfoResponse(
        name = "Example Tech",
        website = "https://www.example.com",
        createdAt = "2024-12-06T18:53:31.756Z"
    )

    private val revenuePerPeriodResponseTemplate = RevenueResponse(
        totalRevenue = 150000,
        totalTransactions = 45,
        transactionsPerDay = TransactionPerDayResponse(
            transactionPerDay = mapOf(
                "2024-01-15" to TransactionSummaryResponse(
                    amount = 5000,
                    count = 3
                ),
                "2024-01-16" to TransactionSummaryResponse(
                    amount = 3000,
                    count = 2
                ),
            )
        )
    )

    private inline fun <reified T> mrrServiceMock(
        response: T,
        httpStatusCode: HttpStatusCode = HttpStatusCode.OK,
        contentType: String? = null
    ) = PublicMRRService(
        apiKey = "test-api-key",
        baseUrl = "http://localhost:8080",
        httpClient = httpClient(mockEngine(response, httpStatusCode, contentType))
    )
}