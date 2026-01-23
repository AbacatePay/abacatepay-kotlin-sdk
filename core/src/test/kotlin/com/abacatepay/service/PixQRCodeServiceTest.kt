package com.abacatepay.service

import com.abacatepay.httpClient
import com.abacatepay.mockEngine
import com.abacatepay.model.enums.PixQRCodeStatus
import com.abacatepay.model.request.CustomerRequest
import com.abacatepay.model.request.PixQRCodeRequest
import com.abacatepay.model.response.AbacatePayResponse
import com.abacatepay.model.response.PixQRCodeResponse
import com.abacatepay.model.response.PixQRCodeStatusResponse
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue


class PixQRCodeServiceTest {

    @Test
    fun `should create pix qrcode`() {
        val pixQRCodeService = pixQRCodeServiceMock(response = AbacatePayResponse(data = pixQRCodeResponseTemplate))
        val response = runBlocking { pixQRCodeService.create(pixQRCodeRequestTemplate) }
        assertTrue(response.isSuccess)
        response.onSuccess {
            assertNotNull(it.data)
        }
    }

    @Test
    fun `should simulate a pix qrcode payment`(){
        val pixQRCodeService = pixQRCodeServiceMock(response = AbacatePayResponse(success = true, data = pixQRCodePaidResponseTemplate))

        val response = runBlocking { pixQRCodeService.simulatePayment(pixQRCodeResponseTemplate.id) }
        assertTrue(response.isSuccess)
        response.onSuccess {
            assertNotNull(it.data)
        }
    }

    @Test
    fun `should simulate a pix qrcode payment to an already paid`(){
        val pixQRCodeService = pixQRCodeServiceMock(response = AbacatePayResponse(success = false, data = null, error = "Pix QR Code not found"))

        val response = runBlocking { pixQRCodeService.simulatePayment(pixQRCodePaidResponseTemplate.id) }
        assertTrue(response.isSuccess)
        response.onSuccess {
            assertNull(it.data)
            assertEquals("Pix QR Code not found", it.error)
            assertFalse(it.success!!)
        }
    }

    @Test
    fun `should get pix qrcode payment status`(){
        val pixQRCodeService = pixQRCodeServiceMock(response = AbacatePayResponse(data = pixQRCodeStatusResponseTemplate))
        val response = runBlocking { pixQRCodeService.check(pixQRCodePaidResponseTemplate.id) }
        assertTrue(response.isSuccess)
        response.onSuccess {
            assertNotNull(it.data)
            assertEquals(PixQRCodeStatus.PAID, it.data.status)
        }
    }
    
    private val pixQRCodeRequestTemplate = PixQRCodeRequest(
        amount = 123,
        expiresIn = 123,
        description = "lorem ipsum",
        customer = CustomerRequest(
            name = "Daniel Lima",
            cellphone = "(11) 4002-8922",
            email = "daniel_lima@abacatepay.com",
            taxId = "123.456.789-01",
        ),
        metadata = mapOf(
            "externalId" to "123"
        )
    )

    private val pixQRCodeResponseTemplate = PixQRCodeResponse(
        id = "pix_char_URB64yZfYkqtaejHGwnxnHbX",
        amount = 100,
        status = PixQRCodeStatus.PENDING,
        devMode = true,
        brCode = "00020101021226950014br.gov.bcb.pix",
        brCodeBase64 = "data:image/png;base64,iVBORw0KGgoAAA",
        platformFee = 80,
        createdAt = "2025-03-24T21:50:20.772Z",
        updatedAt = "2025-03-24T21:50:20.772Z",
        expiresAt = "2025-03-24T21:50:20.772Z"
    )

    private val pixQRCodePaidResponseTemplate = pixQRCodeResponseTemplate.copy(status = PixQRCodeStatus.PAID)
    
    private val pixQRCodeStatusResponseTemplate = PixQRCodeStatusResponse(
        status = pixQRCodePaidResponseTemplate.status,
        expiresAt = pixQRCodePaidResponseTemplate.expiresAt
    )
    
    private inline fun <reified T> pixQRCodeServiceMock(
        response: T,
        httpStatusCode: HttpStatusCode = HttpStatusCode.OK,
        contentType: String? = null
    ) = PixQRCodeService(
        apiKey = "test-api-key",
        baseUrl = "http://localhost:8080",
        httpClient = httpClient(mockEngine(response, httpStatusCode, contentType))
    )
}