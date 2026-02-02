package com.abacatepay.service

import com.abacatepay.exception.AbacatePayGenericException
import com.abacatepay.httpClient
import com.abacatepay.mockEngine
import com.abacatepay.model.enums.PixKeyType
import com.abacatepay.model.enums.TransactionKind
import com.abacatepay.model.enums.WithdrawMethod
import com.abacatepay.model.enums.WithdrawStatus
import com.abacatepay.model.request.PixRequest
import com.abacatepay.model.request.WithdrawRequest
import com.abacatepay.model.response.AbacatePayResponse
import com.abacatepay.model.response.WithdrawResponse
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class WithdrawServiceTest {

    @Test
    fun `should create withdraw`() {
        val withdrawService = withdrawServiceMock(response = AbacatePayResponse(data = withdrawResponseTemplate))
        val response = runBlocking { withdrawService.create(withdrawRequestTemplate) }
        assertTrue(response.isSuccess)
        response.onSuccess {
            assertNotNull(it.data)
        }
    }

    @Test
    fun `should fail when create withdraw`() {
        val withdrawService = withdrawServiceMock(response = "Invalid API call", HttpStatusCode.InternalServerError)
        val response = runBlocking { withdrawService.create(withdrawRequestTemplate) }
        assertTrue(response.isFailure)
        response.onFailure {
            assertIs<AbacatePayGenericException>(it)
            assertEquals("Invalid API call", it.message)
            assertEquals(500, it.status)
        }
    }

    @Test
    fun `should retrieve withdraw details`() {
        val withdrawService = withdrawServiceMock(response = AbacatePayResponse(data = withdrawResponseTemplate))
        val response = runBlocking { withdrawService.get(withdrawRequestTemplate.externalId) }
        assertTrue(response.isSuccess)
        response.onSuccess {
            assertNotNull(it.data)
        }
    }

    @Test
    fun `should list withdraws`() {
        val withdrawService = withdrawServiceMock(response = AbacatePayResponse(data = listOf(withdrawResponseTemplate)))
        val response = runBlocking { withdrawService.list() }
        assertTrue(response.isSuccess)
        response.onSuccess {
            assertNotNull(it.data)
            assertEquals(1, it.data.size)
        }
    }
    
    private val withdrawRequestTemplate = WithdrawRequest(
        externalId = "withdraw-1234",
        method = WithdrawMethod.PIX,
        amount = 5000,
        pix = PixRequest(
            type = PixKeyType.CPF,
            key = "123.456.789-01"
        ),
        description = "Saque para conta principal"
    )
    
    private val withdrawResponseTemplate = WithdrawResponse(
        id = "tran_123456",
        status = WithdrawStatus.PENDING,
        devMode = true,
        receiptUrl = "https://abacatepay.com/receipt/tran_123456",
        kind = TransactionKind.WITHDRAW,
        amount = 5000,
        platformFee = 80,
        createdAt = "2025-03-24T21:50:20.772Z",
        updatedAt = "2025-03-24T21:50:20.772Z",
        externalId = "withdraw-1234"
    )

    private inline fun <reified T> withdrawServiceMock(
        response: T,
        httpStatusCode: HttpStatusCode = HttpStatusCode.OK,
        contentType: String? = null
    ) = WithdrawService(
        apiKey = "test-api-key",
        baseUrl = "http://localhost:8080",
        httpClient = httpClient(mockEngine(response, httpStatusCode, contentType))
    )
}