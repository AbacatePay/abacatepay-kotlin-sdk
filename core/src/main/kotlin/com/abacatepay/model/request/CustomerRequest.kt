package com.abacatepay.model.request

import kotlinx.serialization.Serializable


@Serializable
data class CustomerRequest(
    val name: String,
    val cellphone: String,
    val email: String,
    val taxId: String,
    val zipCode: String,
)
