package core

import com.abacatepay.client.AbacatePayClient
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
import io.ktor.client.engine.mock.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

class AbacatePayClientTest {

    @Test
    fun shouldCreateBillingAndCustomer() {
        val abacatePayClient: AbacatePayClient = abacatePayClientMock(
            AbacatePayResponse(
                billingResponseTemplate(productResponseTemplate(), customerResponseTemplate())
            )
        )

        val product = ProductRequest(externalId = "id", name = "product", quantity = 1, description = "description", price = 100)
        val customer = customerTemplate()

        val billing = BillingRequest(
            PaymentFrequency.ONE_TIME,
            listOf(PaymentMethod.PIX),
            listOf(product),
            "http://voltar",
            "http://completar",
            customer = customer
        )

        runBlocking {
            val createBilling = abacatePayClient.createBilling(billing)
            assertNotNull(createBilling)
        }
    }

    @Test
    fun shouldListBilling() {
        val abacatePayClient: AbacatePayClient = abacatePayClientMock(
            AbacatePayResponse(
                listOf(billingResponseTemplate(productResponseTemplate(), customerResponseTemplate()))
            )
        )

        runBlocking {
            val listBilling = abacatePayClient.listBillings()
            assertNotNull(listBilling)
        }
    }

    private fun productResponseTemplate() = ProductResponse("id", "id", 1)

    private fun customerTemplate() = CustomerRequest(name = "nome", cellphone = "1268711192", email ="clienteteste@gmail.com", "49799449065", zipCode = "00000-999")

    private fun customerResponseTemplate() = CustomerResponse(id = "id", metadata = customerTemplate())

    private fun billingResponseTemplate(productResponse: ProductResponse, customer: CustomerResponse) =
        BillingResponse(
            id ="id",
            url = "url",
            amount = 100,
            status = BillingStatus.PENDING,
            devMode = true,
            paymentMethods = listOf(PaymentMethod.PIX),
            products = listOf(productResponse),
            frequency = PaymentFrequency.ONE_TIME,
            customer = customer,
            nextBilling = "id",
            allowCoupons = false,
            coupons = emptyList(),
        )

    @Test
    fun shouldCreateCustomer() {
        val customer = customerTemplate()
        val abacatePayClient: AbacatePayClient = abacatePayClientMock(
            AbacatePayResponse(
                createCustomerResponseTemplate(customer)
            )
        )

        runBlocking {
            val customerResponse = abacatePayClient.createCustomer(customerTemplate())
            assertNotNull(customerResponse)
        }
    }

    @Test
    fun shouldListCustomer() {
        val customer = customerTemplate()
        val abacatePayClient: AbacatePayClient = abacatePayClientMock(
            AbacatePayResponse(
                listOf(
                    CustomerResponse(
                        "id",
                        customer
                    )
                )
            )
        )

        runBlocking {
            val customerResponse = abacatePayClient.listCustomers()
            assertNotNull(customerResponse)
        }
    }

    private fun createCustomerResponseTemplate(customer: CustomerRequest) = CustomerResponse(
        "id",
        metadata = customer,
    )

    private inline fun <reified T> abacatePayClientMock(response: T) =
        AbacatePayClient(apiKey = "apiKey", engine = MockEngine { _ ->
            respond(
                content = ByteReadChannel(
                    Json.encodeToString(
                        response
                    )
                ),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        })
}