package com.abacatepay.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    val id: String,
    val externalId: String,
    val quantity: Int,
)