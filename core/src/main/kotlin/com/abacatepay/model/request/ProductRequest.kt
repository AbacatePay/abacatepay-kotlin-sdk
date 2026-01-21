package com.abacatepay.model.request

import kotlinx.serialization.Serializable

@Serializable
data class ProductRequest(
    val externalId: String,

    val name: String,

    val quantity: Int,

    val price: Int,

    val description: String?
)