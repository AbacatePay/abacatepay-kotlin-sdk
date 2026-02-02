package com.abacatepay.service

import com.abacatepay.httpClient
import com.abacatepay.mockEngine
import com.abacatepay.model.enums.CouponDiscountKind
import com.abacatepay.model.enums.CouponStatus
import com.abacatepay.model.request.Coupon
import com.abacatepay.model.request.CouponRequest
import com.abacatepay.model.response.AbacatePayResponse
import com.abacatepay.model.response.CouponResponse
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class CouponServiceTest {
    
    @Test
    fun `should create coupon`() {
        val couponService = couponServiceMock(AbacatePayResponse(data = couponResponseTemplate))
        val response = runBlocking { couponService.create(couponRequestTemplate) }
        assertTrue(response.isSuccess)
        response.onSuccess {
            assertNotNull(it.data)
        }
    }

    @Test
    fun `should list coupons`() {
        val couponService = couponServiceMock(AbacatePayResponse(data = listOf(couponResponseTemplate)))
        val response = runBlocking { couponService.list() }
        assertTrue(response.isSuccess)
        response.onSuccess {
            assertNotNull(it.data)
            assertEquals(1, it.data.size)
        }
    }
    
    private val couponRequestTemplate = CouponRequest(
        data = Coupon(
            code = "DEYVIN_20",
            discountKind = CouponDiscountKind.PERCENTAGE,
            discount = 123,
            notes = "Cupom de desconto pro meu público",
            maxRedeems = -1,
            metadata = emptyMap()
        )
    )

    private val couponResponseTemplate = CouponResponse(
        id = "DEYVIN_20",
        discountKind = CouponDiscountKind.PERCENTAGE,
        discount = 123,
        status = CouponStatus.ACTIVE,
        createdAt = "2025-05-25T23:43:25.250Z",
        updatedAt = "2025-05-25T23:43:25.250Z",
        notes = "Cupom de desconto pro meu público",
        maxRedeems = -1,
        redeemsCount = 0,
        devMode = true,
        metadata = emptyMap()
    )

    
    private inline fun <reified T> couponServiceMock(
        response: T,
        httpStatusCode: HttpStatusCode = HttpStatusCode.OK,
        contentType: String? = null
    ) = CouponService(
        apiKey = "test-api-key",
        baseUrl = "http://localhost:8080",
        httpClient = httpClient(mockEngine(response, httpStatusCode, contentType))
    )
}