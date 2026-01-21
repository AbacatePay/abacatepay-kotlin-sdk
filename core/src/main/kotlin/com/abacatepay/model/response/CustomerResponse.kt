package com.abacatepay.model.response

import com.abacatepay.model.request.CustomerRequest
import kotlinx.serialization.Serializable

@Serializable
data class CustomerResponse(
    val id: String,
    val metadata: CustomerRequest
)