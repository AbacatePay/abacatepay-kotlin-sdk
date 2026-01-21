package com.abacatepay.client


import com.abacatepay.model.request.BillingRequest
import com.abacatepay.model.request.CustomerRequest
import com.abacatepay.model.response.AbacatePayResponse
import com.abacatepay.model.response.BillingResponse
import com.abacatepay.model.response.CustomerResponse

interface AbacatePay {

    suspend fun createBilling(billing: BillingRequest): AbacatePayResponse<BillingResponse>

    suspend fun listBillings(): AbacatePayResponse<List<BillingResponse>>

    suspend fun createCustomer(customer: CustomerRequest): AbacatePayResponse<CustomerResponse>

    suspend fun listCustomers(): AbacatePayResponse<List<CustomerResponse>>
}