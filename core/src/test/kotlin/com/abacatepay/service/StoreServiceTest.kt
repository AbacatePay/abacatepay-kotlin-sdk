package com.abacatepay.service

import com.abacatepay.httpClient
import com.abacatepay.mockEngine
import com.abacatepay.model.response.AbacatePayResponse
import com.abacatepay.model.response.BalanceResponse
import com.abacatepay.model.response.StoreResponse
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class StoreServiceTest {

    @Test
    fun `should retrieve store details`() {
        val storeService = storeServiceMock(AbacatePayResponse(success = true, data = storeResponseTemplate))
        val response = runBlocking { storeService.get() }
        assertTrue(response.isSuccess)
        response.onSuccess {
            assertNotNull(it.data)
        }
    }
    
    private val storeResponseTemplate = StoreResponse(
        id = "store_6wneWwG45Jt4EnPQedpJckhb",
        name = "Teste",
        balance = BalanceResponse(
            available = 12338,
            pending = 0,
            blocked = 0
        )
    )

    private inline fun <reified T> storeServiceMock(
        response: T,
        httpStatusCode: HttpStatusCode = HttpStatusCode.OK,
        contentType: String? = null
    ) = StoreService(
        apiKey = "test-api-key",
        baseUrl = "http://localhost:8080",
        httpClient = httpClient(mockEngine(response, httpStatusCode, contentType))
    )
}