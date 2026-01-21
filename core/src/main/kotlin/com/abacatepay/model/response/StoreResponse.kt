package com.abacatepay.model.response

import kotlinx.serialization.Serializable

@Serializable
data class BalanceResponse(
    val available: Int,
    val pending: Int,
    val blocked: Int,
)

@Serializable
data class StoreResponse(
    val id: String,
    val name: String,
    val balance: BalanceResponse
)