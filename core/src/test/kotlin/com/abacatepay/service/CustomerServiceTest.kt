package com.abacatepay.service

import com.abacatepay.exception.AbacatePayGenericException
import com.abacatepay.httpClient
import com.abacatepay.mockEngine
import com.abacatepay.model.request.CustomerRequest
import com.abacatepay.model.response.AbacatePayResponse
import com.abacatepay.model.response.CustomerResponse
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertTrue


class CustomerServiceTest {

    @Test
    fun `should create customer`() {
        val customerService = customerServiceMock(AbacatePayResponse(data = customerResponseTemplate))

        val request = customerRequestTemplate1
        val response = runBlocking { customerService.create(request) }
        assertTrue(response.isSuccess)
        response.onSuccess {
            val customerResponse = it.data!!
            val customer = customerResponse.metadata
            assertEquals("123", customerResponse.id)
            assertEquals("customer_1_name", customer.name)
            assertEquals("kotlin_sdk_customer_1@abacatepay.com", customer.email)
            assertEquals("00000000011", customer.taxId)
            assertEquals("11111-111", customer.zipCode)
        }
    }

    @Test
    fun `should list customers`() {
        val customerService = customerServiceMock(customersResponseTemplate)
        val response = runBlocking { customerService.list() }
        assertTrue(response.isSuccess)
        response.onSuccess {
            val customers = it.data!!
            assertEquals(2, customers.size)
        }
    }

    @Test
    fun `should fail when creating a new customer`(){
        val customerService = customerServiceMock(response = AbacatePayResponse(
            success = false,
            data = null,
            error = "Expected property 'cellphone' to be string but found: undefined"
        ), httpStatusCode = HttpStatusCode.UnprocessableEntity, contentType = "application/json")

        val response = runBlocking { customerService.create(customerRequestTemplateMissingCellPhone) }
        response.onFailure {
            assertIs<AbacatePayGenericException>(it)
            assertEquals("Expected property 'cellphone' to be string but found: undefined", it.message)
            assertEquals(422, it.status)
        }
    }

    @Test
    fun `should fail when listing customers`(){
        val customerService = customerServiceMock(
            response = "Internal Server Error",
            httpStatusCode = HttpStatusCode.InternalServerError
        )

        val response = runBlocking { customerService.list() }
        response.onFailure {
            assertIs<AbacatePayGenericException>(it)
            assertEquals("Internal Server Error", it.message)
            assertEquals(500, it.status)
        }
    }




    private val customerRequestTemplate1 : CustomerRequest = CustomerRequest(
        name = "customer_1_name",
        cellphone = "+5511999999999",
        email = "kotlin_sdk_customer_1@abacatepay.com",
        taxId = "00000000011",
        zipCode = "11111-111"
    )

    private val customerRequestTemplate2 : CustomerRequest = CustomerRequest(
        name = "customer_name_2",
        cellphone = "+5521999999999",
        email = "kotlin_sdk_customer_2@abacatepay.com",
        taxId = "00000000022",
        zipCode = "22222-222"
    )

    private val customerRequestTemplateMissingCellPhone : CustomerRequest = CustomerRequest(
        name = "customer_3_name",
        cellphone = "",
        email = "kotlin_sdk_customer_3@abacatepay.com",
        taxId = "00000000033",
        zipCode = "33333-333"
    )

    private val customerResponseTemplate = CustomerResponse(
        id = "123",
        metadata = customerRequestTemplate1
    )

    private val customerResponseTemplate2 = CustomerResponse(
        id = "456",
        metadata = customerRequestTemplate2
    )

    private val customersResponseTemplate = AbacatePayResponse(data = listOf(customerResponseTemplate, customerResponseTemplate2))


    private inline fun <reified T> customerServiceMock(
        response: T,
        httpStatusCode: HttpStatusCode = HttpStatusCode.OK,
        contentType: String? = null
    ) = CustomerService(
            apiKey = "test-api-key",
            baseUrl = "http://localhost:8080",
            httpClient = httpClient(mockEngine(response, httpStatusCode, contentType))
        )
}