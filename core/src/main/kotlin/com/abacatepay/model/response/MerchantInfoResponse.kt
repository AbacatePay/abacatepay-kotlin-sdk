package com.abacatepay.model.response

import kotlinx.serialization.Serializable

@Serializable
data class MerchantInfoResponse(
    val name: String,
    val website: String,
    val createdAt: String,
)
