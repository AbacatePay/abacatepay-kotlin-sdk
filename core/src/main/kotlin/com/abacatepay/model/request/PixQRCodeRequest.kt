package com.abacatepay.model.request

import kotlinx.serialization.Serializable


@Serializable
data class PixQRCodeRequest(
    val amount: Int,
    val expiresIn: Int,
    val description: String?,
    val customer: CustomerRequest? = null,
    val metadata: Map<String, String> = emptyMap(),
)